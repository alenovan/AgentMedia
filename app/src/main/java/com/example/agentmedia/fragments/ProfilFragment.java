package com.example.agentmedia.fragments;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.agentmedia.R;
import com.example.agentmedia.activity.MainActivity;
import com.example.agentmedia.activity.login.LoginActivity;
import com.example.agentmedia.api.ApiClient;
import com.example.agentmedia.api.Service;
import com.example.agentmedia.api.SharedPrefManager;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfilFragment extends Fragment {

    SharedPrefManager sharedPrefManager;
    Button cirLoginButton;
    TextView text_title,name,phone,email;
    public ProfilFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view= inflater.inflate(R.layout.fragment_profil, container, false);
        sharedPrefManager = new SharedPrefManager(getActivity());
        cirLoginButton = view.findViewById(R.id.cirLoginButton);
        text_title = view.findViewById(R.id.text_title);
        name = view.findViewById(R.id.name);
        phone = view.findViewById(R.id.phone);
        email = view.findViewById(R.id.email);
        saldo();
        cirLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
        return  view;
    }

    public void logout(){
        sharedPrefManager.saveSPString(SharedPrefManager.SP_ID, "");
        sharedPrefManager.saveSPString(SharedPrefManager.SP_NOHP, "");
        sharedPrefManager.saveSPBoolean(SharedPrefManager.SP_SUDAH_LOGIN, false);
        startActivity(new Intent(getActivity(), LoginActivity.class)
                .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
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
                        name.setText(detail.get("name_member").toString().replace("\"", "").toString());
                        phone.setText(detail.get("no_hp").toString().replace("\"", "").toString());
                        email.setText(detail.get("ktp_member").toString().replace("\"", "").toString());
                        text_title.setText("Hai "+detail.get("name_member").toString().replace("\"", "").toString());
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
