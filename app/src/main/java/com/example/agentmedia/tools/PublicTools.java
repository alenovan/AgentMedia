package com.example.agentmedia.tools;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.example.agentmedia.R;

import org.w3c.dom.Text;

import java.text.NumberFormat;
import java.util.Locale;

public class PublicTools {
    public final Context context;
    Dialog customDialog;
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


    public String numberFormatWithoutRp(int number){
        Locale localeID = new Locale("in", "ID");
        NumberFormat formatRupiah = NumberFormat.getInstance(localeID);
        return formatRupiah.format(number);
    }

    public void textDialog(View v,String title,String message) {
        customDialog = new Dialog(v.getContext());
        customDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        customDialog.setContentView(R.layout.dialog_message);
        customDialog.setCancelable(true);
        customDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        TextView titleText = customDialog.findViewById(R.id.title);
        TextView messageText = customDialog.findViewById(R.id.message);
        TextView btn_close = customDialog.findViewById(R.id.btn_close);
        titleText.setText(title);
        messageText.setText(message);
        btn_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDialog.dismiss();
            }
        });
        customDialog.show();
    }


}
