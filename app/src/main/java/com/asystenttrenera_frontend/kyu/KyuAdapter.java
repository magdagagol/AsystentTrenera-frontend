package com.asystenttrenera_frontend.kyu;

import android.content.Context;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.asystenttrenera_frontend.R;
import com.asystenttrenera_frontend.physicalCheckup.PhysicalCheckupAdapter;

import java.util.ArrayList;

public class KyuAdapter extends RecyclerView.Adapter<KyuAdapter.ViewHolder> {
    private ArrayList<Kyu> kyuArrayList;
    ItemClicked activity;

    public interface ItemClicked {
        void onItemClicked(int item);
    }

    public KyuAdapter(Context context, ArrayList<Kyu> kyuArrayList) {
        this.kyuArrayList = kyuArrayList;
        activity = (ItemClicked) context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener{
        TextView examDate;
        TextView kyuDegree;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            examDate = itemView.findViewById(R.id.examDate);
            kyuDegree = itemView.findViewById(R.id.kyuDegree);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("message", "kyu adapter");
                }
            });
            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            activity.onItemClicked(kyuArrayList.indexOf(itemView.getTag()));
            contextMenu.add(this.getAbsoluteAdapterPosition(), 121, 0, "Usuń");
            contextMenu.add(this.getAbsoluteAdapterPosition(), 122, 0, "Edytuj");
        }
    }

    @NonNull
    @Override
    public KyuAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.kyu_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull KyuAdapter.ViewHolder holder, int position) {
        holder.itemView.setTag(kyuArrayList.get(position));
        holder.examDate.setText(kyuArrayList.get(position).getExamDate().toString());
        holder.kyuDegree.setText(kyuArrayList.get(position).getKyuDegree());
    }

    @Override
    public int getItemCount() {
        return kyuArrayList.size();
    }
}
