package com.example.agentmedia.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Spinner;

import com.example.agentmedia.R;
import com.example.agentmedia.adapater.ListPriceAdapter;
import com.example.agentmedia.model.PriceItem;
import com.example.agentmedia.tools.PublicTools;

import java.util.ArrayList;

public class ListrikActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PublicTools tools;
    Spinner provider;
    private ListPriceAdapter adapter;
    private ArrayList<PriceItem> priceArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listrik);
        declaration();
        listTransaksi();
        functionProvider();
    }
    public void declaration(){
        tools = new PublicTools(getApplicationContext());
        recyclerView = findViewById(R.id.list_price);
        recyclerView.setNestedScrollingEnabled(false);
    }

    public void functionProvider(){

    }

    public  void listTransaksi(){

        priceArrayList = new ArrayList<>();
        for (int i = 0 ; i < 10;i++) {
            priceArrayList.add(new PriceItem(i, "5.000", "Harga Rp.5.000", "telkomsel"));
        }

        adapter = new ListPriceAdapter(priceArrayList);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        recyclerView.setAdapter(adapter);

    }

}
