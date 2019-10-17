package com.example.agentmedia.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.agentmedia.R;
import com.example.agentmedia.adapater.ListPointAdapter;
import com.example.agentmedia.adapater.ListTransaksiAdapter;
import com.example.agentmedia.model.TransaksiItem;
import com.example.agentmedia.tools.PublicTools;

import java.util.ArrayList;

public class RiwayatPointActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PublicTools tools;
    private ListPointAdapter adapter;
    private ArrayList<TransaksiItem> transArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat_point);
        declaration();
        back();
        listTransaksi();
    }
    public void declaration(){
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

        transArrayList = new ArrayList<>();
        for (int i = 0 ; i < 10;i++) {
            transArrayList.add(new TransaksiItem("Pulsa XL", "29 September 2019", "081334367717", "+256"));
        }

        adapter = new ListPointAdapter(transArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }

}
