package com.example.agentmedia.adapater;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.agentmedia.R;
import com.example.agentmedia.model.PriceItem;
import com.example.agentmedia.model.TransaksiItem;

import java.util.ArrayList;

public class ListPriceAdapter extends RecyclerView.Adapter<ListPriceAdapter.PriceViewHolder> {


    private ArrayList<PriceItem> dataList;

    public ListPriceAdapter(ArrayList<PriceItem> dataList) {
        this.dataList = dataList;
    }

    @Override
    public PriceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_item_price, parent, false);
        return new PriceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PriceViewHolder holder, int position) {
        holder.tag_price.setText(dataList.get(position).getTag_price());
        holder.price_sell.setText(dataList.get(position).getPrice_sell());
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class PriceViewHolder extends RecyclerView.ViewHolder {
        private TextView tag_price, price_sell;

        public PriceViewHolder(View itemView) {
            super(itemView);
            tag_price = (TextView) itemView.findViewById(R.id.tag_price);
            price_sell = (TextView) itemView.findViewById(R.id.sell_price);
        }
    }
}
