package com.example.wangdonghai.didimodel;

/**
 * Created by wangdonghai on 16/10/16.
 */

public class DiDiOrder {
    private String orderId;
    private String totelfee;
    private String fromAdress;
    private String toAdress;
    private String time;

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public void setTotelfee(String totelfee) {
        this.totelfee = totelfee;
    }

    public void setFromAdress(String fromAdress) {
        this.fromAdress = fromAdress;
    }

    public void setToAdress(String toAdress) {
        this.toAdress = toAdress;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getOrderId() {
        return orderId;
    }

    public String getTotelfee() {
        return totelfee;
    }

    public String getFromAdress() {
        return fromAdress;
    }

    public String getToAdress() {
        return toAdress;
    }

    public String getTime() {
        return time;
    }

    public boolean equals(DiDiOrder diDiOrder) {
        return orderId.equals(diDiOrder.getOrderId());
    }
}
