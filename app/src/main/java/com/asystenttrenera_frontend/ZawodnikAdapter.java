package com.asystenttrenera_frontend;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ZawodnikAdapter extends RecyclerView.Adapter<ZawodnikAdapter.ViewHolder> {

    private ArrayList<Zawodnik> zawodnik;

    public ZawodnikAdapter (Context context, ArrayList<Zawodnik> list) {
        zawodnik = list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView imie;
        TextView nazwisko;
        TextView rokUrodzenia;
        TextView email;
        TextView numerTelefonu;

        public ViewHolder(@NonNull View itemView){
            super(itemView);

            imie = itemView.findViewById(R.id.imie);
            nazwisko = itemView.findViewById(R.id.nazwisko);
            rokUrodzenia = itemView.findViewById(R.id.rokUrodzenia);
            email = itemView.findViewById(R.id.email);
            numerTelefonu = itemView.findViewById(R.id.numerTelefonu);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
    }

    @NonNull
    @Override
    public ZawodnikAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_items, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ZawodnikAdapter.ViewHolder viewHolder, int i) {

        viewHolder.itemView.setTag(zawodnik.get(i));
        viewHolder.imie.setText(zawodnik.get(i).getImie());
        viewHolder.nazwisko.setText(zawodnik.get(i).getNazwisko());
        viewHolder.rokUrodzenia.setText(zawodnik.get(i).getRokUrodzenia());
        viewHolder.email.setText(zawodnik.get(i).getEmail());
        viewHolder.numerTelefonu.setText(zawodnik.get(i).getNumerTelefonu());

    }

    @Override
    public int getItemCount() {
        return zawodnik.size();
    }
}
