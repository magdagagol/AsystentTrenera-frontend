package com.asystenttrenera_frontend.kyu;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class Kyu implements Parcelable {
    private Long id;
    private Date examDate;
    private String kyuDegree;

    public Kyu(Long id, Date examDate, String kyuDegree) {
        this.id = id;
        this.examDate = examDate;
        this.kyuDegree = kyuDegree;
    }

    public Kyu(Date examDate, String kyuDegree) {
        this.examDate = examDate;
        this.kyuDegree = kyuDegree;
    }

    public Long getId() {
        return id;
    }

    public Date getExamDate() {
        return examDate;
    }

    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }

    public String getKyuDegree() {
        return kyuDegree;
    }

    public void setKyuDegree(String kyuDegree) {
        this.kyuDegree = kyuDegree;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeLong(this.examDate != null ? this.examDate.getTime() : -1);
        dest.writeString(this.kyuDegree);
    }

    public void readFromParcel(Parcel source) {
        this.id = (Long) source.readValue(Long.class.getClassLoader());
        long tmpExamDate = source.readLong();
        this.examDate = tmpExamDate == -1 ? null : new Date(tmpExamDate);
        this.kyuDegree = source.readString();
    }

    protected Kyu(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        long tmpExamDate = in.readLong();
        this.examDate = tmpExamDate == -1 ? null : new Date(tmpExamDate);
        this.kyuDegree = in.readString();
    }

    public static final Parcelable.Creator<Kyu> CREATOR = new Parcelable.Creator<Kyu>() {
        @Override
        public Kyu createFromParcel(Parcel source) {
            return new Kyu(source);
        }

        @Override
        public Kyu[] newArray(int size) {
            return new Kyu[size];
        }
    };
}
