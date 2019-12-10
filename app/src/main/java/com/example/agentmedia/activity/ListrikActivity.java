package com.example.agentmedia.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agentmedia.R;
import com.example.agentmedia.activity.topup.TopupPaymentActivity;
import com.example.agentmedia.adapater.ListPriceAdapter;
import com.example.agentmedia.api.ApiClient;
import com.example.agentmedia.api.Service;
import com.example.agentmedia.api.SharedPrefManager;
import com.example.agentmedia.model.PriceItem;
import com.example.agentmedia.tools.PublicTools;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListrikActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PublicTools tools;
    Spinner provider;
    private ListPriceAdapter adapter;
    CardView bottom_bar;
    TextView saldo;
    SharedPrefManager sharedPrefManager;
    EditText edit_no;
    String id_product;
    Integer sumPrice;
    Integer sumSaldo;
    TextView price_sell;
    PublicTools publicTools;
    private ArrayList<PriceItem> priceArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listrik);
        declaration();
        listPrice();
        back();
        beli();
        saldo();
        tools = new PublicTools(getApplication());
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver,
                new IntentFilter("custom-message"));
    }

    public void back(){
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
                                tools.textDialog(getWindow().getDecorView(),"Mohon Maaf","Layanan Saat ini sendang gagguan");
//                                Toast.makeText(getApplicationContext(), "Layanan Saat ini sendang gagguan", Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
//                            Toast.makeText(getApplicationContext(), "Mohon Maaf Layanan sedang gagguan", Toast.LENGTH_SHORT).show();
                            tools.textDialog(getWindow().getDecorView(),"Mohon Maaf","Mohon Maaf Layanan sedang gagguan");
                            Log.e("errorPaymentList", t.getMessage());
                        }

                    });

                }else{
                    tools.textDialog(getWindow().getDecorView(),"Mohon Maaf","Pastikan saldo anda mencukupi");
                }

            }
        });

    }
    public void declaration(){
        sharedPrefManager = new SharedPrefManager(getApplicationContext());
        saldo = findViewById(R.id.saldo);
        edit_no = findViewById(R.id.edit_no);
        publicTools = new PublicTools(getApplicationContext());
        bottom_bar = findViewById(R.id.bottom_bar);
        bottom_bar.setVisibility(View.GONE);
        price_sell = findViewById(R.id.price_sell);
        tools = new PublicTools(getApplicationContext());
        recyclerView = findViewById(R.id.list_price);
        recyclerView.setNestedScrollingEnabled(false);
    }

    public  void listPrice(){
        Service service = ApiClient.getRetrofitInstance().create(Service.class);
        Call<List<PriceItem>> call = service.getAllPrice("2","admin","11");
        call.enqueue(new Callback<List<PriceItem>>() {
            @Override
            public void onResponse(Call<List<PriceItem>> call, Response<List<PriceItem>> response) {
                if(response.isSuccessful()) {
                    generateDataList(response.body());
                }else{

                    bottom_bar.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "Layanan Saat ini sendang gagguan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<PriceItem>> call, Throwable t) {
                bottom_bar.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "Mohon Maaf Layanan sedang gagguan", Toast.LENGTH_SHORT).show();
                Log.e("errorPaymentList",t.getMessage());
            }
        });


    }



    /*Method to generate List of data using RecyclerView with custom adapter*/
    private void generateDataList(List<PriceItem> priceItem) {
        adapter = new ListPriceAdapter(priceItem,getApplicationContext(),1);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        recyclerView.setAdapter(adapter);
    }

    public BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            // Get extra data included in the Intent
            String price = intent.getStringExtra("price_sell");
            id_product = intent.getStringExtra("id_product");
            sumPrice = Integer.parseInt(intent.getStringExtra("price_sell"));
            price_sell.setText(publicTools.numberFormat(Integer.valueOf(price)));
            bottom_bar.setVisibility(View.VISIBLE);


        }
    };


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

}
