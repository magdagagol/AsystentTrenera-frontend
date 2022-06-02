package com.asystenttrenera_frontend.parent;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.asystenttrenera_frontend.R;
import com.asystenttrenera_frontend.participant.Participant;
import com.asystenttrenera_frontend.participant.ParticipantDetails;

import java.util.ArrayList;

public class ParentsListFrag extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    View view;
    ArrayList<Parent> parentArrayList;

    public ParentsListFrag() {
        super(R.layout.fragment_parents_list);
    }

    public ParentsListFrag(ArrayList<Parent> parentArrayList) {
        this.parentArrayList = parentArrayList;
        System.out.println("parent array list " + parentArrayList.toString());
    }

    public ParentsListFrag(int contentLayoutId, ArrayList<Parent> parentArrayList) {
        super(contentLayoutId);
        this.parentArrayList = parentArrayList;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //ArrayList<Parent> parentArrayList = getArguments().getParcelableArrayList("parent");
        ArrayList<Parent> parentArrayList = new ArrayList<>();

        //parentArrayList.add(new Parent(1L, "Monika", "Basińska", 123, "m.basinska@gmail.com", true));
        parentArrayList.add(new Parent(2L, "1111 imie", "aaa nazwisko", "num tel", "email", true));
        parentArrayList.add(new Parent(3L,"222 imie", "aaa nazwisko", "num tel", "email", true));
        parentArrayList.add(new Parent(4L,"333 imie", "aaa nazwisko", "num tel", "email", true));
        parentArrayList.add(new Parent(5L,"444 imie", "aaa nazwisko", "num tel", "email", true));
        System.out.println("################### parent 1111 list " + parentArrayList.toString());


        view = inflater.inflate(R.layout.fragment_parents_list, container, false);

        recyclerView = view.findViewById(R.id.parentRecyclerView);
        layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ParentAdapter(parentArrayList);
        recyclerView.setAdapter(adapter);

        return view;
    }

}