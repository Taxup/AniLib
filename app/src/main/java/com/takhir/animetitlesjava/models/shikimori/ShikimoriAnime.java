package com.takhir.animetitlesjava.models.shikimori;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.annotations.SerializedName;
import com.takhir.animetitlesjava.models.shikimori.image.Image;

public class ShikimoriAnime implements Parcelable {
    @SerializedName("id")
    int id;
    @SerializedName("name")
    String name;
    @SerializedName("russian")
    String russian;
    @SerializedName("image")
    Image image;
    @SerializedName("url")
    String url;
    @SerializedName("kind")
    private String kind;
    @SerializedName("score")
    private String score;
    @SerializedName("status")
    private String status;
    @SerializedName("episodes")
    private String episodes;
    @SerializedName("episodes_aired")
    private String episodes_aired;
    @SerializedName("aired_on")
    private String aired_on;
    @SerializedName("released_on")
    private String released_on;

    protected ShikimoriAnime(Parcel in) {
        id = in.readInt();
        name = in.readString();
        russian = in.readString();
        url = in.readString();
        kind = in.readString();
        score = in.readString();
        status = in.readString();
        episodes = in.readString();
        episodes_aired = in.readString();
        aired_on = in.readString();
        released_on = in.readString();
    }

    public static final Creator<ShikimoriAnime> CREATOR = new Creator<ShikimoriAnime>() {
        @Override
        public ShikimoriAnime createFromParcel(Parcel in) {
            return new ShikimoriAnime(in);
        }

        @Override
        public ShikimoriAnime[] newArray(int size) {
            return new ShikimoriAnime[size];
        }
    };

    @Override
    public String toString() {
        return "Anime{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", russian='" + russian + '\'' +
                ", image=" + image +
                ", url='" + url + '\'' +
                ", kind='" + kind + '\'' +
                ", score='" + score + '\'' +
                ", status='" + status + '\'' +
                ", episodes='" + episodes + '\'' +
                ", episodes_aired='" + episodes_aired + '\'' +
                ", aired_on='" + aired_on + '\'' +
                ", released_on='" + released_on + '\'' +
                '}';
    }

    public ShikimoriAnime() {
    }

    public ShikimoriAnime(int id, String name, String russian, Image image, String url, String kind, String score, String status, String episodes, String episodes_aired, String aired_on, String released_on) {
        this.id = id;
        this.name = name;
        this.russian = russian;
        this.image = image;
        this.url = url;
        this.kind = kind;
        this.score = score;
        this.status = status;
        this.episodes = episodes;
        this.episodes_aired = episodes_aired;
        this.aired_on = aired_on;
        this.released_on = released_on;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRussian() {
        return russian;
    }

    public void setRussian(String russian) {
        this.russian = russian;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getEpisodes() {
        return episodes;
    }

    public void setEpisodes(String episodes) {
        this.episodes = episodes;
    }

    public String getEpisodes_aired() {
        return episodes_aired;
    }

    public void setEpisodes_aired(String episodes_aired) {
        this.episodes_aired = episodes_aired;
    }

    public String getAired_on() {
        return aired_on;
    }

    public void setAired_on(String aired_on) {
        this.aired_on = aired_on;
    }

    public String getReleased_on() {
        return released_on;
    }

    public void setReleased_on(String released_on) {
        this.released_on = released_on;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(russian);
        parcel.writeString(url);
        parcel.writeString(kind);
        parcel.writeString(score);
        parcel.writeString(status);
        parcel.writeString(episodes);
        parcel.writeString(episodes_aired);
        parcel.writeString(aired_on);
        parcel.writeString(released_on);
    }
}
