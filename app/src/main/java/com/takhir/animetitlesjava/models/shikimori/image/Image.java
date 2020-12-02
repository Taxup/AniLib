package com.takhir.animetitlesjava.models.shikimori.image;

import android.os.Parcel;
import android.os.Parcelable;

public class Image implements Parcelable {
    private String original;
    private String preview;
    private String x96;
    private String x48;

    @Override
    public String toString() {
        return "Image{" +
                "original='" + original + '\'' +
                ", preview='" + preview + '\'' +
                ", x96='" + x96 + '\'' +
                ", x48='" + x48 + '\'' +
                '}';
    }

    public Image() {
    }

    public Image(String original, String preview, String x96, String x48) {
        this.original = original;
        this.preview = preview;
        this.x96 = x96;
        this.x48 = x48;
    }

    protected Image(Parcel in) {
        original = in.readString();
        preview = in.readString();
        x96 = in.readString();
        x48 = in.readString();
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

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public String getX96() {
        return x96;
    }

    public void setX96(String x96) {
        this.x96 = x96;
    }

    public String getX48() {
        return x48;
    }

    public void setX48(String x48) {
        this.x48 = x48;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(original);
        parcel.writeString(preview);
        parcel.writeString(x96);
        parcel.writeString(x48);
    }
}
