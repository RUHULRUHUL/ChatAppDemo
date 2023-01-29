package com.ruhul.chatappdemo.java.model;

import java.util.Map;

public class PingJavaModel {
    private Map<String,String> host_last_updated_at;
    private String device_id;
    private String broadcast_id;
    private String fcm_token;
    private String status;

    public PingJavaModel(Map<String, String> host_last_updated_at, String device_id, String broadcast_id, String fcm_token, String status) {
        this.host_last_updated_at = host_last_updated_at;
        this.device_id = device_id;
        this.broadcast_id = broadcast_id;
        this.fcm_token = fcm_token;
        this.status = status;
    }

    public PingJavaModel() {
    }

    public Map<String, String> getHost_last_updated_at() {
        return host_last_updated_at;
    }

    public void setHost_last_updated_at(Map<String, String> host_last_updated_at) {
        this.host_last_updated_at = host_last_updated_at;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public String getBroadcast_id() {
        return broadcast_id;
    }

    public void setBroadcast_id(String broadcast_id) {
        this.broadcast_id = broadcast_id;
    }

    public String getFcm_token() {
        return fcm_token;
    }

    public void setFcm_token(String fcm_token) {
        this.fcm_token = fcm_token;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
