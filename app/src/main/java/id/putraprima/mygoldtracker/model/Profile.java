package id.putraprima.mygoldtracker.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "profile")
public class Profile implements Parcelable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "profile_id")
    private int id;

    private String username;
    private String email;
    private String image;

    public Profile() {
    }

    public Profile(String username, String email, String image) {
        this.username = username;
        this.email = email;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.username);
        dest.writeString(this.email);
        dest.writeString(this.image);
    }

    protected Profile(Parcel in) {
        this.id = in.readInt();
        this.username = in.readString();
        this.email = in.readString();
        this.image = in.readString();
    }

    public static final Creator<Profile> CREATOR = new Creator<Profile>() {
        @Override
        public Profile createFromParcel(Parcel source) {
            return new Profile(source);
        }

        @Override
        public Profile[] newArray(int size) {
            return new Profile[size];
        }
    };
}
