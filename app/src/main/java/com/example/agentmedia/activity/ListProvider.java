package com.example.agentmedia.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.agentmedia.R;
import com.example.agentmedia.adapater.ListProviderAdapter;
import com.example.agentmedia.adapater.ListTransaksiAdapter;
import com.example.agentmedia.model.ProviderItem;
import com.example.agentmedia.model.TransaksiItem;
import com.example.agentmedia.tools.PublicTools;

import java.util.ArrayList;

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
        listTransaksi();
    }

    public void declaration(){
        tools = new PublicTools(getApplicationContext());
        recyclerView = findViewById(R.id.list_provider);
        recyclerView.setNestedScrollingEnabled(false);
    }



    public  void listTransaksi(){

        provArrayList = new ArrayList<>();
        for (int i = 0 ; i < 3;i++) {
            provArrayList.add(new ProviderItem(1, status,"Simpati"+i));
        }

        adapter = new ListProviderAdapter(getApplicationContext(),provArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }
}
