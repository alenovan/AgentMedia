package com.example.agentmedia.fragments;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.agentmedia.R;
import com.example.agentmedia.activity.ListProvider;
import com.example.agentmedia.activity.ListrikActivity;
import com.example.agentmedia.activity.PaketActivity;
import com.example.agentmedia.activity.PulsaActivity;
import com.example.agentmedia.activity.TopupActivity;
import com.example.agentmedia.adapater.ListTransaksiAdapter;
import com.example.agentmedia.model.TransaksiItem;
import com.example.agentmedia.tools.PublicTools;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends Fragment {
    private RecyclerView recyclerView;
    private PublicTools tools;
    private ListTransaksiAdapter adapter;
    private ArrayList<TransaksiItem> transArrayList;
    RelativeLayout relPulsa,relPaket,relListrik,relTopup;
    public DashboardFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        declaration(view);
        tools.functionIntentRelative(relPulsa, ListProvider.class,1);
        tools.functionIntentRelative(relPaket, ListProvider.class,2);
        tools.functionIntentRelative(relListrik, ListrikActivity.class,0);
        tools.functionIntentRelative(relTopup, TopupActivity.class,0);
        listTransaksi();
        return view;
    }

    public void declaration(View view){
        tools = new PublicTools(getActivity());
        relPulsa = view.findViewById(R.id.relPulsa);
        relPaket = view.findViewById(R.id.relPaket);
        relTopup = view.findViewById(R.id.relTopup);
        relListrik = view.findViewById(R.id.relListrik);
        recyclerView = view.findViewById(R.id.list_transaksi);
        recyclerView.setNestedScrollingEnabled(false);
    }



    public  void listTransaksi(){

        transArrayList = new ArrayList<>();
        for (int i = 0 ; i < 10;i++) {
            transArrayList.add(new TransaksiItem("Pulsa Simpati", "29 September 2019", "081334367717", "Success"));
        }

        adapter = new ListTransaksiAdapter(transArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }

}
