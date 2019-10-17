
package com.example.agentmedia.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PriceItem {

    @SerializedName("id_product")
    @Expose
    private String idProduct;
    @SerializedName("name_product")
    @Expose
    private String nameProduct;
    @SerializedName("kode_product")
    @Expose
    private String kodeProduct;
    @SerializedName("id_product_type")
    @Expose
    private String idProductType;
    @SerializedName("price_sell")
    @Expose
    private String priceSell;
    @SerializedName("price_buy")
    @Expose
    private String priceBuy;
    @SerializedName("margin_price")
    @Expose
    private String marginPrice;
    @SerializedName("status_product")
    @Expose
    private String statusProduct;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("update_at")
    @Expose
    private Object updateAt;
    @SerializedName("delete_at")
    @Expose
    private Object deleteAt;
    @SerializedName("name_product_type")
    @Expose
    private String nameProductType;
    @SerializedName("status_product_type")
    @Expose
    private String statusProductType;
    @SerializedName("id_category")
    @Expose
    private String idCategory;
    @SerializedName("name_category")
    @Expose
    private String nameCategory;
    @SerializedName("status_category")
    @Expose
    private String statusCategory;

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getKodeProduct() {
        return kodeProduct;
    }

    public void setKodeProduct(String kodeProduct) {
        this.kodeProduct = kodeProduct;
    }

    public String getIdProductType() {
        return idProductType;
    }

    public void setIdProductType(String idProductType) {
        this.idProductType = idProductType;
    }

    public String getPriceSell() {
        return priceSell;
    }

    public void setPriceSell(String priceSell) {
        this.priceSell = priceSell;
    }

    public String getPriceBuy() {
        return priceBuy;
    }

    public void setPriceBuy(String priceBuy) {
        this.priceBuy = priceBuy;
    }

    public String getMarginPrice() {
        return marginPrice;
    }

    public void setMarginPrice(String marginPrice) {
        this.marginPrice = marginPrice;
    }

    public String getStatusProduct() {
        return statusProduct;
    }

    public void setStatusProduct(String statusProduct) {
        this.statusProduct = statusProduct;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Object getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Object updateAt) {
        this.updateAt = updateAt;
    }

    public Object getDeleteAt() {
        return deleteAt;
    }

    public void setDeleteAt(Object deleteAt) {
        this.deleteAt = deleteAt;
    }

    public String getNameProductType() {
        return nameProductType;
    }

    public void setNameProductType(String nameProductType) {
        this.nameProductType = nameProductType;
    }

    public String getStatusProductType() {
        return statusProductType;
    }

    public void setStatusProductType(String statusProductType) {
        this.statusProductType = statusProductType;
    }

    public String getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(String idCategory) {
        this.idCategory = idCategory;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public String getStatusCategory() {
        return statusCategory;
    }

    public void setStatusCategory(String statusCategory) {
        this.statusCategory = statusCategory;
    }

}