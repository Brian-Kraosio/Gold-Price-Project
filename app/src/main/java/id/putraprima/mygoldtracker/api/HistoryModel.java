package id.putraprima.mygoldtracker.api;

import java.util.Date;

public class HistoryModel {
    private String price_id;
    private Date date_price;
    private float sell_price, buy_price;

    public HistoryModel(String price_id, Date date_price, float sell_price, float buy_price) {
        this.price_id = price_id;
        this.date_price = date_price;
        this.sell_price = sell_price;
        this.buy_price = buy_price;
    }

    public String getPrice_id() {
        return price_id;
    }

    public void setPrice_id(String price_id) {
        this.price_id = price_id;
    }

    public Date getDate_price() {
        return date_price;
    }

    public void setDate_price(Date date_price) {
        this.date_price = date_price;
    }

    public float getSell_price() {
        return sell_price;
    }

    public void setSell_price(float sell_price) {
        this.sell_price = sell_price;
    }

    public float getBuy_price() {
        return buy_price;
    }

    public void setBuy_price(float buy_price) {
        this.buy_price = buy_price;
    }
}
