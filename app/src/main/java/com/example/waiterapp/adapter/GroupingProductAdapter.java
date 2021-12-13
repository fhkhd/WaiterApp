package com.example.waiterapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.waiterapp.R;
import com.example.waiterapp.model.Grouping;

import java.util.List;

public class GroupingProductAdapter extends RecyclerView.Adapter<GroupingProductAdapter.ViewHolder>{

    Context context;
    Grouping grouping;
    List<Grouping> groupingList;
    ProductAdapter productAdapter;
    Listener listener;

    public GroupingProductAdapter(Context context, List<Grouping> groupingList,Listener listener) {
        this.context = context;
        this.groupingList = groupingList;
        this.listener = listener;
    }

    public interface Listener{
        void onClick(int pos, Grouping category);
    }


    @Override
    public GroupingProductAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.grouping_product_info_list,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(GroupingProductAdapter.ViewHolder holder, int position) {
        grouping = groupingList.get(position);
        holder.grouping_product_name_tv.setText(grouping.name);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onClick(position,grouping);
            }
        });
    }

    @Override
    public int getItemCount() {
        return groupingList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView grouping_product_name_tv;
        public ViewHolder(View itemView) {
            super(itemView);
            grouping_product_name_tv = itemView.findViewById(R.id.grouping_product_name_tv);
        }
    }
}
