package com.takhir.animetitlesjava.models.kitsu.attributes.image;

import android.os.Parcel;
import android.os.Parcelable;

public class Image implements Parcelable {
    private String tiny;
    private String small;
    private String medium;
    private String large;
    private String original;

    public Image() {
    }

    protected Image(Parcel in) {
        tiny = in.readString();
        small = in.readString();
        medium = in.readString();
        large = in.readString();
        original = in.readString();
    }

    public static final Creator<Image> CREATOR = new Creator<Image>() {
        @Override
        public Image createFromParcel(Parcel in) {
            return new Image(in);
        }

        @Override
        public Image[] newArray(int size) {
            return new Image[size];
        }
    };

    @Override
    public String toString() {
        return "Image{" +
                "tiny='" + tiny + '\'' +
                ", small='" + small + '\'' +
                ", medium='" + medium + '\'' +
                ", large='" + large + '\'' +
                ", original='" + original + '\'' +
                '}';
    }

    public Image(String tiny, String small, String medium, String large, String original) {
        this.tiny = tiny;
        this.small = small;
        this.medium = medium;
        this.large = large;
        this.original = original;
    }

    public String getTiny() {
        return tiny;
    }

    public void setTiny(String tiny) {
        this.tiny = tiny;
    }

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(tiny);
        parcel.writeString(small);
        parcel.writeString(medium);
        parcel.writeString(large);
        parcel.writeString(original);
    }
}
