package com.asystenttrenera_frontend.parent;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.asystenttrenera_frontend.R;

import java.util.ArrayList;

public class ParentsListFrag extends Fragment implements ParentAdapter.ItemClicked, EditParentDialog.EditParentDialogListener,
DeleteParentDialog.DeleteParentDialogListener {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    View view;
    ArrayList<Parent> parentArrayList;

    private Parent currentParent;
    private Long participant_id;

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case 121:
                Log.i("item", "Usuń " + currentParent.toString());
                deleteParent(currentParent.getId());
                return true;
            case 122:
                Log.i("item", "Edytuj " + currentParent.toString());
                editParent(currentParent);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    private void deleteParent(Long id) {
        DeleteParentDialog deleteParentDialog = new DeleteParentDialog();
        Bundle args = new Bundle();
        args.putParcelable("parent", currentParent);
        deleteParentDialog.setArguments(args);
        deleteParentDialog.show(getActivity().getSupportFragmentManager().beginTransaction(), "deleteParentDialog");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_parents_list, container, false);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            parentArrayList = getArguments().getParcelableArrayList("parents");
            participant_id = getArguments().getLong("participant_id");
        } else {
            Log.i("ErrorMessage", "nie ma takiego bundle");
        }

        recyclerView = view.findViewById(R.id.parentRecyclerView);
        layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ParentAdapter(this, parentArrayList);
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onItemClicked(int item) {
        currentParent = parentArrayList.get(item);
    }

    @Override
    public void deleteTexts(Long id) {
        ParentService parentService = new ParentService(getActivity());
        parentService.deleteParent(id);
    }

    private void editParent(Parent currentParent) {
        EditParentDialog editParentDialog = new EditParentDialog();
        Bundle args = new Bundle();
        args.putParcelable("parent", currentParent);
        editParentDialog.setArguments(args);
        editParentDialog.show(getActivity().getSupportFragmentManager().beginTransaction(), "deleteParentDialog");
    }

    @Override
    public void updateTexts(Long id, String name, String surname, String phoneNumber, String email, Boolean contactAgree) {
        Parent parent = new Parent(name, surname, phoneNumber, email, contactAgree);
        ParentService parentService = new ParentService(getActivity());
        parentService.updateParent(parent, id);
    }
}