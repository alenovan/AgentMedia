package com.example.agentmedia.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.example.agentmedia.R;
import com.example.agentmedia.activity.topup.RiwayatTopupActivity;

public class SuccessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);
        final Handler handler = new Handler();
        try {
           if(getIntent().getStringExtra("code").equals("topup")){
               TextView text = findViewById(R.id.text);
               text.setText("Selamat \n Pesanan sedang di proses");
               handler.postDelayed(new Runnable() {
                   @Override
                   public void run() {
                       startActivity(new Intent(getApplicationContext(), RiwayatTopupActivity.class));
                       finish();
                   }
               }, 3500L); //3000 L = 3 detik
           }else{
               TextView text = findViewById(R.id.text);
               text.setText("Selamat \n Pesanan sedang di proses");
               handler.postDelayed(new Runnable() {
                   @Override
                   public void run() {

                       startActivity(new Intent(getApplicationContext(), MainActivity.class));
                       finish();
                   }
               }, 2500L); //3000 L = 3 detik
           }
        }catch (Exception e){
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }
            }, 2500L); //3000 L = 3 detik
        }


    }
}
