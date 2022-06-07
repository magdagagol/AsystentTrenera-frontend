package com.asystenttrenera_frontend.kyu;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;


public class DeleteKyuDialog extends AppCompatDialogFragment {
    private DeleteKyuDialogListener listener;
    private Long id;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        Bundle extras = getArguments();
        id = extras.getLong("kyu_id");

        builder.setMessage("")
                .setTitle("Czy usunąć kyu?")
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
            listener = (DeleteKyuDialogListener) context;
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    public interface DeleteKyuDialogListener {
        void deleteTexts(Long id);
    }
}
