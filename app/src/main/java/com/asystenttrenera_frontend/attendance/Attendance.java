package com.asystenttrenera_frontend.attendance;

import android.os.Parcel;
import android.os.Parcelable;

import com.asystenttrenera_frontend.group.Group;
import com.asystenttrenera_frontend.participant.Participant;

import java.util.Date;
import java.util.List;

public class Attendance implements Parcelable {
    private Long id;
    private Date date;
    private Group group;
    private List<Participant> participants;

    public Attendance(Long id, Date date, Group group) {
        this.id = id;
        this.date = date;
        this.group = group;
    }

    public Attendance(Long id, Date date, Group group, List<Participant> participants) {
        this.id = id;
        this.date = date;
        this.group = group;
        this.participants = participants;
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

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }

    @Override
    public String toString() {
        return "Attendance{" +
                "id=" + id +
                ", date=" + date +
                ", group=" + group +
                ", participants=" + participants +
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
        dest.writeTypedList(this.participants);
    }

    public void readFromParcel(Parcel source) {
        this.id = (Long) source.readValue(Long.class.getClassLoader());
        long tmpDate = source.readLong();
        this.date = tmpDate == -1 ? null : new Date(tmpDate);
        this.group = source.readParcelable(Group.class.getClassLoader());
        this.participants = source.createTypedArrayList(Participant.CREATOR);
    }

    protected Attendance(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        long tmpDate = in.readLong();
        this.date = tmpDate == -1 ? null : new Date(tmpDate);
        this.group = in.readParcelable(Group.class.getClassLoader());
        this.participants = in.createTypedArrayList(Participant.CREATOR);
    }

    public static final Creator<Attendance> CREATOR = new Creator<Attendance>() {
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
