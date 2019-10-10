package com.example.agentmedia.model;

public class PriceItem {
    int id;
    String tag_price,price_sell,provider;

    public PriceItem(int id, String tag_price, String price_sell, String provider) {
        this.id = id;
        this.tag_price = tag_price;
        this.price_sell = price_sell;
        this.provider = provider;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTag_price() {
        return tag_price;
    }

    public void setTag_price(String tag_price) {
        this.tag_price = tag_price;
    }

    public String getPrice_sell() {
        return price_sell;
    }

    public void setPrice_sell(String price_sell) {
        this.price_sell = price_sell;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }
}
