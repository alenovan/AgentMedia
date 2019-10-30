package com.example.agentmedia.activity.topup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.agentmedia.R;
import com.example.agentmedia.activity.login.LoginActivity;
import com.example.agentmedia.adapater.ListPaymentAdapter;
import com.example.agentmedia.adapater.ListRiwayatTopupAdapter;
import com.example.agentmedia.api.ApiClient;
import com.example.agentmedia.api.Service;
import com.example.agentmedia.api.SharedPrefManager;
import com.example.agentmedia.model.PaymentItem;
import com.example.agentmedia.model.topup.RiwayatTopupItem;
import com.example.agentmedia.tools.PublicTools;
import com.example.agentmedia.tools.RecyclerItemClickListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RiwayatTopupActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PublicTools tools;
    SharedPrefManager sharedPrefManager;
    private ListRiwayatTopupAdapter adapter;
    private List<RiwayatTopupItem> payArrayList = new ArrayList<>();
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
        sharedPrefManager = new SharedPrefManager(this);
        tools = new PublicTools(getApplicationContext());
        recyclerView = findViewById(R.id.list_topup);
        recyclerView.setNestedScrollingEnabled(false);
        listTransaksi();
    }



    public  void listTransaksi(){
        Service service = ApiClient.getRetrofitInstance().create(Service.class);
        String id_member = sharedPrefManager.getSpId();
        Call<List<RiwayatTopupItem>> call = service.getHistoryTopup(id_member);
        call.enqueue(new Callback<List<RiwayatTopupItem>>() {
            @Override
            public void onResponse(Call<List<RiwayatTopupItem>> call, Response<List<RiwayatTopupItem>>  response) {
                if(response.isSuccessful()) {
                    payArrayList = response.body();
                    generateDataList(payArrayList);
                }else{
                    Toast.makeText(getApplicationContext(), "Mohon Cek Koneksi Internet anda", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<RiwayatTopupItem>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.e("errorPaymentList",t.getMessage());
            }
        });

    }

    /*Method to generate List of data using RecyclerView with custom adapter*/
    private void generateDataList(List<RiwayatTopupItem> riwayatTopupItem) {
        adapter = new ListRiwayatTopupAdapter(riwayatTopupItem,getApplicationContext());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // do whatever

                        String statusTopup = payArrayList.get(position).getStatusTopup();
                        String id_topup = payArrayList.get(position).getIdTopup();
                        Intent i = new Intent(getApplicationContext(),TopupPaymentActivity.class);
                        i.putExtra("id_topup",id_topup);
                        i.putExtra("status",statusTopup);
                        startActivity(i);



                        }

                    @Override
                    public void onLongItemClick(View view, int position) {

                    }
                }

        ));

    }
}
