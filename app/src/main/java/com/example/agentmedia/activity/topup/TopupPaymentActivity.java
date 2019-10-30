package com.example.agentmedia.activity.topup;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agentmedia.R;
import com.example.agentmedia.activity.MainActivity;
import com.example.agentmedia.activity.SuccessActivity;
import com.example.agentmedia.api.ApiClient;
import com.example.agentmedia.api.Service;
import com.example.agentmedia.api.SharedPrefManager;
import com.example.agentmedia.tools.PublicTools;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;


import android.media.MediaScannerConnection;
import android.os.Environment;
import android.provider.MediaStore;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import java.util.List;

import static android.media.MediaRecorder.VideoSource.CAMERA;

public class TopupPaymentActivity extends AppCompatActivity {
    TextView nameAgent,noHp,nominalTopup,namaBank,namaRek,noRek;
    SharedPrefManager sharedPrefManager;
    CardView cardDetailTransaksi;
    PublicTools publicTools;
    RelativeLayout confirmation;
    String donePath;
    private ImageView imageview;
    private static final String IMAGE_DIRECTORY = "/buktipembayaranagenmedia";
    private int GALLERY = 1, CAMERA = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topup_payment);
        requestMultiplePermissions();
        declaration();
    }

    public void declaration(){
        confirmation = findViewById(R.id.confirmation);
        publicTools = new PublicTools(getApplicationContext());
        sharedPrefManager = new SharedPrefManager(this);
        nameAgent = findViewById(R.id.nameAgent);
        imageview = findViewById(R.id.imageview);
        noHp = findViewById(R.id.noHp);
        nominalTopup = findViewById(R.id.nominalTopup);
        namaBank = findViewById(R.id.namaBank);
        namaRek = findViewById(R.id.namaRek);
        noRek = findViewById(R.id.noRek);
        cardDetailTransaksi = findViewById(R.id.cardDetailTransaksi);
        String status = getIntent().getStringExtra("status");
        if(status.equals("1")){
            cardDetailTransaksi.setVisibility(View.VISIBLE);
        }else{
            cardDetailTransaksi.setVisibility(View.GONE);
            confirmation.setVisibility(View.GONE);
        }
        callDetailtopup();
    }

    public  void callDetailtopup(){
        String id_member = sharedPrefManager.getSpId();
        String id_topup = getIntent().getExtras().getString("id_topup");
        Service service = ApiClient.getRetrofitInstance().create(Service.class);
        service.topupDetailRequest(id_member,id_topup).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response){
                if (response.isSuccessful()){
                        String status = response.body().getAsJsonObject().get("result").toString();
                        if (status.equals("true")){
                            JsonArray array = response.body().getAsJsonObject().getAsJsonArray("data");
                            Log.d("hasilDetail",array.toString());
                            for (int i = 0; i < array.size(); i++) {
                                JsonObject detail =  array.get(i).getAsJsonObject();
                                nameAgent.setText(detail.get("name_member").toString().replace("\"", ""));
                                noHp.setText(sharedPrefManager.getSpNohp());
                                nominalTopup.setText(publicTools.numberFormat(Integer.valueOf(detail.get("nominal").toString().replace("\"", ""))).toString());
                                namaBank.setText(detail.get("name_payment_method").toString().replace("\"", ""));
                                namaRek.setText(detail.get("name_payment_account").toString().replace("\"", ""));
                                noRek.setText(detail.get("nomor_payment_account").toString().replace("\"", ""));
                              }
                        } else {
                            String error_message = status;
                            Toast.makeText(getApplicationContext(), error_message, Toast.LENGTH_SHORT).show();
                        }
                }else{
//                    Toast.makeText(getApplicationContext(), "Layanan Saat ini sendang gagguan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Mohon Maaf Layanan sedang gagguan", Toast.LENGTH_SHORT).show();
                Log.e("errorPaymentList",t.getMessage());
            }



        });
    }
    public void onConfirmation(View view) {
        Service service = ApiClient.getRetrofitInstance().create(Service.class);
        RequestBody  id_member =  RequestBody.create(MediaType.parse("text/plain"), sharedPrefManager.getSpId());
        RequestBody  id_topup =  RequestBody.create(MediaType.parse("text/plain"), getIntent().getExtras().getString("id_topup"));//getIntent().getExtras().getString("id_topup");
        File file = new File(donePath);
        RequestBody mFile = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part fileToUpload = MultipartBody.Part.createFormData("file", file.getName(), mFile);
        service.topupKonfirmationRequest(fileToUpload,id_member,id_topup).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response)  {
                if (response.isSuccessful()){
                    String status = response.body().getAsJsonObject().get("result").toString();
                    if (status.equals("true")){
                        Log.d("hasilDetail",response.body().getAsJsonObject().toString());
                        String error_message = response.body().getAsJsonObject().get("message").toString();
                        Toast.makeText(getApplicationContext(), error_message, Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getApplicationContext(), SuccessActivity.class);
                        i.putExtra("code","topup");
                        startActivity(i);
                    } else {
                        JsonArray array = response.body().getAsJsonObject().getAsJsonArray("error");
                        Log.d("hasilDetail",response.body().getAsJsonObject().toString());
                        String error_message = response.body().getAsJsonObject().toString();
                        Toast.makeText(getApplicationContext(), error_message, Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(), "Layanan Saat ini sendang gagguan", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Mohon Maaf Layanan sedang gagguan", Toast.LENGTH_SHORT).show();
                Log.e("errorPaymentList",t.getMessage());
            }

        });
    }

    public void onUploadProof(View view) {
        AlertDialog.Builder pictureDialog = new AlertDialog.Builder(this);
        pictureDialog.setTitle("Select Action");
        String[] pictureDialogItems = {
                "Select photo from gallery",
                "Capture photo from camera" };
        pictureDialog.setItems(pictureDialogItems,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                choosePhotoFromGallary();
                                break;
                            case 1:
                                takePhotoFromCamera();
                                break;
                        }
                    }
                });
        pictureDialog.show();
    }

    public void choosePhotoFromGallary() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(galleryIntent, GALLERY);
    }

    private void takePhotoFromCamera() {
        Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAMERA);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == this.RESULT_CANCELED) {
            return;
        }
        if (requestCode == GALLERY) {
            if (data != null) {
                Uri contentURI = data.getData();
                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), contentURI);
                    String path = saveImage(bitmap);
//                    Toast.makeText(getApplicationContext(), path, Toast.LENGTH_SHORT).show();
                    donePath = path;
                    confirmation.setVisibility(View.VISIBLE);
                    imageview.setImageBitmap(bitmap);

                } catch (IOException e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Failed!", Toast.LENGTH_SHORT).show();
                }
            }

        } else if (requestCode == CAMERA) {
            Bitmap thumbnail = (Bitmap) data.getExtras().get("data");
            imageview.setImageBitmap(thumbnail);
            String path = saveImage(thumbnail);
            donePath = path;
            confirmation.setVisibility(View.VISIBLE);
//            Toast.makeText(getApplicationContext(), "Image Saved!", Toast.LENGTH_SHORT).show();
        }
    }

    public String saveImage(Bitmap myBitmap) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes);
        File wallpaperDirectory = new File(
                Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY);
        // have the object build the directory structure, if needed.
        if (!wallpaperDirectory.exists()) {
            wallpaperDirectory.mkdirs();
        }

        try {
            File f = new File(wallpaperDirectory, Calendar.getInstance()
                    .getTimeInMillis() + ".jpg");
            f.createNewFile();
            FileOutputStream fo = new FileOutputStream(f);
            fo.write(bytes.toByteArray());
            MediaScannerConnection.scanFile(this,
                    new String[]{f.getPath()},
                    new String[]{"image/jpeg"}, null);
            fo.close();
            Log.d("TAG", "File Saved::---&gt;" + f.getAbsolutePath());

            return f.getAbsolutePath();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return "";
    }

    private void  requestMultiplePermissions(){
        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {
                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {
                            Toast.makeText(getApplicationContext(), "All permissions are granted by user!", Toast.LENGTH_SHORT).show();
                        }

                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied()) {
                            // show alert dialog navigating to Settings
                            //openSettingsDialog();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }


                }).
                withErrorListener(new PermissionRequestErrorListener() {
                    @Override
                    public void onError(DexterError error) {
                        Toast.makeText(getApplicationContext(), "Some Error! ", Toast.LENGTH_SHORT).show();
                    }
                })
                .onSameThread()
                .check();
    }
}
