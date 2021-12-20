package com.asystenttrenera_frontend.group;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.asystenttrenera_frontend.R;

import java.util.ArrayList;

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.ViewHolder> {
    private ArrayList<Group> groupArrayList;

    ItemClicked activity;
    public interface ItemClicked{
        void onItemClicked(int index);
    }

    public  GroupAdapter(Context context, ArrayList<Group> groupArrayList) {
        this.groupArrayList = groupArrayList;
        activity = (ItemClicked) context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView groupName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            groupName = itemView.findViewById(R.id.groupName);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    activity.onItemClicked(groupArrayList.indexOf((Group) view.getTag()));
                }
            });
        }
    }

    @NonNull
    @Override
    public GroupAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup group, int viewType) {
        View v = LayoutInflater.from(group.getContext()).inflate(R.layout.groups_item, group, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupAdapter.ViewHolder holder, int position) {
        holder.itemView.setTag(groupArrayList.get(position));
        holder.groupName.setText(groupArrayList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return groupArrayList.size();
    }
}
