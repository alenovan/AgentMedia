package com.example.agentmedia.adapater;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.agentmedia.R;
import com.example.agentmedia.activity.ListProvider;
import com.example.agentmedia.activity.PaketActivity;
import com.example.agentmedia.activity.PulsaActivity;
import com.example.agentmedia.model.ProviderItem;
import com.example.agentmedia.model.TransaksiItem;
import com.example.agentmedia.tools.PublicTools;

import java.util.ArrayList;

public class ListProviderAdapter extends RecyclerView.Adapter<ListProviderAdapter.TransaksiViewHolder> {

    private PublicTools tools;
    private ArrayList<ProviderItem> dataList;
    public Context mContext ;
    public ListProviderAdapter(Context context,ArrayList<ProviderItem> dataList) {
        this.dataList = dataList;
        mContext  = context;
    }

    @Override
    public TransaksiViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_item_provider, parent, false);
        return new TransaksiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final TransaksiViewHolder holder, final int position) {
        tools = new PublicTools(mContext);
        holder.title.setText(dataList.get(position).getTitle());

        holder.provider_row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(dataList.get(position).getStatus_theme() == 1){
                    Intent intent = new Intent(mContext, PulsaActivity.class);
                    mContext.startActivity(intent);
                }else{
                    Intent intent = new Intent(mContext, PaketActivity.class);
                    mContext.startActivity(intent);

                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class TransaksiViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private LinearLayout provider_row;

        public TransaksiViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            provider_row = (LinearLayout) itemView.findViewById(R.id.provider_row);
        }
    }
}
