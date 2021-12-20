package com.asystenttrenera_frontend.group;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatDialogFragment;

import com.asystenttrenera_frontend.R;

public class AddGroupDialog extends AppCompatDialogFragment {
    private EditText groupName;
    private AddGroupDialogListener addGroupDialogListener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View view = layoutInflater.inflate(R.layout.layout_add_group_dialog, null);

        builder.setView(view)
                .setTitle("Dodaj grupę")
                .setNegativeButton("Anuluj", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String getGroupName = groupName.getText().toString();
                        addGroupDialogListener.applyText(getGroupName);
                    }
                });
        groupName = view.findViewById(R.id.addGroupBtn);
        // Create the AlertDialog object and return it
        return builder.create();
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        try{
            addGroupDialogListener = (AddGroupDialogListener) context;
        } catch (ClassCastException e){
            throw new ClassCastException(context.toString() + "must implement AddGroupDialogListener");
        }

    }

    public interface AddGroupDialogListener {
        void applyText(String groupName);
    }

}
