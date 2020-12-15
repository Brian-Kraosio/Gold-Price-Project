package id.putraprima.mygoldtracker.api;

public class BuyModel {
    private float weight;
    private float sellPrice;
    private float buyPrice;

    public BuyModel(float weight, float sellPrice, float buyPrice) {
        this.weight = weight;
        this.sellPrice = sellPrice;
        this.buyPrice = buyPrice;
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

    public float getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(float buyPrice) {
        this.buyPrice = buyPrice;
    }
}
