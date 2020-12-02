package com.takhir.animetitlesjava.models.kitsu.attributes.titles;

import android.os.Parcel;
import android.os.Parcelable;

public class Titles implements Parcelable {
    private String en;
    private String en_jp;
    private String ja_jp;

    @Override
    public String toString() {
        return "Titles{" +
                "en='" + en + '\'' +
                ", en_jp='" + en_jp + '\'' +
                ", ja_jp='" + ja_jp + '\'' +
                '}';
    }

    public Titles() {
    }

    public Titles(String en, String en_jp, String ja_jp) {
        this.en = en;
        this.en_jp = en_jp;
        this.ja_jp = ja_jp;
    }

    protected Titles(Parcel in) {
        en = in.readString();
        en_jp = in.readString();
        ja_jp = in.readString();
    }

    public static final Creator<Titles> CREATOR = new Creator<Titles>() {
        @Override
        public Titles createFromParcel(Parcel in) {
            return new Titles(in);
        }

        @Override
        public Titles[] newArray(int size) {
            return new Titles[size];
        }
    };

    public String getEn() {
        return en;
    }

    public void setEn(String en) {
        this.en = en;
    }

    public String getEn_jp() {
        return en_jp;
    }

    public void setEn_jp(String en_jp) {
        this.en_jp = en_jp;
    }

    public String getJa_jp() {
        return ja_jp;
    }

    public void setJa_jp(String ja_jp) {
        this.ja_jp = ja_jp;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(en);
        parcel.writeString(en_jp);
        parcel.writeString(ja_jp);
    }
}
