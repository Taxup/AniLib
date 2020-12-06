package com.takhir.animetitlesjava.models.kitsu;

import android.os.Parcel;
import android.os.Parcelable;
import com.takhir.animetitlesjava.models.kitsu.attributes.Attributes;

public class Genres implements Parcelable {
    private int id;
    private Attributes attributes;

    protected Genres(Parcel in) {
        id = in.readInt();
        attributes = in.readParcelable(Attributes.class.getClassLoader());
    }

    public static final Creator<Genres> CREATOR = new Creator<Genres>() {
        @Override
        public Genres createFromParcel(Parcel in) {
            return new Genres(in);
        }

        @Override
        public Genres[] newArray(int size) {
            return new Genres[size];
        }
    };

    @Override
    public String toString() {
        return "AnimeGenres{" +
                "id=" + id +
                ", attributes=" + attributes +
                '}';
    }

    public Genres(int id, Attributes attributes) {
        this.id = id;
        this.attributes = attributes;
    }

    public Genres() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Attributes getAttributes() {
        return attributes;
    }

    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

    }
}
