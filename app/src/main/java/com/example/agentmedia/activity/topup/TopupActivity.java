package com.example.agentmedia.activity.topup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agentmedia.R;
import com.example.agentmedia.activity.login.LoginActivity;
import com.example.agentmedia.adapater.ListPaymentAdapter;
import com.example.agentmedia.api.ApiClient;
import com.example.agentmedia.api.Service;
import com.example.agentmedia.api.SharedPrefManager;
import com.example.agentmedia.model.PaymentItem;
import com.example.agentmedia.tools.RecyclerItemClickListener;
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

public class TopupActivity extends AppCompatActivity{
    private RecyclerView recyclerView;
    private PublicTools tools;
    private EditText nominal;
    TextView saldo;
    private ListPaymentAdapter adapter;
    private List<PaymentItem> payArrayList = new ArrayList<>();
    SharedPrefManager sharedPrefManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topup);
        declaration();
        back();
        listTransaksi();
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

    public void declaration(){
        sharedPrefManager = new SharedPrefManager(this);
        tools = new PublicTools(getApplicationContext());
        saldo = findViewById(R.id.saldo);
        recyclerView = findViewById(R.id.list_payment);
        nominal = findViewById(R.id.nominal);
        recyclerView.setNestedScrollingEnabled(false);
        saldo();
    }

    public  void listTransaksi(){
        Service service = ApiClient.getRetrofitInstance().create(Service.class);
        Call<List<PaymentItem>> call = service.getAllListPayment();
        call.enqueue(new Callback<List<PaymentItem>>() {
            @Override
            public void onResponse(Call<List<PaymentItem>> call, Response<List<PaymentItem>>  response) {
                if(response.isSuccessful()) {
                    payArrayList = response.body();
                    generateDataList(payArrayList);
                }else{
                    Toast.makeText(getApplicationContext(), "Mohon Cek Koneksi Internet anda", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<PaymentItem>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("errorPaymentList",t.getMessage());
            }
        });


    }

    /*Method to generate List of data using RecyclerView with custom adapter*/
    private void generateDataList(List<PaymentItem> listPayment) {
        adapter = new ListPaymentAdapter(listPayment,getApplicationContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // do whatever

                        String nominal_text = nominal.getText().toString();
                        String id_member = sharedPrefManager.getSpId();
                        String id_payment = payArrayList.get(position).getIdPaymentAccount();
//                        Toast.makeText(getApplicationContext(), "id"+id_member, Toast.LENGTH_SHORT).show();
                        Service service = ApiClient.getRetrofitInstance().create(Service.class);
                        if (nominal_text.isEmpty()) {
                            Toast.makeText(getApplicationContext(), "isi nominal topup anda", Toast.LENGTH_SHORT).show();
                        } else {
                            service.topupRequest(id_member, nominal_text, id_payment).enqueue(new Callback<ResponseBody>() {
                                @Override
                                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                                    if (response.isSuccessful()) {
                                        try {
                                            JSONObject jsonRESULTS = new JSONObject(response.body().string());
                                            if (jsonRESULTS.getString("result").equals("true")) {
                                                Toast.makeText(getApplicationContext(), jsonRESULTS.getString("message"), Toast.LENGTH_SHORT).show();
                                                Intent i = new Intent(getApplicationContext(),TopupPaymentActivity.class);
                                                i.putExtra("id_topup",jsonRESULTS.getString("id_topup"));
                                                i.putExtra("status","1");
                                                startActivity(i);
                                            } else {
                                                String error_message = jsonRESULTS.getString("message");
                                                Toast.makeText(getApplicationContext(), error_message, Toast.LENGTH_SHORT).show();
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Layanan Saat ini sendang gagguan", Toast.LENGTH_SHORT).show();
                                    }

                                }

                                @Override
                                public void onFailure(Call<ResponseBody> call, Throwable t) {
                                    Toast.makeText(getApplicationContext(), "Mohon Maaf Layanan sedang gagguan", Toast.LENGTH_SHORT).show();
                                    Log.e("errorPaymentList", t.getMessage());
                                }

                            });
                        }
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // do whatever
                    }
                })
        );
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
