package com.takhir.animetitlesjava.models.kitsu;

import android.os.Parcel;
import android.os.Parcelable;

public class Links implements Parcelable {
    private String prev;
    private String self;
    private String next;
    private String last;

    public Links() {
    }

    public Links(String prev, String self, String next, String last) {
        this.prev = prev;
        this.self = self;
        this.next = next;
        this.last = last;
    }

    protected Links(Parcel in) {
        prev = in.readString();
        self = in.readString();
        next = in.readString();
        last = in.readString();
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

    public String getPrev() {
        return prev;
    }

    public void setPrev(String prev) {
        this.prev = prev;
    }

    public String getSelf() {
        return self;
    }

    public void setSelf(String self) {
        this.self = self;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(prev);
        parcel.writeString(self);
        parcel.writeString(next);
        parcel.writeString(last);
    }
}
