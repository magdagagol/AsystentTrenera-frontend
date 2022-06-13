package com.asystenttrenera_frontend.parent;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.asystenttrenera_frontend.R;

public class EditParentDialog extends AppCompatDialogFragment {
    private Parent parent;
    private EditText edit_parent_name;
    private EditText edit_parent_surname;
    private EditText edit_parent_phone;
    private EditText edit_parent_email;
    private CheckBox edit_parent_contact;

    private EditParentDialogListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.edit_parent_dialog, null);
        Bundle extras = getArguments();
        parent = extras.getParcelable("parent");

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

                    String name = edit_parent_name.getText().toString();
                    String surname = edit_parent_surname.getText().toString();
                    String phoneNumber = edit_parent_phone.getText().toString();
                    String email = edit_parent_email.getText().toString();
                    Boolean contactAgree = edit_parent_contact.isChecked();

                        listener.updateTexts(parent.getId(), name, surname, phoneNumber, email, contactAgree);
                    }
                });
        edit_parent_name = view.findViewById(R.id.edit_parent_name);
        edit_parent_name.setText(parent.getName());
        edit_parent_surname = view.findViewById(R.id.edit_parent_surname);
        edit_parent_surname.setText(parent.getSurname());
        edit_parent_phone = view.findViewById(R.id.edit_parent_phone);
        edit_parent_phone.setText(parent.getPhoneNumber());
        edit_parent_email= view.findViewById(R.id.edit_parent_email);
        edit_parent_email.setText(parent.getEmail());
        edit_parent_contact = view.findViewById(R.id.edit_parent_contact);
        edit_parent_contact.setChecked(parent.getContactAgree());

        return builder.create();
    }

    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (EditParentDialogListener) getParentFragmentManager().findFragmentById(R.id.fragmentContainerView);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public interface EditParentDialogListener {
        void updateTexts(Long id, String name, String surname, String phoneNumber, String email, Boolean contactAgree);
    }
}
