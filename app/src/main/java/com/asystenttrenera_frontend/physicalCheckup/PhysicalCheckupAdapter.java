package com.asystenttrenera_frontend.physicalCheckup;

import android.content.Context;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.asystenttrenera_frontend.R;
import com.asystenttrenera_frontend.participant.ParticipantAdapter;

import java.util.ArrayList;

public class PhysicalCheckupAdapter extends RecyclerView.Adapter<PhysicalCheckupAdapter.ViewHolder> {
    private ArrayList<PhysicalCheckup> physicalCheckupArrayList;
    ItemClicked activity;

    public interface ItemClicked {
        void onItemClicked(int item);
    }

    public PhysicalCheckupAdapter(Context context, ArrayList<PhysicalCheckup> physicalCheckupArrayList) {
        this.physicalCheckupArrayList = physicalCheckupArrayList;
        activity = (ItemClicked) context;
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
        TextView physicalCheckupData;
        TextView height;
        TextView weight;
        TextView comment;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            physicalCheckupData = itemView.findViewById(R.id.physicalCheckupDat);
            height = itemView.findViewById(R.id.height);
            weight = itemView.findViewById(R.id.weight);
            comment = itemView.findViewById(R.id.comment);

            //cardView = itemView.findViewById(R.id.physical_checkup_cv);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //activity.onItemClicked(physicalCheckupArrayList.indexOf(view.getTag()));
                }
            });
            itemView.setOnCreateContextMenuListener(this);

        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            activity.onItemClicked(physicalCheckupArrayList.indexOf(itemView.getTag()));
            contextMenu.add(this.getAbsoluteAdapterPosition(), 121, 0, "Usuń");
            contextMenu.add(this.getAbsoluteAdapterPosition(), 122, 0, "Edytuj");
        }
    }

    @NonNull
    @Override
    public PhysicalCheckupAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.physical_checkups_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhysicalCheckupAdapter.ViewHolder holder, int position) {
        holder.itemView.setTag(physicalCheckupArrayList.get(position));
        holder.physicalCheckupData.setText(physicalCheckupArrayList.get(position).getPhysicalCheckupData().toString());
        holder.height.setText(physicalCheckupArrayList.get(position).getHeight().toString());
        holder.weight.setText(physicalCheckupArrayList.get(position).getWeight().toString());
        holder.comment.setText(physicalCheckupArrayList.get(position).getComment());
    }

    @Override
    public int getItemCount() {
        return physicalCheckupArrayList.size();
    }
}
