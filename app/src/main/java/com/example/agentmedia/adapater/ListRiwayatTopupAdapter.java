package com.example.agentmedia.adapater;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agentmedia.R;
import com.example.agentmedia.api.PublicStatic;
import com.example.agentmedia.model.topup.RiwayatTopupItem;
import com.example.agentmedia.tools.PublicTools;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ListRiwayatTopupAdapter extends RecyclerView.Adapter<ListRiwayatTopupAdapter.TransaksiViewHolder> {


    private List<RiwayatTopupItem> dataList;
    PublicTools publicTools;
    Context context;

    public ListRiwayatTopupAdapter(List<RiwayatTopupItem> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    @Override
    public TransaksiViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_item_riwayat_topup, parent, false);
        return new TransaksiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TransaksiViewHolder holder, int position) {
        publicTools = new PublicTools(context);
        holder.nominal.setText(publicTools.numberFormat(Integer.valueOf(dataList.get(position).getNominal()))+"");
        holder.date.setText(dataList.get(position).getCreateAt());
        holder.bank.setText(dataList.get(position).getNamePaymentMethod());
        Picasso.with(context).load(PublicStatic.path+"payment_method/"+dataList.get(position).getNamaFile()).into(holder.logo);
        String status =dataList.get(position).getStatusTopup();
        if(status.equals("1")){
            holder.status.setText("belum Konfirmasi");
            holder.frameStatus.getBackground().setColorFilter(Color.parseColor("#EB651C"), PorterDuff.Mode.SRC_ATOP);
        }else if(status.equals("2")){
            holder.status.setText("Proses");
        }else if(status.equals("3")) {
            holder.status.setText("Success");
            holder.frameStatus.getBackground().setColorFilter(Color.parseColor("#14a895"), PorterDuff.Mode.SRC_ATOP);
        }else if(status.equals("4")){
            holder.status.setText("Di tolak");
            holder.frameStatus.getBackground().setColorFilter(Color.parseColor("#d1395c"), PorterDuff.Mode.SRC_ATOP);
        }
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class TransaksiViewHolder extends RecyclerView.ViewHolder{
        private TextView nominal,date,bank,status;
        private FrameLayout frameStatus;
        private ImageView logo;
        public TransaksiViewHolder(View itemView) {
            super(itemView);
            nominal = (TextView) itemView.findViewById(R.id.nominal);
            date = (TextView) itemView.findViewById(R.id.date);
            bank = (TextView) itemView.findViewById(R.id.bank);
            status = (TextView) itemView.findViewById(R.id.status);
            frameStatus = itemView.findViewById(R.id.frameStatus);
            logo = (ImageView) itemView.findViewById(R.id.logo);
        }
    }
}
