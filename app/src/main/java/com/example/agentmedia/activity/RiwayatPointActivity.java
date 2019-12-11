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
import com.example.agentmedia.adapater.ListPointAdapter;
import com.example.agentmedia.adapater.ListRiwayatTransaksiAdapter;
import com.example.agentmedia.adapater.ListTransaksiAdapter;
import com.example.agentmedia.api.ApiClient;
import com.example.agentmedia.api.Service;
import com.example.agentmedia.api.SharedPrefManager;
import com.example.agentmedia.model.RiwayatItem;
import com.example.agentmedia.model.TransaksiItem;
import com.example.agentmedia.tools.PublicTools;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RiwayatPointActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PublicTools tools;
//    private ListPointAdapter adapter;
//    private ArrayList<TransaksiItem> transArrayList;
    SharedPrefManager sharedPrefManager;
    private ListRiwayatTransaksiAdapter adapter;
    private ArrayList<RiwayatItem> transArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat_point);
        declaration();
        back();
        listTransaksi();
    }
    public void declaration(){
        sharedPrefManager = new SharedPrefManager(getApplicationContext());
        tools = new PublicTools(getApplicationContext());
        recyclerView = findViewById(R.id.list_transaksi);
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

    public  void listTransaksi(){
        String id_member = sharedPrefManager.getSpId();
        Service service = ApiClient.getRetrofitInstance().create(Service.class);
        Call<List<RiwayatItem>> call = service.getRiwayat(id_member);
        call.enqueue(new Callback<List<RiwayatItem>>() {
            @Override
            public void onResponse(Call<List<RiwayatItem>> call, Response<List<RiwayatItem>> response) {
                if(response.isSuccessful()) {
                    generateDataList(response.body());
                }else{
                    Toast.makeText(getApplicationContext(), "Mohon Cek Koneksi Internet anda", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<RiwayatItem>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("errorPaymentList",t.getMessage());
            }
        });


    }

    private void generateDataList(List<RiwayatItem> listTransaksi) {
        adapter = new ListRiwayatTransaksiAdapter(listTransaksi,getApplicationContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

//    public  void listTransaksi(){
//
//        transArrayList = new ArrayList<>();
//        for (int i = 0 ; i < 10;i++) {
//            transArrayList.add(new TransaksiItem("Pulsa XL", "29 September 2019", "081334367717", "+256"));
//        }
//
//        adapter = new ListPointAdapter(transArrayList);
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
//        recyclerView.setLayoutManager(layoutManager);
//        recyclerView.setAdapter(adapter);
//
//    }

}
