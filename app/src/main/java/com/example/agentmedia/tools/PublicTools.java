package com.example.agentmedia.tools;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;

public class PublicTools {
    public final Context context;

    public PublicTools(Context context) {
        this.context = context;
    }

    public void functionIntentRelative(RelativeLayout view, final Class tujuan){
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,tujuan);
                context.startActivity(intent);
            }
        });

    }
}
