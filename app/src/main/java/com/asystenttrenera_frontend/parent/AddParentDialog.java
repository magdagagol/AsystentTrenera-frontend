package com.asystenttrenera_frontend.parent;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.asystenttrenera_frontend.R;
import com.asystenttrenera_frontend.kyu.AddKyuDialog;
import com.google.android.material.textfield.TextInputLayout;

public class AddParentDialog extends AppCompatDialogFragment {
    private TextInputLayout addParentName;
    private TextInputLayout addParentSurname;
    private TextInputLayout addParentPhoneNumber;
    private TextInputLayout addParentEmail;

    private AddParentDialogListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.add_parent_dialog, null);

        builder.setView(view)
                .setTitle("Dodaj rodzica")
                .setNegativeButton("Anuluj", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Dodaj", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        String name = addParentName.getEditText().getText().toString();
                        String surname = addParentSurname.getEditText().getText().toString();
                        String phone = addParentPhoneNumber.getEditText().getText().toString();
                        String email = addParentEmail.getEditText().getText().toString();
                        Boolean agree = true;
                        listener.applyText(name, surname, phone, email, agree );
                    }
                });
        addParentName = view.findViewById(R.id.add_parent_name);
        addParentSurname = view.findViewById(R.id.add_paren_surname);
        addParentPhoneNumber = view.findViewById(R.id.add_parent_phone_number);
        addParentEmail = view.findViewById(R.id.add_parent_email);

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (AddParentDialogListener) context;
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public interface AddParentDialogListener {
        void applyText(String name, String surname, String phone, String email, Boolean agree );
    }
}
