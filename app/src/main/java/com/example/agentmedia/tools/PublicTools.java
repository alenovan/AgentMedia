package com.example.agentmedia.tools;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.Locale;

public class PublicTools {
    public final Context context;

    public PublicTools(Context context) {
        this.context = context;
    }

    public void functionIntentRelative(RelativeLayout view, final Class tujuan, final int variable){
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,tujuan);
                intent.putExtra("theme",variable);
                context.startActivity(intent);
            }
        });

    }
    public void functionIntentLinear(LinearLayout view, final Class tujuan){
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,tujuan);
                context.startActivity(intent);
            }
        });

    }
    public void functionIntentTextView(TextView view, final Class tujuan){
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,tujuan);
                context.startActivity(intent);
            }
        });

    }

    public String numberFormat(int number){
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getCurrencyInstance(localeID);
        return formatRupiah.format(number);
    }

}
