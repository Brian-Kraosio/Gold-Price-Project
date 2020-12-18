package id.putraprima.mygoldtracker.api;

import android.os.Parcel;
import android.os.Parcelable;

public class WalletProfitModel implements Parcelable {
    private float weight;
    private float sellPrice;
    private float profitSell;

    public WalletProfitModel(float weight, float sellPrice, float profitSell) {
        this.weight = weight;
        this.sellPrice = sellPrice;
        this.profitSell = profitSell;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public float getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(float sellPrice) {
        this.sellPrice = sellPrice;
    }

    public float getProfitSell() {
        return profitSell;
    }

    public void setProfitSell(float profitSell) {
        this.profitSell = profitSell;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(this.weight);
        dest.writeFloat(this.sellPrice);
        dest.writeFloat(this.profitSell);
    }

    protected WalletProfitModel(Parcel in) {
        this.weight = in.readFloat();
        this.sellPrice = in.readFloat();
        this.profitSell = in.readFloat();
    }

    public static final Creator<WalletProfitModel> CREATOR = new Creator<WalletProfitModel>() {
        @Override
        public WalletProfitModel createFromParcel(Parcel source) {
            return new WalletProfitModel(source);
        }

        @Override
        public WalletProfitModel[] newArray(int size) {
            return new WalletProfitModel[size];
        }
    };
}
