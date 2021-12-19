package com.asystenttrenera_frontend.group;


import android.os.Parcel;
import android.os.Parcelable;

import com.asystenttrenera_frontend.participant.Participant;

import java.util.ArrayList;

public class Group implements Parcelable {
    private Long id;
    private String name;
    private ArrayList<Participant> participants;

    public Group(Long id, String name, ArrayList<Participant> participants) {
        this.id = id;
        this.name = name;
        this.participants = participants;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(ArrayList<Participant> participants) {
        this.participants = participants;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.name);
        dest.writeTypedList(this.participants);
    }

    public void readFromParcel(Parcel source) {
        this.id = (Long) source.readValue(Long.class.getClassLoader());
        this.name = source.readString();
        this.participants = source.createTypedArrayList(Participant.CREATOR);
    }

    protected Group(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.name = in.readString();
        this.participants = in.createTypedArrayList(Participant.CREATOR);
    }

    public static final Creator<Group> CREATOR = new Creator<Group>() {
        @Override
        public Group createFromParcel(Parcel source) {
            return new Group(source);
        }

        @Override
        public Group[] newArray(int size) {
            return new Group[size];
        }
    };
}
