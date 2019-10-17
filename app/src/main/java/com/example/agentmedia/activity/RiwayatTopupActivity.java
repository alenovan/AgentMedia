package com.example.agentmedia.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.agentmedia.R;
import com.example.agentmedia.adapater.ListPaymentAdapter;
import com.example.agentmedia.adapater.ListProviderAdapter;
import com.example.agentmedia.adapater.ListRiwayatTopupAdapter;
import com.example.agentmedia.model.PaymentItem;
import com.example.agentmedia.model.ProviderItem;
import com.example.agentmedia.model.RiwayatTopupItem;
import com.example.agentmedia.tools.PublicTools;

import java.util.ArrayList;

public class RiwayatTopupActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PublicTools tools;
    private ListRiwayatTopupAdapter adapter;
    private ArrayList<RiwayatTopupItem> payArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat_topup);
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
        recyclerView = findViewById(R.id.list_topup);
        recyclerView.setNestedScrollingEnabled(false);
    }



    public  void listTransaksi(){

        payArrayList = new ArrayList<>();
        for (int i = 0 ; i < 10;i++) {
            payArrayList.add(new RiwayatTopupItem("1", "29 September 2019", "Rp.50.000", "Bank Central Asia","success"));
        }

        adapter = new ListRiwayatTopupAdapter(payArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }
}
