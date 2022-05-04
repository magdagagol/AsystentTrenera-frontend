package com.asystenttrenera_frontend.message;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.asystenttrenera_frontend.R;
import com.asystenttrenera_frontend.participant.Participant;

import java.util.ArrayList;
import java.util.List;

public class ParticipantAdapter extends ArrayAdapter<Participant> {

    LayoutInflater layoutInflater;

    public ParticipantAdapter(@NonNull Context context, int resource, ArrayList<Participant> participants) {
        super(context, resource, participants);
        layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View rowView = layoutInflater.inflate(R.layout.custom_spinner_participant_adapter, null, true);
        Participant participant = getItem(position);
        TextView name = (TextView) rowView.findViewById(R.id.participantAdapterName);
        TextView surname = (TextView) rowView.findViewById(R.id.participantAdapterSurname);

        name.setText(participant.getName());
        surname.setText(participant.getSurname());

        return rowView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.custom_spinner_participant_adapter, parent, false);
        }
        Participant participant = getItem(position);
        TextView name = (TextView) convertView.findViewById(R.id.participantAdapterName);
        TextView surname = (TextView) convertView.findViewById(R.id.participantAdapterSurname);

        name.setText(participant.getName());
        surname.setText(participant.getSurname());

        return convertView;
    }
}
