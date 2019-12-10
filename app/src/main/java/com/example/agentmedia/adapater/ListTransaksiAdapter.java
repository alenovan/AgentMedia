package com.example.agentmedia.adapater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.agentmedia.R;
import com.example.agentmedia.model.TransaksiItem;

import java.util.ArrayList;

public class ListTransaksiAdapter extends RecyclerView.Adapter<ListTransaksiAdapter.TransaksiViewHolder> {


    private ArrayList<TransaksiItem> dataList;
    Context ctx;
    public ListTransaksiAdapter(ArrayList<TransaksiItem> dataList,Context ctx) {
        this.dataList = dataList;
        this.ctx = ctx;
    }

    @Override
    public TransaksiViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_list_transaction, parent, false);
        return new TransaksiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TransaksiViewHolder holder, int position) {
        holder.title.setText(dataList.get(position).getTitle());
        holder.date.setText(dataList.get(position).getDate());
        holder.tujuan.setText(dataList.get(position).getTujuan());
        holder.status.setText(dataList.get(position).getStatus());
//        Glide.with(ctx).load(image).into(holder.logo_layanan);
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class TransaksiViewHolder extends RecyclerView.ViewHolder{
        private TextView title,date,tujuan,status;
        ImageView logo_layanan;
        public TransaksiViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            date = (TextView) itemView.findViewById(R.id.date);
            tujuan = (TextView) itemView.findViewById(R.id.tujuan);
            status = (TextView) itemView.findViewById(R.id.status);
            logo_layanan = (ImageView) itemView.findViewById(R.id.logo_layanan);
        }
    }
}
