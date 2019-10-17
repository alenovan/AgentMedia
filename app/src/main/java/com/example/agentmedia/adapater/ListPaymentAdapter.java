package com.example.agentmedia.adapater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.agentmedia.R;
import com.example.agentmedia.api.PublicStatic;
import com.example.agentmedia.model.PaymentItem;
import com.example.agentmedia.model.TransaksiItem;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ListPaymentAdapter extends RecyclerView.Adapter<ListPaymentAdapter.TransaksiViewHolder> {


    private List<PaymentItem> dataList;
    Context mContext;
    public ListPaymentAdapter(List<PaymentItem> dataList, Context context) {
        this.dataList = dataList;
        this.mContext = context;
    }

    @Override
    public TransaksiViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_item_payment_account, parent, false);
        return new TransaksiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TransaksiViewHolder holder, int position) {
        holder.code.setText(dataList.get(position).getCodePaymentMethod());
        holder.name.setText(dataList.get(position).getNamePaymentMethod());;
        Picasso.with(mContext).load(PublicStatic.path+"payment_method/"+dataList.get(position).getNamaFile()).into(holder.logo);
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public class TransaksiViewHolder extends RecyclerView.ViewHolder{
        private TextView code,name;
        private ImageView logo;
        public TransaksiViewHolder(View itemView) {
            super(itemView);
            code = (TextView) itemView.findViewById(R.id.code);
            logo = (ImageView) itemView.findViewById(R.id.logo);
            name = (TextView) itemView.findViewById(R.id.name);
        }
    }
}
