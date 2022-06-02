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

public class EditPhysicalCheckupDialog extends AppCompatDialogFragment {
    private EditText edit_physical_checkup_data;
    private EditText edit_height;
    private EditText edit_weight;
    private EditText edit_comment;
    private EditPhysicalCheckupDialogListener listener;

    private PhysicalCheckup physicalCheckup;



    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.edit_physical_checkup_dialog, null);
        Bundle extras = getArguments();
        physicalCheckup = extras.getParcelable("checkup");

        Log.i("aaaa", physicalCheckup.toString());

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
                        String dateString = edit_physical_checkup_data.getText().toString();
                        Date date = null;
                        try {
                            date = new SimpleDateFormat("dd/MM/yyyy").parse(dateString);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        Double height  = Double.parseDouble(edit_height.getText().toString());
                        Double weight = Double.parseDouble(edit_weight.getText().toString());
                        String comment = edit_comment.getText().toString();

                        listener.updateTexts(physicalCheckup.getId(), date, height, weight, comment);
                    }
                });
        edit_physical_checkup_data = view.findViewById(R.id.edit_physical_checkup_data);
        Date dateT = physicalCheckup.getPhysicalCheckupData();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String date = df.format(dateT);

        edit_physical_checkup_data.setText(date);

        edit_height = view.findViewById(R.id.edit_height);
        edit_height.setText(physicalCheckup.getHeight().toString());

        edit_weight = view.findViewById(R.id.edit_weight);
        edit_weight.setText(physicalCheckup.getWeight().toString());

        edit_comment = view.findViewById(R.id.edit_comment);
        edit_comment.setText(physicalCheckup.getComment());

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (EditPhysicalCheckupDialogListener) context;
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    public interface EditPhysicalCheckupDialogListener {
        void updateTexts(Long id, Date data, Double height, Double weight, String comment);
    }
}
