package com.asystenttrenera_frontend.participant;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.asystenttrenera_frontend.R;


public class EditParticipantDialog extends AppCompatDialogFragment {
    private EditText nameText;
    private EditText surnameText;
    private EditText birthText;
    private EditText emailText;
    private EditText phoneText;
    private EditParticipantDialogListener listener;
    private Participant participant;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.edit_participant_dialog, null);
        Bundle extras = getArguments();
        participant = extras.getParcelable("participant");

        builder.setView(view)
                .setTitle("Edytuj")
                .setNegativeButton("Anuluj", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Dodaj", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                       String name = nameText.getText().toString();
                       String surname = surnameText.getText().toString();
                       String birth = birthText.getText().toString();
                       String email = emailText.getText().toString();
                       String phone = phoneText.getText().toString();

                       listener.updateTexts(participant.getId(), name, surname, birth, email, phone);
                    }
                });
        nameText = view.findViewById(R.id.edit_participant_name);
        nameText.setText(participant.getName());

        surnameText = view.findViewById(R.id.edit_participant_surname);
        surnameText.setText(participant.getSurname());

        birthText = view.findViewById(R.id.edit_participant_birth);
        birthText.setText(participant.getYearOfBirth());

        emailText = view.findViewById(R.id.edit_participant_email);
        emailText.setText(participant.getEmail());

        phoneText = view.findViewById(R.id.edit_participant_phone);
        phoneText.setText(participant.getPhoneNumber());

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (EditParticipantDialogListener) context;
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public interface EditParticipantDialogListener {
        void updateTexts(Long id, String name, String surname, String birth, String email, String phone);
    }
}
