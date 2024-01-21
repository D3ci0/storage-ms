package com.gamma.storage.model;

public class StoreFileResponseMessage {
    private String resultCode;

    public StoreFileResponseMessage() {
    }

    public StoreFileResponseMessage(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }
}
