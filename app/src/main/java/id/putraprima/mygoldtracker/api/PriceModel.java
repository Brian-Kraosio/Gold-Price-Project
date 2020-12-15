package id.putraprima.mygoldtracker.api;

public class PriceModel {
    private String price_id, date;
    private float sell_price, buy_price, partner_sell_price, tokopedia_sell_percentage, is_future;

    public PriceModel(String price_id, String date, float sell_price, float buy_price, float partner_sell_price, float tokopedia_sell_percentage, float is_future) {
        this.price_id = price_id;
        this.date = date;
        this.sell_price = sell_price;
        this.buy_price = buy_price;
        this.partner_sell_price = partner_sell_price;
        this.tokopedia_sell_percentage = tokopedia_sell_percentage;
        this.is_future = is_future;
    }

    public String getPrice_id() {
        return price_id;
    }

    public void setPrice_id(String price_id) {
        this.price_id = price_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public float getPartner_sell_price() {
        return partner_sell_price;
    }

    public void setPartner_sell_price(float partner_sell_price) {
        this.partner_sell_price = partner_sell_price;
    }

    public float getTokopedia_sell_percentage() {
        return tokopedia_sell_percentage;
    }

    public void setTokopedia_sell_percentage(float tokopedia_sell_percentage) {
        this.tokopedia_sell_percentage = tokopedia_sell_percentage;
    }

    public float getIs_future() {
        return is_future;
    }

    public void setIs_future(float is_future) {
        this.is_future = is_future;
    }
}
