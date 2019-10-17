package com.example.agentmedia.adapater;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.agentmedia.R;
import com.example.agentmedia.model.PaymentItem;
import com.example.agentmedia.model.RiwayatTopupItem;
import com.example.agentmedia.model.TransaksiItem;

import java.util.ArrayList;

public class ListRiwayatTopupAdapter extends RecyclerView.Adapter<ListRiwayatTopupAdapter.TransaksiViewHolder> {


    private ArrayList<RiwayatTopupItem> dataList;

    public ListRiwayatTopupAdapter(ArrayList<RiwayatTopupItem> dataList) {
        this.dataList = dataList;
    }

    @Override
    public TransaksiViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_item_riwayat_topup, parent, false);
        return new TransaksiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TransaksiViewHolder holder, int position) {
        holder.nominal.setText(dataList.get(position).getNominal());
        holder.date.setText(dataList.get(position).getDate());
        holder.bank.setText(dataList.get(position).getBank());
        holder.status.setText(dataList.get(position).getStatus());
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class TransaksiViewHolder extends RecyclerView.ViewHolder{
        private TextView nominal,date,bank,status;

        public TransaksiViewHolder(View itemView) {
            super(itemView);
            nominal = (TextView) itemView.findViewById(R.id.nominal);
            date = (TextView) itemView.findViewById(R.id.date);
            bank = (TextView) itemView.findViewById(R.id.bank);
            status = (TextView) itemView.findViewById(R.id.status);
        }
    }
}
