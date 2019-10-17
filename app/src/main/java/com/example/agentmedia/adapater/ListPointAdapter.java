package com.example.agentmedia.adapater;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.agentmedia.R;
import com.example.agentmedia.model.TransaksiItem;

import java.util.ArrayList;

public class ListPointAdapter extends RecyclerView.Adapter<ListPointAdapter.TransaksiViewHolder> {


    private ArrayList<TransaksiItem> dataList;

    public ListPointAdapter(ArrayList<TransaksiItem> dataList) {
        this.dataList = dataList;
    }

    @Override
    public TransaksiViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_list_transaction, parent, false);
        return new TransaksiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListPointAdapter.TransaksiViewHolder holder, int position) {
        holder.title.setText(dataList.get(position).getTitle());
        holder.date.setText(dataList.get(position).getDate());
        holder.tujuan.setText(dataList.get(position).getTujuan());
        holder.status.setText(dataList.get(position).getStatus());
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class TransaksiViewHolder extends RecyclerView.ViewHolder {
        private TextView title, date, tujuan, status;

        public TransaksiViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            date = (TextView) itemView.findViewById(R.id.date);
            tujuan = (TextView) itemView.findViewById(R.id.tujuan);
            status = (TextView) itemView.findViewById(R.id.status);
        }
    }
}
