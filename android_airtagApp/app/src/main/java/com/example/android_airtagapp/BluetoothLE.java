package com.example.android_airtagapp;

public class BluetoothLE {
    private String content;
    private String rssi;

    public BluetoothLE(String content, String rssi) {
        this.content = content;
        this.rssi = rssi;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRssi() {
        return rssi;
    }

    public void setRssi(String rssi) {
        this.rssi = rssi;
    }
}
