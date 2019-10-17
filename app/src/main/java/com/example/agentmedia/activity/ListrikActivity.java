package com.example.agentmedia.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.agentmedia.R;
import com.example.agentmedia.adapater.ListPriceAdapter;
import com.example.agentmedia.api.ApiClient;
import com.example.agentmedia.api.Service;
import com.example.agentmedia.model.PriceItem;
import com.example.agentmedia.tools.PublicTools;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListrikActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PublicTools tools;
    Spinner provider;
    private ListPriceAdapter adapter;
    CardView bottom_bar;
    private ArrayList<PriceItem> priceArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listrik);
        declaration();
        listPrice();
        back();
        beli();
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
                startActivity(new Intent(getApplicationContext(), SuccessActivity.class));
            }
        });

    }
    public void declaration(){
        bottom_bar = findViewById(R.id.bottom_bar);
        tools = new PublicTools(getApplicationContext());
        recyclerView = findViewById(R.id.list_price);
        recyclerView.setNestedScrollingEnabled(false);
    }

    public  void listPrice(){
        Service service = ApiClient.getRetrofitInstance().create(Service.class);
        String type = "2";
        String cat  ="4";
        Call<List<PriceItem>> call = service.getAllPrice(cat,"admin",type);
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
        adapter = new ListPriceAdapter(priceItem,getApplicationContext());
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        recyclerView.setAdapter(adapter);
    }

}
