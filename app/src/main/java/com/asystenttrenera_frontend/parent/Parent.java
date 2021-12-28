package com.asystenttrenera_frontend.parent;


import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Parent implements Parcelable, Serializable {
    private Long id;
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;
    private Boolean contactAgree;

    public Parent(Long id, String name, String surname, String phoneNumber, String email, Boolean contactAgree) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.contactAgree = contactAgree;
    }

    public Parent(String name, String surname, String phoneNumber, String email, Boolean contactAgree) {
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.contactAgree = contactAgree;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
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
        dest.writeString(this.phoneNumber);
        dest.writeString(this.email);
        dest.writeValue(this.contactAgree);
    }

    public void readFromParcel(Parcel source) {
        this.id = (Long) source.readValue(Long.class.getClassLoader());
        this.name = source.readString();
        this.surname = source.readString();
        this.phoneNumber = source.readString();
        this.email = source.readString();
        this.contactAgree = (Boolean) source.readValue(Boolean.class.getClassLoader());
    }

    protected Parent(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.name = in.readString();
        this.surname = in.readString();
        this.phoneNumber = in.readString();
        this.email = in.readString();
        this.contactAgree = (Boolean) in.readValue(Boolean.class.getClassLoader());
    }

    public static final Creator<Parent> CREATOR = new Creator<Parent>() {
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
