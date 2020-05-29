package com.example.transactionview;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class List_Adapter extends RecyclerView.Adapter<List_Adapter.TransViewHolder> {

    ArrayList<String> Txn;
    ArrayList<String> Amt;
    ArrayList<String> Status;
    ArrayList<String> Refrs;
    ArrayList<String> Cardtype;
    Context context;

    public List_Adapter(Context context, ArrayList<String> Txn, ArrayList<String> Amt,
                        ArrayList<String> Status,ArrayList<String> Refrs, ArrayList<String> Cardtype){
        this.context = context;
        this.Txn = Txn;
        this.Amt = Amt;
        this.Status = Status;
        this.Refrs = Refrs;
        this.Cardtype = Cardtype;
    }


    @Override
    public TransViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview,parent,false);
        TransViewHolder Tvh = new TransViewHolder(v);
        return Tvh;
    }

    @Override
    public void onBindViewHolder(List_Adapter.TransViewHolder holder, final int position) {

        holder.Txndata.setText(Txn.get(position));
        holder.Amttxt.setText(Amt.get(position));
        holder.Statustxt.setText(Status.get(position));
        holder.Reftxt.setText(Refrs.get(position));
        holder.Cardtxt.setText(Cardtype.get(position));

        String StatusCheck = Status.get(position);

        if(StatusCheck.equals("AUTHORIZED")){
            holder.AuthImg.setVisibility(View.VISIBLE);
            holder.Statustxt.setTextColor(ContextCompat.getColor(context,R.color.green));
        }else {
            holder.FailImg.setVisibility(View.VISIBLE);
            holder.Statustxt.setTextColor(Color.RED);
        }
        // implement setOnClickListener event on item view.
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // display a toast with color name on item click
                Toast.makeText(context, Status.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return Txn.size();
    }

    public class TransViewHolder extends RecyclerView.ViewHolder {

        TextView Txndata,Amttxt,Statustxt,Reftxt,Cardtxt;
        ImageView FailImg,AuthImg;

        public TransViewHolder(@NonNull View itemView) {
            super(itemView);

            Txndata = itemView.findViewById(R.id.taxdata);
            Amttxt = itemView.findViewById(R.id.amount);
            Statustxt = itemView.findViewById(R.id.status);
            Reftxt = itemView.findViewById(R.id.reference);
            Cardtxt = itemView.findViewById(R.id.cardtype);
            FailImg = itemView.findViewById(R.id.StatusFailImg);
            AuthImg = itemView.findViewById(R.id.StatusAuthImg);

        }
    }
}

