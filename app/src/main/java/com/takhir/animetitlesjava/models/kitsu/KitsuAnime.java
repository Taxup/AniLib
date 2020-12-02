package com.takhir.animetitlesjava.models.kitsu;


import android.os.Parcel;
import android.os.Parcelable;
import com.takhir.animetitlesjava.models.kitsu.attributes.Attributes;
import com.takhir.animetitlesjava.models.kitsu.links.Links;

public class KitsuAnime implements Parcelable {
    private int id;
    private Links links;
    private Attributes attributes;

    public KitsuAnime() {
    }

    public KitsuAnime(int id, Links links, Attributes attributes) {
        this.id = id;
        this.links = links;
        this.attributes = attributes;
    }

    protected KitsuAnime(Parcel in) {
        id = in.readInt();
    }

    public static final Creator<KitsuAnime> CREATOR = new Creator<KitsuAnime>() {
        @Override
        public KitsuAnime createFromParcel(Parcel in) {
            return new KitsuAnime(in);
        }

        @Override
        public KitsuAnime[] newArray(int size) {
            return new KitsuAnime[size];
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
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
    }

    @Override
    public String toString() {
        return "Anime{" +
                "id=" + id +
                ", links=" + links +
                ", attributes=" + attributes +
                '}';
    }
}
