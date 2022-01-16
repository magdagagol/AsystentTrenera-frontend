package com.asystenttrenera_frontend.message;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.asystenttrenera_frontend.R;
import com.asystenttrenera_frontend.group.Group;

import java.util.ArrayList;

public class GroupAdapter extends ArrayAdapter<Group> {
    public GroupAdapter(@NonNull Context context, ArrayList<Group> resource) {
        super(context, 0, resource);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.spinner_group_row, parent, false
            );
        }

        TextView textView = convertView.findViewById(R.id.spinnerGroupName);
        Group group = getItem(position);

        if (group !=null){ textView.setText(group.getName()); }

        return convertView;
    }

}
