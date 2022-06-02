package com.asystenttrenera_frontend.physicalCheckup;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.asystenttrenera_frontend.R;

import java.text.ParseException;
import java.util.Date;

public class DeletePhysicalCheckupDialog extends AppCompatDialogFragment {
    private DeletePhysicalCheckupDialogListener listener;

    private Long id;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        //View view = inflater.inflate(R.layout.edit_physical_checkup_dialog, null);
        Bundle extras = getArguments();
        id = extras.getLong("checkup_id");

        builder.setMessage("")
                .setTitle("Czy usunąć badanie?")
                .setNegativeButton("Anuluj", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        listener.deleteTexts(id);
                    }
                });
        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (DeletePhysicalCheckupDialogListener) context;
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    public interface DeletePhysicalCheckupDialogListener {
        void deleteTexts(Long id);
    }
}
