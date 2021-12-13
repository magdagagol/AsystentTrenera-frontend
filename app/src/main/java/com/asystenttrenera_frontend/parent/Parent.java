package com.asystenttrenera_frontend.parent;

import android.os.Parcel;
import android.os.Parcelable;

import com.asystenttrenera_frontend.participant.Participant;
import com.asystenttrenera_frontend.participant.ParticipantAdapter;

import java.util.HashSet;
import java.util.Set;

public class Parent implements Parcelable {
    private Long id;
    private String name;
    private String surname;
    private Integer phoneNumber;
    private String email;
    private Boolean contactAgree;

    public Parent(String name) {
        this.name = name;
    }

    public Parent(Long id, String name, String surname, Integer phoneNumber, String email, Boolean contactAgree) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.contactAgree = contactAgree;
    }

    @Override
    public String toString() {
        return "Parent{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", phoneNumber=" + phoneNumber +
                ", email='" + email + '\'' +
                ", contactAgree=" + contactAgree +
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

    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getContactAgree() {
        return contactAgree;
    }

    public void setContactAgree(Boolean contactAgree) {
        this.contactAgree = contactAgree;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.name);
        dest.writeString(this.surname);
        dest.writeValue(this.phoneNumber);
        dest.writeString(this.email);
        dest.writeValue(this.contactAgree);
    }

    public void readFromParcel(Parcel source) {
        this.id = (Long) source.readValue(Long.class.getClassLoader());
        this.name = source.readString();
        this.surname = source.readString();
        this.phoneNumber = (Integer) source.readValue(Integer.class.getClassLoader());
        this.email = source.readString();
        this.contactAgree = (Boolean) source.readValue(Boolean.class.getClassLoader());
    }

    protected Parent(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.name = in.readString();
        this.surname = in.readString();
        this.phoneNumber = (Integer) in.readValue(Integer.class.getClassLoader());
        this.email = in.readString();
        this.contactAgree = (Boolean) in.readValue(Boolean.class.getClassLoader());
    }

    public static final Parcelable.Creator<Parent> CREATOR = new Parcelable.Creator<Parent>() {
        @Override
        public Parent createFromParcel(Parcel source) {
            return new Parent(source);
        }

        @Override
        public Parent[] newArray(int size) {
            return new Parent[size];
        }
    };
}
