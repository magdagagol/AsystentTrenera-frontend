package com.asystenttrenera_frontend.attendance;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.asystenttrenera_frontend.R;
import com.asystenttrenera_frontend.participant.Participant;

import java.util.ArrayList;

public class CheckBoxAdapter extends RecyclerView.Adapter<CheckBoxAdapter.ViewHolder> {
    View view;
    Context context;
    ArrayList<Participant> participants;
    private ArrayList<Participant> arrayListChecked = new ArrayList<>();
    private OnCheckedInfoListener onCheckedInfoListener;

    public CheckBoxAdapter(Context context, ArrayList<Participant> participants) {
        this.context = context;
        this.participants = participants;
        System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&7" + participants);
        this.onCheckedInfoListener = (OnCheckedInfoListener) context;
       // try {
       //
       // } catch (ClassCastException e) {
       //     throw new ClassCastException(e.getMessage());
       // }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.checkbox_attendance_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.checkBox.setText(participants.get(position).getName() + " " + participants.get(position).getSurname());
        //holder.checkBox.setText("aaaaaaaaaaaaaaaaaa");
        System.out.println("##################################################");

        if (participants != null && participants.size() > 0) {
            holder.checkBox.setText(participants.get(position).getName() + " " + participants.get(position).getSurname());
            holder.checkBox.setChecked(false);
           // if(participants.get(position).getGroup() != null) {
           //     holder.checkBox.setChecked(true);
           //     Log.i("checked", participants.get(position).toString());
           //     arrayListChecked.add(participants.get(position));
           // } else {
           //
           // }

            holder.checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (holder.checkBox.isChecked()) {
                        arrayListChecked.add(participants.get(position));
                    } else {
                        arrayListChecked.remove(participants.get(position));
                    }

                    Intent intent = new Intent();
                    intent.putExtra("participants", arrayListChecked);
                    onCheckedInfoListener.onCheckedInfoListener(intent);
                    Log.d("participants array from adapter", arrayListChecked.toString());
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return participants.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBox;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            checkBox = itemView.findViewById(R.id.checkBox2);
        }
    }
    public interface OnCheckedInfoListener{
        public ArrayList<Participant> onCheckedInfoListener(Intent intent);
    }
}