package com.asystenttrenera_frontend.physicalCheckup;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class PhysicalCheckup implements Parcelable {
    private Long id;
    private Date physicalCheckupData;
    private Double height;
    private Double weight;
    private String comment;

    public PhysicalCheckup(Long id, Date physicalCheckupData, Double height, Double weight, String comment) {
        this.id = id;
        this.physicalCheckupData = physicalCheckupData;
        this.height = height;
        this.weight = weight;
        this.comment = comment;
    }

    public PhysicalCheckup(Date physicalCheckupData, Double height, Double weight, String comment) {
        this.physicalCheckupData = physicalCheckupData;
        this.height = height;
        this.weight = weight;
        this.comment = comment;
    }

    public Long getId() {
        return id;
    }

    public Date getPhysicalCheckupData() {
        return physicalCheckupData;
    }

    public void setPhysicalCheckupData(Date physicalCheckupData) {
        this.physicalCheckupData = physicalCheckupData;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "PhysicalCheckup{" +
                "id=" + id +
                ", physicalCheckupData=" + physicalCheckupData +
                ", height=" + height +
                ", weight=" + weight +
                ", comment='" + comment + '\'' +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeLong(this.physicalCheckupData != null ? this.physicalCheckupData.getTime() : -1);
        dest.writeValue(this.height);
        dest.writeValue(this.weight);
        dest.writeString(this.comment);
    }

    public void readFromParcel(Parcel source) {
        this.id = (Long) source.readValue(Long.class.getClassLoader());
        long tmpPhysicalCheckupData = source.readLong();
        this.physicalCheckupData = tmpPhysicalCheckupData == -1 ? null : new Date(tmpPhysicalCheckupData);
        this.height = (Double) source.readValue(Double.class.getClassLoader());
        this.weight = (Double) source.readValue(Double.class.getClassLoader());
        this.comment = source.readString();
    }

    protected PhysicalCheckup(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        long tmpPhysicalCheckupData = in.readLong();
        this.physicalCheckupData = tmpPhysicalCheckupData == -1 ? null : new Date(tmpPhysicalCheckupData);
        this.height = (Double) in.readValue(Double.class.getClassLoader());
        this.weight = (Double) in.readValue(Double.class.getClassLoader());
        this.comment = in.readString();
    }

    public static final Parcelable.Creator<PhysicalCheckup> CREATOR = new Parcelable.Creator<PhysicalCheckup>() {
        @Override
        public PhysicalCheckup createFromParcel(Parcel source) {
            return new PhysicalCheckup(source);
        }

        @Override
        public PhysicalCheckup[] newArray(int size) {
            return new PhysicalCheckup[size];
        }
    };
}
