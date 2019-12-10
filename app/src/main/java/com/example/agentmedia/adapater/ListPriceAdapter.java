package com.example.agentmedia.adapater;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agentmedia.R;
import com.example.agentmedia.activity.PaketActivity;
import com.example.agentmedia.activity.PulsaActivity;
import com.example.agentmedia.model.PriceItem;
import com.example.agentmedia.model.TransaksiItem;
import com.example.agentmedia.tools.PublicTools;

import java.util.ArrayList;
import java.util.List;

public class ListPriceAdapter extends RecyclerView.Adapter<ListPriceAdapter.PriceViewHolder> {
    Integer row_index = -1;
    Context context;
    Integer status;
    private List<PriceItem> dataList;
    PublicTools publicTools;

    public ListPriceAdapter(List<PriceItem> dataList, Context context,Integer status) {
        this.dataList = dataList;
        this.context = context;
        this.status = status;
    }

    @Override
    public PriceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_item_price, parent, false);
        return new PriceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PriceViewHolder holder, final int position) {
        publicTools = new PublicTools(context);

        if(status == 1){
            holder.tag_price.setText(publicTools.numberFormat(Integer.valueOf(dataList.get(position).getNameProduct()))+"");
        }else{
            holder.tag_price.setText(dataList.get(position).getNameProduct().toString());
        }
        holder.price_sell.setText("Harga :"+publicTools.numberFormat(Integer.valueOf(dataList.get(position).getPriceSell()))+"");

        holder.row_list_price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                row_index=position;
                notifyDataSetChanged();
            }
        });
        if(row_index==position){
            holder.row_list_price.setBackgroundColor(Color.parseColor("#01bcf1"));
            holder.tag_price.setTextColor(Color.parseColor("#FFFFFF"));
            holder.price_sell.setTextColor(Color.parseColor("#FFFFFF"));
            Intent intent = new Intent("custom-message");
            intent.putExtra("price_sell",dataList.get(position).getPriceSell());
            intent.putExtra("id_product",dataList.get(position).getIdProduct());
            LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
        }else{
            holder.row_list_price.setBackgroundResource(R.drawable.price_rounded);
            holder.tag_price.setTextColor(Color.parseColor("#000000"));
            holder.price_sell.setTextColor(Color.parseColor("#000000"));
        }
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class PriceViewHolder extends RecyclerView.ViewHolder {
        private TextView tag_price, price_sell;
        private LinearLayout row_list_price;
        public PriceViewHolder(View itemView) {
            super(itemView);
            tag_price = (TextView) itemView.findViewById(R.id.tag_price);
            row_list_price = (LinearLayout) itemView.findViewById(R.id.row_list_price);
            price_sell = (TextView) itemView.findViewById(R.id.sell_price);

        }
    }
}
