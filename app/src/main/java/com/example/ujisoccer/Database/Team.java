package com.example.ujisoccer.Database;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;



@Entity(foreignKeys =
@ForeignKey(entity = League.class,
        parentColumns = "id",
        childColumns = "league_id" ), tableName = "Team")


public class Team implements Parcelable {
    @PrimaryKey
    public int id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "shortname")
    public String shortname;

    /*
    @ColumnInfo(name = "stadium")
    public String stadium;
*/
    @ColumnInfo(name = "colours")
    public String colours;

    @ColumnInfo(name = "web")
    public String web;

    @ColumnInfo(name = "league_id")
    public int league_id;

    @ColumnInfo(name = "founded")
    public String founded;

    @ColumnInfo(name = "venue")
    public String venue;

    public Team(int id, String name, String shortname, String colours, String web,int league_id,String founded,String venue) {
        this.id = id;
        this.name = name;
        this.shortname = shortname;
       // this.stadium = stadium;
        this.colours = colours;
        this.web = web;
        this.league_id = league_id;
        this.founded = founded;
        this.venue = venue;
    }


    protected Team(Parcel in) {
        id = in.readInt();
        name = in.readString();
        shortname = in.readString();
       // stadium = in.readString();
        colours = in.readString();
        web = in.readString();
        league_id = in.readInt();
        founded = in.readString();
        venue = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeString(shortname);
       // dest.writeString(stadium);
        dest.writeString(colours);
        dest.writeString(web);
        dest.writeInt(league_id);
        dest.writeString(founded);
        dest.writeString(venue);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Team> CREATOR = new Parcelable.Creator<Team>() {
        @Override
        public Team createFromParcel(Parcel in) {
            return new Team(in);
        }

        @Override
        public Team[] newArray(int size) {
            return new Team[size];
        }
    };

}