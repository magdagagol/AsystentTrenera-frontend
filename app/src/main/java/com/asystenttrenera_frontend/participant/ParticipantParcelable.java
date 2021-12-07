package com.asystenttrenera_frontend.participant;

import android.os.Parcel;
import android.os.Parcelable;

public class ParticipantParcelable  implements Parcelable {
    protected ParticipantParcelable(Parcel in) {
    }

    public static final Creator<ParticipantParcelable> CREATOR = new Creator<ParticipantParcelable>() {
        @Override
        public ParticipantParcelable createFromParcel(Parcel in) {
            return new ParticipantParcelable(in);
        }

        @Override
        public ParticipantParcelable[] newArray(int size) {
            return new ParticipantParcelable[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }
}
