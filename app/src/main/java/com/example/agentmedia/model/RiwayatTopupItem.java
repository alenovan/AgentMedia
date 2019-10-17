package com.example.agentmedia.model;

public class RiwayatTopupItem {
    private String id,date,nominal,bank,status;

    public RiwayatTopupItem(String id, String date, String nominal, String bank, String status) {
        this.id = id;
        this.date = date;
        this.nominal = nominal;
        this.bank = bank;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }



    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNominal() {
        return nominal;
    }

    public void setNominal(String nominal) {
        this.nominal = nominal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
