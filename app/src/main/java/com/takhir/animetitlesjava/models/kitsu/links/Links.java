package com.takhir.animetitlesjava.models.kitsu.links;

import android.os.Parcel;
import android.os.Parcelable;

public class Links implements Parcelable {
    private String self;

    protected Links(Parcel in) {
        self = in.readString();
    }

    public static final Creator<Links> CREATOR = new Creator<Links>() {
        @Override
        public Links createFromParcel(Parcel in) {
            return new Links(in);
        }

        @Override
        public Links[] newArray(int size) {
            return new Links[size];
        }
    };

    @Override
    public String toString() {
        return "Links{" +
                "self='" + self + '\'' +
                '}';
    }

    public String getSelf() {
        return self;
    }

    public void setSelf(String self) {
        this.self = self;
    }

    public Links() {
    }

    public Links(String self) {
        this.self = self;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(self);
    }
}
