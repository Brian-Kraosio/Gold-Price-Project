package id.putraprima.mygoldtracker.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "wallet")
public class Wallet implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "walletId")
    private int id;

    private float weight, buyPrice;
    private Date purchaseDate;

    public Wallet() {
    }

    public Wallet(float weight, float buyPrice, Date purchaseDate) {
        this.weight = weight;
        this.buyPrice = buyPrice;
        this.purchaseDate = purchaseDate;
    }

    public Wallet(int id, float weight, float buyPrice, Date purchaseDate) {
        this.id = id;
        this.weight = weight;
        this.buyPrice = buyPrice;
        this.purchaseDate = purchaseDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(float buyPrice) {
        this.buyPrice = buyPrice;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeFloat(this.weight);
        dest.writeFloat(this.buyPrice);
    }

    protected Wallet(Parcel in) {
        this.id = in.readInt();
        this.weight = in.readFloat();
        this.buyPrice = in.readFloat();
    }

    public static final Creator<Wallet> CREATOR = new Creator<Wallet>() {
        @Override
        public Wallet createFromParcel(Parcel source) {
            return new Wallet(source);
        }

        @Override
        public Wallet[] newArray(int size) {
            return new Wallet[size];
        }
    };
}
