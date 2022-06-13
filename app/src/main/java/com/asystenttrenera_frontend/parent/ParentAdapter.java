package com.asystenttrenera_frontend.parent;

import android.app.Activity;
import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.asystenttrenera_frontend.R;
import com.asystenttrenera_frontend.physicalCheckup.PhysicalCheckupAdapter;

import java.util.ArrayList;

public class ParentAdapter extends RecyclerView.Adapter<ParentAdapter.ViewHolder> {
    private ArrayList<Parent> parents;
    ItemClicked activity;

    public interface ItemClicked {
        void onItemClicked(int item);
    }


    public ParentAdapter(Fragment context, ArrayList<Parent> parents) {
        this.parents = parents;
        activity = (ItemClicked) context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        private TextView parentName;
        private TextView parentSurname;
        private TextView parentEmail;
        private TextView parentPhone;
        private TextView parentContactAgree;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            this.parentName = itemView.findViewById(R.id.parentName);
            this.parentSurname = itemView.findViewById(R.id.parentSurname);
            this.parentEmail = itemView.findViewById(R.id.parentEmail);
            this.parentPhone = itemView.findViewById(R.id.parentPhoneNumber);
            this.parentContactAgree = itemView.findViewById(R.id.parentContactAgree);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            activity.onItemClicked(parents.indexOf(itemView.getTag()));
            contextMenu.add(this.getAbsoluteAdapterPosition(), 121, 0, "Usuń");
            contextMenu.add(this.getAbsoluteAdapterPosition(), 122, 0, "Edytuj");
        }
    }

    @NonNull
    @Override
    public ParentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.parent_item, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ParentAdapter.ViewHolder holder, int position) {
        holder.itemView.setTag(parents.get(position));

        holder.parentName.setText(parents.get(position).getName());
        holder.parentSurname.setText(parents.get(position).getSurname());
        holder.parentEmail.setText(parents.get(position).getEmail());
        holder.parentPhone.setText(parents.get(position).getPhoneNumber());
        if(parents.get(position).getContactAgree() == true){
            holder.parentContactAgree.setText("tak");
        } else holder.parentContactAgree.setText("nie");


    }

    @Override
    public int getItemCount() {
        return parents.size();
    }
}
