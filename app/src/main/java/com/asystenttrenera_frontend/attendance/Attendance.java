package com.asystenttrenera_frontend.attendance;

import android.os.Parcel;
import android.os.Parcelable;

import com.asystenttrenera_frontend.group.Group;

import java.util.Date;

public class Attendance implements Parcelable {
    private Long id;
    private Date date;
    private Group group;

    public Attendance(Long id, Date date, Group group) {
        this.id = id;
        this.date = date;
        this.group = group;
    }

    public Long getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "Attendance{" +
                "id=" + id +
                ", date=" + date +
                ", group=" + group +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeLong(this.date != null ? this.date.getTime() : -1);
        dest.writeParcelable(this.group, flags);
    }

    public void readFromParcel(Parcel source) {
        this.id = (Long) source.readValue(Long.class.getClassLoader());
        long tmpDate = source.readLong();
        this.date = tmpDate == -1 ? null : new Date(tmpDate);
        this.group = source.readParcelable(Group.class.getClassLoader());
    }

    protected Attendance(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        long tmpDate = in.readLong();
        this.date = tmpDate == -1 ? null : new Date(tmpDate);
        this.group = in.readParcelable(Group.class.getClassLoader());
    }

    public static final Parcelable.Creator<Attendance> CREATOR = new Parcelable.Creator<Attendance>() {
        @Override
        public Attendance createFromParcel(Parcel source) {
            return new Attendance(source);
        }

        @Override
        public Attendance[] newArray(int size) {
            return new Attendance[size];
        }
    };
}
