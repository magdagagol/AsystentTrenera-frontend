package com.asystenttrenera_frontend.kyu;

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

public class EditKyuDialog extends AppCompatDialogFragment {
    private EditText exam_date;
    private EditText kyu_degree;
    private EditKyuDialogListener listener;

    private Kyu kyu;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.edit_kyu_dialog, null);
        Bundle extras = getArguments();
        kyu = extras.getParcelable("kyu");

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
                        String dateString = exam_date.getText().toString();
                        Date date = null;
                        try {
                            date = new SimpleDateFormat("dd/MM/yyyy").parse(dateString);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        Log.i("date1", date.toString());
                        String kyuDegree = kyu_degree.getText().toString();

                        listener.updateTexts(kyu.getId(), date, kyuDegree);
                    }
                });
        exam_date = view.findViewById(R.id.edit_exam_date);
        Date dateT = kyu.getExamDate();
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String date = df.format(dateT);

        exam_date.setText(date);

        kyu_degree = view.findViewById(R.id.edit_kyu_degree);
        kyu_degree.setText(kyu.getKyuDegree());

        return builder.create();
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        try {
            listener = (EditKyuDialogListener) context;
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public interface EditKyuDialogListener {
        void updateTexts(Long id, Date examDate, String kyuDegree);
    }
}
