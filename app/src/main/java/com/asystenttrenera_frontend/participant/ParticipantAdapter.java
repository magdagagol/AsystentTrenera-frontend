package com.asystenttrenera_frontend.participant;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.asystenttrenera_frontend.MainActivity;
import com.asystenttrenera_frontend.R;
import com.asystenttrenera_frontend.zawodnik.ZawodnikAdapter;

import java.util.ArrayList;
import java.util.List;

public class ParticipantAdapter  extends RecyclerView.Adapter<ParticipantAdapter.ViewHolder> {
    private ArrayList<Participant> participants;
    ItemClicked activity;

    public interface ItemClicked {
        void onItemClicked(int participant);
    }

    public ParticipantAdapter(Context context, ArrayList<Participant> participants) {
        this.participants = participants;
        activity = (ItemClicked) context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView participantName;
        private TextView participantSurname;
        private TextView participantEmail;
        private TextView participantPhone;
        private TextView participantYearOfBirth;

        public ViewHolder(View view) {
            super(view);

            this.participantName = view.findViewById(R.id.participantName);
            this.participantSurname = view.findViewById(R.id.participantSurname);
            this.participantEmail = view.findViewById(R.id.participantEmail);
            this.participantPhone = view.findViewById(R.id.participantPhone);
            this.participantYearOfBirth = view.findViewById(R.id.participantYearOfBirth);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    activity.onItemClicked(participants.indexOf(view.getTag()));
                }
            });
        }

    }


    @NonNull
    @Override
    public ParticipantAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.participants_item, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ParticipantAdapter.ViewHolder holder, int i) {
        holder.itemView.setTag(participants.get(i));
        holder.participantName.setText(participants.get(i).getName());
        holder.participantSurname.setText(participants.get(i).getSurname());
        holder.participantEmail.setText(participants.get(i).getEmail());
        holder.participantPhone.setText(participants.get(i).getPhoneNumber());
        holder.participantYearOfBirth.setText(participants.get(i).getYearOfBirth());
    }

    @Override
    public int getItemCount() {
        return participants.size();
    }
}
