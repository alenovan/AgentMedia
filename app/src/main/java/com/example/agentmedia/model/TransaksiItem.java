package com.example.agentmedia.model;

public class TransaksiItem {
    private String title,date,tujuan,status;

    public TransaksiItem(String title, String date, String tujuan, String status) {
        this.title = title;
        this.date = date;
        this.tujuan = tujuan;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
}
