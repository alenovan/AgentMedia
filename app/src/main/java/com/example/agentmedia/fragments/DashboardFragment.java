package com.example.agentmedia.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.agentmedia.R;
import com.example.agentmedia.activity.ListProvider;
import com.example.agentmedia.activity.ListrikActivity;
import com.example.agentmedia.activity.RiwayatPointActivity;
import com.example.agentmedia.activity.RiwayatTopupActivity;
import com.example.agentmedia.activity.RiwayatTransaksiActivity;
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
    TextView text_rincian;
    private ListTransaksiAdapter adapter;
    private ArrayList<TransaksiItem> transArrayList;
    RelativeLayout relPulsa,relPaket,relListrik,relTopup,relRiwayatTopup,relRiwayatPoint;
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
        tools.functionIntentRelative(relRiwayatTopup, RiwayatTopupActivity.class,0);
        tools.functionIntentTextView(text_rincian, RiwayatTransaksiActivity.class);
        tools.functionIntentRelative(relRiwayatPoint, RiwayatPointActivity.class,0);
        listTransaksi();
        return view;
    }

    public void declaration(View view){
        tools = new PublicTools(getActivity());
        relPulsa = view.findViewById(R.id.relPulsa);
        text_rincian = view.findViewById(R.id.text_rincian);
        relPaket = view.findViewById(R.id.relPaket);
        relTopup = view.findViewById(R.id.relTopup);
        relRiwayatTopup = view.findViewById(R.id.relRiwayatTopup);
        relRiwayatPoint = view.findViewById(R.id.relRiwayatPoint);
        relListrik = view.findViewById(R.id.relListrik);
        recyclerView = view.findViewById(R.id.list_transaksi);
        recyclerView.setNestedScrollingEnabled(false);
    }



    public  void listTransaksi(){

        transArrayList = new ArrayList<>();
        for (int i = 0 ; i < 10;i++) {
            transArrayList.add(new TransaksiItem("Pulsa XL", "29 September 2019", "081334367717", "Success"));
        }

        adapter = new ListTransaksiAdapter(transArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }

}
