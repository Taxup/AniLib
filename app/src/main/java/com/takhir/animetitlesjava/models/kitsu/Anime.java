package com.takhir.animetitlesjava.models.kitsu;


import android.os.Parcel;
import android.os.Parcelable;
import com.takhir.animetitlesjava.models.kitsu.attributes.Attributes;
import com.takhir.animetitlesjava.models.kitsu.links.Links;

import java.util.List;

public class Anime implements Parcelable {
    private int id;
    private Links links;
    private Attributes attributes;
    private List<Genres> genres;


    public Anime(int id, Links links, Attributes attributes, List<Genres> genres) {
        this.id = id;
        this.links = links;
        this.attributes = attributes;
        this.genres = genres;
    }

    public List<Genres> getAnimeGenres() {
        return genres;
    }

    public void setAnimeGenres(List<Genres> genres) {
        this.genres = genres;
    }

    public Anime() {
    }

    protected Anime(Parcel in) {
        id = in.readInt();
        links = in.readParcelable(Links.class.getClassLoader());
        attributes = in.readParcelable(Attributes.class.getClassLoader());
    }

    public static final Creator<Anime> CREATOR = new Creator<Anime>() {
        @Override
        public Anime createFromParcel(Parcel in) {
            return new Anime(in);
        }

        @Override
        public Anime[] newArray(int size) {
            return new Anime[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public Attributes getAttributes() {
        return attributes;
    }

    public void setAttributes(Attributes attributes) {
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return "Anime{" +
                "id=" + id +
                ", links=" + links +
                ", attributes=" + attributes +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeParcelable(links, i);
        parcel.writeParcelable(attributes, i);
        parcel.writeList(genres);
    }
}
