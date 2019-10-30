package com.example.agentmedia.model.topup;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RiwayatTopupItem {

    @SerializedName("id_topup")
    @Expose
    private String idTopup;
    @SerializedName("nominal")
    @Expose
    private String nominal;
    @SerializedName("name_payment_method")
    @Expose
    private String namePaymentMethod;
    @SerializedName("create_at")
    @Expose
    private String createAt;
    @SerializedName("nama_file")
    @Expose
    private String namaFile;
    @SerializedName("status_topup")
    @Expose
    private String statusTopup;

    public String getIdTopup() {
        return idTopup;
    }

    public void setIdTopup(String idTopup) {
        this.idTopup = idTopup;
    }

    public String getNominal() {
        return nominal;
    }

    public void setNominal(String nominal) {
        this.nominal = nominal;
    }

    public String getNamePaymentMethod() {
        return namePaymentMethod;
    }

    public void setNamePaymentMethod(String namePaymentMethod) {
        this.namePaymentMethod = namePaymentMethod;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getNamaFile() {
        return namaFile;
    }

    public void setNamaFile(String namaFile) {
        this.namaFile = namaFile;
    }

    public String getStatusTopup() {
        return statusTopup;
    }

    public void setStatusTopup(String statusTopup) {
        this.statusTopup = statusTopup;
    }

}