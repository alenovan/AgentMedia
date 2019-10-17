package com.example.agentmedia.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ProviderItem {

    @SerializedName("id_product_type")
    @Expose
    private String idProductType;
    @SerializedName("name_product_type")
    @Expose
    private String nameProductType;
    @SerializedName("status_product_type")
    @Expose
    private String statusProductType;
    @SerializedName("id_category")
    @Expose
    private String idCategory;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("update_at")
    @Expose
    private Object updateAt;
    @SerializedName("delete_at")
    @Expose
    private Object deleteAt;

    public String getIdProductType() {
        return idProductType;
    }

    public void setIdProductType(String idProductType) {
        this.idProductType = idProductType;
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

}