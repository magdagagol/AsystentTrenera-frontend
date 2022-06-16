package com.asystenttrenera_frontend.physicalCheckup;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
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

public class AddPhysicalCheckupDialog extends AppCompatDialogFragment {
    private EditText add_physical_checkup_data;
    private EditText add_height;
    private EditText add_weight;
    private EditText add_comment;
    private AddPhysicalCheckupDialogListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.add_physical_checkup_dialog, null);

        builder.setView(view)
                .setTitle("Dodaj badanie")
                .setNegativeButton("Anuluj", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Dodaj", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String dateString = add_physical_checkup_data.getText().toString();
                        Date date = null;
                        try {
                            date = new SimpleDateFormat("dd/MM/yyyy").parse(dateString);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        Log.i("date1", date.toString());
                        Double height  = Double.parseDouble(add_height.getText().toString());
                        Double weight = Double.parseDouble(add_weight.getText().toString());
                        String comment = add_comment.getText().toString();

                        listener.applyTexts(date, height, weight, comment);
                    }
                });
        add_physical_checkup_data = view.findViewById(R.id.add_physical_checkup_data);
        add_height = view.findViewById(R.id.add_height);
        add_weight = view.findViewById(R.id.add_weight);
        add_comment = view.findViewById(R.id.add_comment);

        return builder.create();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (AddPhysicalCheckupDialogListener) context;
        } catch(Exception e) {
            e.printStackTrace();
        }

    }

    public interface AddPhysicalCheckupDialogListener {
        void applyTexts(Date data, Double height, Double weight, String comment);
    }
}
