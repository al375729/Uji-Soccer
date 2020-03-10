package com.example.ujisoccer;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;



@Entity
public class League implements Parcelable {
    @PrimaryKey
    public int id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "country")
    public String country;

    @ColumnInfo(name = "start")
    public String start;

    @ColumnInfo(name = "end")
    public String end;

    public League(int id, String name,String country, String start, String end ){
        this.id = id;
        this.name = name;
        this.country = country;
        this.start = start;
        this.end = end;
    }

    protected League(Parcel in) {
        id = in.readInt();
        name = in.readString();
        country = in.readString();
        start = in.readString();
        end = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(country);
        dest.writeString(start);
        dest.writeString(end);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<League> CREATOR = new Parcelable.Creator<League>() {
        @Override
        public League createFromParcel(Parcel in) {
            return new League(in);
        }

        @Override
        public League[] newArray(int size) {
            return new League[size];
        }
    };
}