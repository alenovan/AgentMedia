package com.example.agentmedia.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.agentmedia.R;
import com.example.agentmedia.adapater.ListPaymentAdapter;
import com.example.agentmedia.adapater.ListProviderAdapter;
import com.example.agentmedia.adapater.ListTransaksiAdapter;
import com.example.agentmedia.api.ApiClient;
import com.example.agentmedia.api.Service;
import com.example.agentmedia.model.PaymentItem;
import com.example.agentmedia.model.ProviderItem;
import com.example.agentmedia.model.TransaksiItem;
import com.example.agentmedia.tools.PublicTools;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListProvider extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PublicTools tools;
    private Integer status;
    private ListProviderAdapter adapter;
    private ArrayList<ProviderItem> provArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_provider);
        declaration();
        status = getIntent().getIntExtra("theme",0);
        back();
        if(status == 1){
            listTransaksiPulsa();
        }else{
            listTransaksiPaket();
        }
    }

    public void declaration(){

        tools = new PublicTools(getApplicationContext());
        recyclerView = findViewById(R.id.list_provider);
        recyclerView.setNestedScrollingEnabled(false);
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



    public  void listTransaksiPulsa(){
        Service service = ApiClient.getRetrofitInstance().create(Service.class);
        Call<List<ProviderItem>> call = service.getAllListProviderPulsa();
        call.enqueue(new Callback<List<ProviderItem>>() {
            @Override
            public void onResponse(Call<List<ProviderItem>> call, Response<List<ProviderItem>> response) {
                Log.e("successProvider",response.body().toString());
                if(response.isSuccessful()) {
                    generateDataList(response.body());
                }else{
                    Toast.makeText(getApplicationContext(), "Mohon Cek Koneksi Internet anda", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ProviderItem>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("errorPaymentList",t.getMessage());
            }
        });


    }


    public  void listTransaksiPaket(){
        Service service = ApiClient.getRetrofitInstance().create(Service.class);
        Call<List<ProviderItem>> call = service.getAllListProviderPaket();
        call.enqueue(new Callback<List<ProviderItem>>() {
            @Override
            public void onResponse(Call<List<ProviderItem>> call, Response<List<ProviderItem>> response) {
                if(response.isSuccessful()) {
                    generateDataList(response.body());
                }else{
                    Toast.makeText(getApplicationContext(), "Mohon Cek Koneksi Internet anda", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<ProviderItem>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("errorPaymentList",t.getMessage());
            }
        });


    }

    private void generateDataList(List<ProviderItem> listPayment) {
        adapter = new ListProviderAdapter(getApplicationContext(),listPayment);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
}
