package com.takhir.animetitlesjava.models.kitsu.attributes;

import android.os.Parcel;
import android.os.Parcelable;
import com.takhir.animetitlesjava.models.kitsu.attributes.image.Image;
import com.takhir.animetitlesjava.models.kitsu.attributes.titles.Titles;

import java.util.Arrays;

public class Attributes implements Parcelable {
    private String createdAt;
    private String updatedAt;
    private String slug;
    private String description;
    private Titles titles;
    private String canonicalTitle;
    private String[] abbreviatedTitles;
    private String averageRating;
    private String startDate;
    private String endDate;
    private Image posterImage;
    private Image coverImage;
    private int episodeCount;
    private int episodeLength;

    @Override
    public String toString() {
        return "Attributes{" +
                "createdAt='" + createdAt + '\'' +
                ", updatedAt='" + updatedAt + '\'' +
                ", slug='" + slug + '\'' +
                ", description='" + description + '\'' +
                ", titles=" + titles +
                ", canonicalTitle='" + canonicalTitle + '\'' +
                ", abbreviatedTitles=" + Arrays.toString(abbreviatedTitles) +
                ", averageRating='" + averageRating + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", posterImage=" + posterImage +
                ", coverImage=" + coverImage +
                ", episodeCount=" + episodeCount +
                ", episodeLength=" + episodeLength +
                '}';
    }

    protected Attributes(Parcel in) {
        createdAt = in.readString();
        updatedAt = in.readString();
        slug = in.readString();
        description = in.readString();
        titles = in.readParcelable(Titles.class.getClassLoader());
        canonicalTitle = in.readString();
        abbreviatedTitles = in.createStringArray();
        averageRating = in.readString();
        startDate = in.readString();
        endDate = in.readString();
        posterImage = in.readParcelable(Image.class.getClassLoader());
        coverImage = in.readParcelable(Image.class.getClassLoader());
        episodeCount = in.readInt();
        episodeLength = in.readInt();
    }

    public static final Creator<Attributes> CREATOR = new Creator<Attributes>() {
        @Override
        public Attributes createFromParcel(Parcel in) {
            return new Attributes(in);
        }

        @Override
        public Attributes[] newArray(int size) {
            return new Attributes[size];
        }
    };

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Titles getTitles() {
        return titles;
    }

    public void setTitles(Titles titles) {
        this.titles = titles;
    }

    public String getCanonicalTitle() {
        return canonicalTitle;
    }

    public void setCanonicalTitle(String canonicalTitle) {
        this.canonicalTitle = canonicalTitle;
    }

    public String[] getAbbreviatedTitles() {
        return abbreviatedTitles;
    }

    public void setAbbreviatedTitles(String[] abbreviatedTitles) {
        this.abbreviatedTitles = abbreviatedTitles;
    }

    public String getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(String averageRating) {
        this.averageRating = averageRating;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Image getPosterImage() {
        return posterImage;
    }

    public void setPosterImage(Image posterImage) {
        this.posterImage = posterImage;
    }

    public Image getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(Image coverImage) {
        this.coverImage = coverImage;
    }

    public int getEpisodeCount() {
        return episodeCount;
    }

    public void setEpisodeCount(int episodeCount) {
        this.episodeCount = episodeCount;
    }

    public int getEpisodeLength() {
        return episodeLength;
    }

    public void setEpisodeLength(int episodeLength) {
        this.episodeLength = episodeLength;
    }

    public Attributes() {
    }

    public Attributes(String createdAt, String updatedAt, String slug, String description, Titles titles, String canonicalTitle, String[] abbreviatedTitles, String averageRating, String startDate, String endDate, Image posterImage, Image coverImage, int episodeCount, int episodeLength) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.slug = slug;
        this.description = description;
        this.titles = titles;
        this.canonicalTitle = canonicalTitle;
        this.abbreviatedTitles = abbreviatedTitles;
        this.averageRating = averageRating;
        this.startDate = startDate;
        this.endDate = endDate;
        this.posterImage = posterImage;
        this.coverImage = coverImage;
        this.episodeCount = episodeCount;
        this.episodeLength = episodeLength;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(createdAt);
        parcel.writeString(updatedAt);
        parcel.writeString(slug);
        parcel.writeString(description);
        parcel.writeParcelable(titles, i);
        parcel.writeString(canonicalTitle);
        parcel.writeStringArray(abbreviatedTitles);
        parcel.writeString(averageRating);
        parcel.writeString(startDate);
        parcel.writeString(endDate);
        parcel.writeParcelable(posterImage, i);
        parcel.writeParcelable(coverImage, i);
        parcel.writeInt(episodeCount);
        parcel.writeInt(episodeLength);
    }
}
