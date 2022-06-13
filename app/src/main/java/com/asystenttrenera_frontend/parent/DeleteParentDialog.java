package com.asystenttrenera_frontend.parent;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.asystenttrenera_frontend.R;


public class DeleteParentDialog extends AppCompatDialogFragment {
    private DeleteParentDialogListener listener;
    private Long id;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        Bundle extras = this.getArguments();
        id = extras.getLong("parent_id");

        builder.setMessage("")
                .setTitle("Czy usunąć Parent?")
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
            listener = (DeleteParentDialogListener) getParentFragmentManager().findFragmentById(R.id.fragmentContainerView);
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    public interface DeleteParentDialogListener {
        void deleteTexts(Long id);
    }
}
