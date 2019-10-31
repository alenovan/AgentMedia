package com.example.agentmedia.fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agentmedia.R;
import com.example.agentmedia.activity.ListProvider;
import com.example.agentmedia.activity.ListrikActivity;
import com.example.agentmedia.activity.MainActivity;
import com.example.agentmedia.activity.RiwayatPointActivity;
import com.example.agentmedia.activity.topup.RiwayatTopupActivity;
import com.example.agentmedia.activity.RiwayatTransaksiActivity;
import com.example.agentmedia.activity.topup.TopupActivity;
import com.example.agentmedia.adapater.ListTransaksiAdapter;
import com.example.agentmedia.api.ApiClient;
import com.example.agentmedia.api.Service;
import com.example.agentmedia.api.SharedPrefManager;
import com.example.agentmedia.model.TransaksiItem;
import com.example.agentmedia.tools.PublicTools;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class DashboardFragment extends Fragment {
    private RecyclerView recyclerView;
    private PublicTools tools;
    TextView text_rincian,saldo;
    SharedPrefManager sharedPrefManager;
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
        saldo();
        return view;
    }

    public void declaration(View view){
        sharedPrefManager = new SharedPrefManager(getActivity());
        tools = new PublicTools(getActivity());
        relPulsa = view.findViewById(R.id.relPulsa);
        saldo = view.findViewById(R.id.saldo);
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

    public  void saldo(){
        Service service = ApiClient.getRetrofitInstance().create(Service.class);
        String id_member = sharedPrefManager.getSpId();
        service.getSaldoRequest(id_member).enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                String status = response.body().getAsJsonObject().get("result").toString();
                if (status.equals("true")){
                    JsonArray array = response.body().getAsJsonObject().getAsJsonArray("data");
                    Log.d("hasilDetail",array.toString());
                    for (int i = 0; i < array.size(); i++) {
                        JsonObject detail =  array.get(i).getAsJsonObject();
//                        saldo.setText(detail.get("saldo_member").toString().replace("\"", ""));
                        saldo.setText(tools.numberFormatWithoutRp(Integer.valueOf(detail.get("saldo_member").toString().replace("\"", ""))).toString());
                    }
                } else {
                    String error_message = status;
                    System.out.println(response.body().getAsJsonObject());
                    Toast.makeText(getActivity(), error_message, Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                Toast.makeText(getActivity(), "Mohon Maaf Layanan sedang gagguan", Toast.LENGTH_SHORT).show();
                Log.e("errorPaymentList",t.getMessage());
            }

        });
    }

}
