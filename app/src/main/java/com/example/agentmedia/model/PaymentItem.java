package com.example.agentmedia.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PaymentItem {

    @SerializedName("id_payment_account")
    @Expose
    private String idPaymentAccount;
    @SerializedName("id_payment_method")
    @Expose
    private String idPaymentMethod;
    @SerializedName("name_payment_account")
    @Expose
    private String namePaymentAccount;
    @SerializedName("nomor_payment_account")
    @Expose
    private String nomorPaymentAccount;
    @SerializedName("type_payment_account")
    @Expose
    private String typePaymentAccount;
    @SerializedName("status_payment_account")
    @Expose
    private String statusPaymentAccount;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("update_at")
    @Expose
    private Object updateAt;
    @SerializedName("delete_at")
    @Expose
    private Object deleteAt;
    @SerializedName("name_payment_method")
    @Expose
    private String namePaymentMethod;
    @SerializedName("catatan_method")
    @Expose
    private Object catatanMethod;
    @SerializedName("api_payment_method")
    @Expose
    private String apiPaymentMethod;
    @SerializedName("code_payment_method")
    @Expose
    private String codePaymentMethod;
    @SerializedName("id_file")
    @Expose
    private String idFile;
    @SerializedName("status_payment_method")
    @Expose
    private String statusPaymentMethod;
    @SerializedName("nama_file")
    @Expose
    private String namaFile;
    @SerializedName("table_relation")
    @Expose
    private Object tableRelation;
    @SerializedName("id_relation")
    @Expose
    private Object idRelation;

    public String getIdPaymentAccount() {
        return idPaymentAccount;
    }

    public void setIdPaymentAccount(String idPaymentAccount) {
        this.idPaymentAccount = idPaymentAccount;
    }

    public String getIdPaymentMethod() {
        return idPaymentMethod;
    }

    public void setIdPaymentMethod(String idPaymentMethod) {
        this.idPaymentMethod = idPaymentMethod;
    }

    public String getNamePaymentAccount() {
        return namePaymentAccount;
    }

    public void setNamePaymentAccount(String namePaymentAccount) {
        this.namePaymentAccount = namePaymentAccount;
    }

    public String getNomorPaymentAccount() {
        return nomorPaymentAccount;
    }

    public void setNomorPaymentAccount(String nomorPaymentAccount) {
        this.nomorPaymentAccount = nomorPaymentAccount;
    }

    public String getTypePaymentAccount() {
        return typePaymentAccount;
    }

    public void setTypePaymentAccount(String typePaymentAccount) {
        this.typePaymentAccount = typePaymentAccount;
    }

    public String getStatusPaymentAccount() {
        return statusPaymentAccount;
    }

    public void setStatusPaymentAccount(String statusPaymentAccount) {
        this.statusPaymentAccount = statusPaymentAccount;
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

    public String getNamePaymentMethod() {
        return namePaymentMethod;
    }

    public void setNamePaymentMethod(String namePaymentMethod) {
        this.namePaymentMethod = namePaymentMethod;
    }

    public Object getCatatanMethod() {
        return catatanMethod;
    }

    public void setCatatanMethod(Object catatanMethod) {
        this.catatanMethod = catatanMethod;
    }

    public String getApiPaymentMethod() {
        return apiPaymentMethod;
    }

    public void setApiPaymentMethod(String apiPaymentMethod) {
        this.apiPaymentMethod = apiPaymentMethod;
    }

    public String getCodePaymentMethod() {
        return codePaymentMethod;
    }

    public void setCodePaymentMethod(String codePaymentMethod) {
        this.codePaymentMethod = codePaymentMethod;
    }

    public String getIdFile() {
        return idFile;
    }

    public void setIdFile(String idFile) {
        this.idFile = idFile;
    }

    public String getStatusPaymentMethod() {
        return statusPaymentMethod;
    }

    public void setStatusPaymentMethod(String statusPaymentMethod) {
        this.statusPaymentMethod = statusPaymentMethod;
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
