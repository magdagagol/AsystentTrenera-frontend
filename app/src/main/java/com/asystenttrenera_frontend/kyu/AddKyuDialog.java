package com.asystenttrenera_frontend.kyu;

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

public class AddKyuDialog extends AppCompatDialogFragment {
    private EditText add_exam_date;
    private EditText add_kyu_degree;
    private AddKyuDialogListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.add_kyu_dialog, null);

        builder.setView(view)
                .setTitle("Dodaj egzamin kyu")
                .setNegativeButton("Anuluj", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("Dodaj", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String dateString = add_exam_date.getText().toString();
                        Date date = null;
                        try {
                            date = new SimpleDateFormat("dd/MM/yyyy").parse(dateString);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        Log.i("date1", date.toString());
                        String kyuDegree = add_kyu_degree.getText().toString();

                        listener.applyTexts(date, kyuDegree);
                    }
                });
        add_exam_date = view.findViewById(R.id.add_exam_date);
        add_kyu_degree = view.findViewById(R.id.add_kyu_degree);

        return builder.create();
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (AddKyuDialogListener) context;
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public interface AddKyuDialogListener {
        void applyTexts(Date examDate, String kyuDegree);
    }
}
