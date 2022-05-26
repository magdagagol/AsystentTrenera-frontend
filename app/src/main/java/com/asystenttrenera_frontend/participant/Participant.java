package com.asystenttrenera_frontend.participant;

import android.os.Parcel;
import android.os.Parcelable;

import com.asystenttrenera_frontend.group.Group;
import com.asystenttrenera_frontend.parent.Parent;

import java.util.ArrayList;
import java.util.List;


public class Participant implements Parcelable {
    private Long id;
    private String name;
    private String surname;
    private String yearOfBirth;
    private String email;
    private String phoneNumber;
    private ArrayList<Parent> parents;
    private Group group;

    public Participant(Long id, String name, String surname, String yearOfBirth) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.yearOfBirth = yearOfBirth;
    }

    public Participant(String name, String surname, String yearOfBirth, String email, String phoneNumber) {
        this.name = name;
        this.surname = surname;
        this.yearOfBirth = yearOfBirth;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Participant(String name, String surname, String yearOfBirth, String email, String phoneNumber, ArrayList<Parent> parents) {
        this.name = name;
        this.surname = surname;
        this.yearOfBirth = yearOfBirth;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.parents = parents;
    }

    public Participant(Long id,String name, String surname, String yearOfBirth, String email, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.yearOfBirth = yearOfBirth;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Participant(Long id, String name, String surname, String yearOfBirth, String email, String phoneNumber, ArrayList<Parent> parents) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.yearOfBirth = yearOfBirth;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.parents = parents;
    }

    public Participant(Long id, String name, String surname, String yearOfBirth, String email, String phoneNumber, Group group) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.yearOfBirth = yearOfBirth;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.group = group;
    }

    public Participant(Long id, String name, String surname, String yearOfBirth, String email, String phoneNumber, ArrayList<Parent> parents, Group group) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.yearOfBirth = yearOfBirth;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.parents = parents;
        this.group = group;
    }

    @Override
    public String toString() {
        return "Participant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", yearOfBirth='" + yearOfBirth + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", parents=" + parents +
                ", group=" + group +
                '}';
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(String yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<Parent> getParents() {
        return parents;
    }

    public void setParents(ArrayList<Parent> parents) {
        this.parents = parents;
    }

    public Group getGroup() { return group; }

    public void setGroup(Group group) { this.group = group; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.name);
        dest.writeString(this.surname);
        dest.writeString(this.yearOfBirth);
        dest.writeString(this.email);
        dest.writeString(this.phoneNumber);
        dest.writeList(this.parents);
    }

    public void readFromParcel(Parcel source) {
        this.id = (Long) source.readValue(Long.class.getClassLoader());
        this.name = source.readString();
        this.surname = source.readString();
        this.yearOfBirth = source.readString();
        this.email = source.readString();
        this.phoneNumber = source.readString();
        this.parents = source.readParcelable(Parent.class.getClassLoader());
    }

    protected Participant(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.name = in.readString();
        this.surname = in.readString();
        this.yearOfBirth = in.readString();
        this.email = in.readString();
        this.phoneNumber = in.readString();
        this.parents  = new ArrayList<Parent>();
        in.readList(parents, Parent.class.getClassLoader());
    }

    public static final Parcelable.Creator<Participant> CREATOR = new Parcelable.Creator<Participant>() {
        @Override
        public Participant createFromParcel(Parcel source) {
            return new Participant(source);
        }

        @Override
        public Participant[] newArray(int size) {
            return new Participant[size];
        }
    };
}
