package com.example.agentmedia.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RiwayatItem {

    @SerializedName("id_pemesanan")
    @Expose
    private String idPemesanan;
    @SerializedName("id_product")
    @Expose
    private String idProduct;
    @SerializedName("tujuan")
    @Expose
    private String tujuan;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("status_text")
    @Expose
    private String statusText;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private Object updatedAt;
    @SerializedName("deleted_at")
    @Expose
    private Object deletedAt;
    @SerializedName("id_member")
    @Expose
    private String idMember;
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
    @SerializedName("update_at")
    @Expose
    private Object updateAt;
    @SerializedName("delete_at")
    @Expose
    private Object deleteAt;
    @SerializedName("name_member")
    @Expose
    private String nameMember;
    @SerializedName("password_member")
    @Expose
    private String passwordMember;
    @SerializedName("no_hp")
    @Expose
    private String noHp;
    @SerializedName("ktp_member")
    @Expose
    private String ktpMember;
    @SerializedName("saldo_member")
    @Expose
    private String saldoMember;
    @SerializedName("name_product_type")
    @Expose
    private String nameProductType;
    @SerializedName("status_product_type")
    @Expose
    private String statusProductType;
    @SerializedName("id_category")
    @Expose
    private String idCategory;
    @SerializedName("id_file")
    @Expose
    private String idFile;
    @SerializedName("nama_file")
    @Expose
    private String namaFile;
    @SerializedName("table_relation")
    @Expose
    private Object tableRelation;
    @SerializedName("id_relation")
    @Expose
    private Object idRelation;

    public String getIdPemesanan() {
        return idPemesanan;
    }

    public void setIdPemesanan(String idPemesanan) {
        this.idPemesanan = idPemesanan;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusText() {
        return statusText;
    }

    public void setStatusText(String statusText) {
        this.statusText = statusText;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Object getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Object updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Object getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Object deletedAt) {
        this.deletedAt = deletedAt;
    }

    public String getIdMember() {
        return idMember;
    }

    public void setIdMember(String idMember) {
        this.idMember = idMember;
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

    public String getNameMember() {
        return nameMember;
    }

    public void setNameMember(String nameMember) {
        this.nameMember = nameMember;
    }

    public String getPasswordMember() {
        return passwordMember;
    }

    public void setPasswordMember(String passwordMember) {
        this.passwordMember = passwordMember;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    public String getKtpMember() {
        return ktpMember;
    }

    public void setKtpMember(String ktpMember) {
        this.ktpMember = ktpMember;
    }

    public String getSaldoMember() {
        return saldoMember;
    }

    public void setSaldoMember(String saldoMember) {
        this.saldoMember = saldoMember;
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

    public String getIdFile() {
        return idFile;
    }

    public void setIdFile(String idFile) {
        this.idFile = idFile;
    }

    public String getNamaFile() {
        return namaFile;
    }

    public void setNamaFile(String namaFile) {
        this.namaFile = namaFile;
    }

    public Object getTableRelation() {
        return tableRelation;
    }

    public void setTableRelation(Object tableRelation) {
        this.tableRelation = tableRelation;
    }

    public Object getIdRelation() {
        return idRelation;
    }

    public void setIdRelation(Object idRelation) {
        this.idRelation = idRelation;
    }

}