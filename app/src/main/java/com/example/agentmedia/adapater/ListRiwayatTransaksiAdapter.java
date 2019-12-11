package com.example.agentmedia.adapater;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.agentmedia.R;
import com.example.agentmedia.api.PublicStatic;
import com.example.agentmedia.model.RiwayatItem;
import com.example.agentmedia.model.TransaksiItem;
import com.example.agentmedia.tools.PublicTools;

import java.util.ArrayList;
import java.util.List;


public class ListRiwayatTransaksiAdapter extends RecyclerView.Adapter<ListRiwayatTransaksiAdapter.TransaksiViewHolder> {


    private List<RiwayatItem> dataList;
    Context ctx;
    private PublicTools tools;
    public ListRiwayatTransaksiAdapter(List<RiwayatItem> dataList, Context ctx) {
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
        tools = new PublicTools(ctx);
        holder.title.setText(dataList.get(position).getNameProductType()+" "+dataList.get(position).getNameProduct() +"("+tools.numberFormat(Integer.parseInt(dataList.get(position).getPriceBuy()))+")");
        holder.date.setText(dataList.get(position).getCreatedAt());
        holder.tujuan.setText(dataList.get(position).getTujuan());
        String status = dataList.get(position).getStatus();
        if(status.equals("1")){
            holder.status.setText("Pending");
            holder.frame_layout.getBackground().setColorFilter(Color.parseColor("#EB651C"), PorterDuff.Mode.SRC_ATOP);
        }else if(status.equals("2")){
            holder.status.setText("Proses");
        }else if(status.equals("20")) {
            holder.status.setText("Success");
            holder.frame_layout.getBackground().setColorFilter(Color.parseColor("#14a895"), PorterDuff.Mode.SRC_ATOP);
        }else if(status.equals("40")){
            holder.status.setText("Di tolak");
            holder.frame_layout.getBackground().setColorFilter(Color.parseColor("#d1395c"), PorterDuff.Mode.SRC_ATOP);
        }else{
            holder.status.setText("Proses");
        }
        Glide.with(ctx).load(PublicStatic.path+"type/"+dataList.get(position).getNamaFile().toString()).into(holder.logo_layanan);
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class TransaksiViewHolder extends RecyclerView.ViewHolder{
        private TextView title,date,tujuan,status;
        ImageView logo_layanan;
        FrameLayout frame_layout;
        public TransaksiViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            date = (TextView) itemView.findViewById(R.id.date);
            tujuan = (TextView) itemView.findViewById(R.id.tujuan);
            status = (TextView) itemView.findViewById(R.id.status);
            frame_layout = (FrameLayout) itemView.findViewById(R.id.frame_layout);
            logo_layanan = (ImageView) itemView.findViewById(R.id.logo_layanan);
        }
    }
}

