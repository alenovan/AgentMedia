package com.example.agentmedia.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.agentmedia.R;
import com.example.agentmedia.adapater.ListPaymentAdapter;
import com.example.agentmedia.adapater.ListTransaksiAdapter;
import com.example.agentmedia.api.ApiClient;
import com.example.agentmedia.api.Service;
import com.example.agentmedia.model.PaymentItem;
import com.example.agentmedia.model.TransaksiItem;
import com.example.agentmedia.tools.PublicTools;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopupActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PublicTools tools;
    private ListPaymentAdapter adapter;
    private ArrayList<PaymentItem> payArrayList;
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
        tools = new PublicTools(getApplicationContext());
        recyclerView = findViewById(R.id.list_payment);
        recyclerView.setNestedScrollingEnabled(false);
    }

    public  void listTransaksi(){
        Service service = ApiClient.getRetrofitInstance().create(Service.class);
        Call<List<PaymentItem>> call = service.getAllListPayment();
        call.enqueue(new Callback<List<PaymentItem>>() {
            @Override
            public void onResponse(Call<List<PaymentItem>> call, Response<List<PaymentItem>>  response) {
                if(response.isSuccessful()) {
                    generateDataList(response.body());
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
    }
}
