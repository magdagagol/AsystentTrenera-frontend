package com.asystenttrenera_frontend.group;

import android.content.Context;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.asystenttrenera_frontend.R;
import com.asystenttrenera_frontend.participant.Participant;

import java.util.ArrayList;

public class ParticipantForGroupAdapter extends RecyclerView.Adapter<ParticipantForGroupAdapter.ViewHolder> {
    private ArrayList<Participant> participants;

    public ParticipantForGroupAdapter(ArrayList<Participant> participants) {
        this.participants = participants;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView nameOfBirthParticInGroup, surnameOfBirthParticInGroup, yearOfBirthParticInGroup;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            nameOfBirthParticInGroup = itemView.findViewById(R.id.nameBirthParticInGroup);
            surnameOfBirthParticInGroup = itemView.findViewById(R.id.surnameOfBirthParticInGroup);
            yearOfBirthParticInGroup = itemView.findViewById(R.id.yearOfBirthParticInGroup);
        }
    }

    @NonNull
    @Override
    public ParticipantForGroupAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup part, int viewType) {
        View v = LayoutInflater.from(part.getContext()).inflate(R.layout.participant_for_group_item, part, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ParticipantForGroupAdapter.ViewHolder holder, int position) {
        holder.nameOfBirthParticInGroup.setText(participants.get(position).getName());
        holder.surnameOfBirthParticInGroup.setText(participants.get(position).getSurname());
        holder.yearOfBirthParticInGroup.setText(participants.get(position).getYearOfBirth());
    }

    @Override
    public int getItemCount() { return participants.size(); }
}
