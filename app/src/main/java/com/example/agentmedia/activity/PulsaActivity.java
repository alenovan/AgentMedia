package com.example.agentmedia.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agentmedia.R;
import com.example.agentmedia.activity.topup.TopupPaymentActivity;
import com.example.agentmedia.adapater.ListPaymentAdapter;
import com.example.agentmedia.adapater.ListPriceAdapter;
import com.example.agentmedia.adapater.ListTransaksiAdapter;
import com.example.agentmedia.api.ApiClient;
import com.example.agentmedia.api.Service;
import com.example.agentmedia.api.SharedPrefManager;
import com.example.agentmedia.model.PaymentItem;
import com.example.agentmedia.model.PriceItem;
import com.example.agentmedia.model.TransaksiItem;
import com.example.agentmedia.model.topup.RiwayatTopupItem;
import com.example.agentmedia.tools.PublicTools;
import com.example.agentmedia.tools.RecyclerItemClickListener;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PulsaActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PublicTools tools;
    ImageView getContact;
    EditText edit_no;
    Spinner provider;
    CardView bottom_bar;
    TextView price_sell;
    PublicTools publicTools;
    private ListPriceAdapter adapter;
    TextView saldo;
    Integer sumPrice,sumSaldo;
    String id_product;
    SharedPrefManager sharedPrefManager;
    private ArrayList<PriceItem> priceArrayList;
    private List<PriceItem> payArrayList = new ArrayList<>();
    private final int REQUEST_CODE=99;
    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pulsa);
        declaration();
        listPrice();
        back();
        beli();
        saldo();
        showContacts();
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                new IntentFilter("custom-message"));
    }

    private void showContacts() {
        // Check the SDK version and whether the permission is already granted or not.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);
            //After this point you wait for callback in onRequestPermissionsResult(int, String[], int[]) overriden method
        } else {
            // Android version is lesser than 6.0 or the permission is already granted.

            getNoContact();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                           int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission is granted
                showContacts();
            } else {
                Toast.makeText(this, "Until you grant the permission, we canot display the names", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void back() {
        ImageView back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    public void beli(){
        Button beli = findViewById(R.id.beli);


        beli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sumPrice <= sumSaldo){
                    Service service = ApiClient.getRetrofitInstance().create(Service.class);
                    String id_member = sharedPrefManager.getSpId();
                    String tujuan = edit_no.getText().toString();
                    service.pemesnanRequest(id_member, id_product, tujuan).enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if (response.isSuccessful()) {
                                try {
                                    JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                    if (jsonRESULTS.getString("result").equals("true")) {
                                        Intent i = new Intent(getApplicationContext(), SuccessActivity.class);
                                        i.putExtra("code","listrik");
                                        startActivity(i);
                                        finish();
                                    } else {
                                        String error_message = jsonRESULTS.getString("message");
//                                        Toast.makeText(getApplicationContext(), error_message, Toast.LENGTH_SHORT).show();
                                        tools.textDialog(getWindow().getDecorView(),"Mohon Maaf",error_message);
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            } else {
                                tools.textDialog(getWindow().getDecorView(),"Mohon Maaf","Mohon Maaf Layanan sedang gangguan");
//                                Toast.makeText(getApplicationContext(), "Layanan Saat ini sendang gagguan", Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            tools.textDialog(getWindow().getDecorView(),"Mohon Maaf","Mohon Maaf Layanan Sedang Gangguan");
//                            Toast.makeText(getApplicationContext(), "Mohon Maaf Layanan sedang gagguan", Toast.LENGTH_SHORT).show();
                            Log.e("errorPaymentList", t.getMessage());
                        }

                    });

                }else{
                    tools.textDialog(getWindow().getDecorView(),"Mohon Maaf","Saldo anda tidak mencukupi");
                }

            }
        });

    }

    public void declaration() {
        sharedPrefManager = new SharedPrefManager(getApplicationContext());
        saldo = findViewById(R.id.saldo);
        edit_no= findViewById(R.id.edit_no);
        publicTools = new PublicTools(getApplicationContext());
        bottom_bar = findViewById(R.id.bottom_bar);
        bottom_bar.setVisibility(View.GONE);
        price_sell = findViewById(R.id.price_sell);
        getContact = findViewById(R.id.getContact);
        tools = new PublicTools(getApplicationContext());
        recyclerView = findViewById(R.id.list_price);
        recyclerView.setNestedScrollingEnabled(false);
    }

    public  void saldo(){
        Service service = ApiClient.getRetrofitInstance().create(Service.class);
        String id_member = sharedPrefManager.getSpId();
        service.getSaldoRequest(id_member).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                String status = response.body().getAsJsonObject().get("result").toString();
                if (status.equals("true")){
                    JsonArray array = response.body().getAsJsonObject().getAsJsonArray("data");
                    Log.d("hasilDetail",array.toString());
                    for (int i = 0; i < array.size(); i++) {
                        JsonObject detail =  array.get(i).getAsJsonObject();
//                        saldo.setText(detail.get("saldo_member").toString().replace("\"", ""));
                        saldo.setText(tools.numberFormatWithoutRp(Integer.valueOf(detail.get("saldo_member").toString().replace("\"", ""))).toString());
                        String saldo = detail.get("saldo_member").toString().replace("\"", "");
                        sumSaldo =Integer.parseInt(saldo);
                    }
                } else {
                    String error_message = status;
                    System.out.println(response.body().getAsJsonObject());
                    Toast.makeText(getApplicationContext(), error_message, Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Mohon Maaf Layanan sedang gagguan", Toast.LENGTH_SHORT).show();
                Log.e("errorPaymentList",t.getMessage());
            }

        });
    }

    public void getNoContact(){
        getContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

    }

    public void listPrice() {
        Service service = ApiClient.getRetrofitInstance().create(Service.class);
        String type = getIntent().getExtras().getString("type");
        String cat = getIntent().getExtras().getString("cat");
        Call<List<PriceItem>> call = service.getAllPrice(cat, "admin", type);
        call.enqueue(new Callback<List<PriceItem>>() {
            @Override
            public void onResponse(Call<List<PriceItem>> call, Response<List<PriceItem>> response) {
                if (response.isSuccessful()) {
                    payArrayList = response.body();
                    generateDataList(response.body());
                } else {

                    bottom_bar.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "Layanan Saat ini sendang gangguan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<PriceItem>> call, Throwable t) {
                bottom_bar.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "Mohon Maaf Layanan sedang gagguan", Toast.LENGTH_SHORT).show();
                Log.e("errorPaymentList", t.getMessage());
            }
        });


    }


    /*Method to generate List of data using RecyclerView with custom adapter*/
    private void generateDataList(List<PriceItem> priceItem) {
        adapter = new ListPriceAdapter(priceItem, getApplicationContext(),1);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setAdapter(adapter);

    }

    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent
            String price = intent.getStringExtra("price_sell");
            price_sell.setText(publicTools.numberFormat(Integer.valueOf(price)));
            id_product = intent.getStringExtra("id_product");
            sumPrice = Integer.parseInt(intent.getStringExtra("price_sell"));
            bottom_bar.setVisibility(View.VISIBLE);
        }
    };

    @Override
    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);
        switch (reqCode) {
            case (REQUEST_CODE):
                if (resultCode == Activity.RESULT_OK) {
                    Uri contactData = data.getData();
                    Cursor c = getContentResolver().query(contactData, null, null, null, null);
                    if (c.moveToFirst()) {
                        String contactId = c.getString(c.getColumnIndex(ContactsContract.Contacts._ID));
                        String hasNumber = c.getString(c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
                        String num = "";
                        if (Integer.valueOf(hasNumber) == 1) {
                            Cursor numbers = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId, null, null);
                            while (numbers.moveToNext()) {
                                num = numbers.getString(numbers.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                                edit_no.setText(num.toString());
//                                Toast.makeText(getApplicationContext(), "Number="+num, Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                    break;
                }
        }
    }

}
