package com.ruhul.chatappdemo.java.model;

public class BroadCastStatusJavaModel {

    private boolean status;
    private String type;
    private String action_status;
    private String message;
    private String request_to_logout;
    private String end_broadcast;

    public BroadCastStatusJavaModel() {
    }

    public BroadCastStatusJavaModel(boolean status, String type, String action_status, String message, String request_to_logout, String end_broadcast) {
        this.status = status;
        this.type = type;
        this.action_status = action_status;
        this.message = message;
        this.request_to_logout = request_to_logout;
        this.end_broadcast = end_broadcast;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAction_status() {
        return action_status;
    }

    public void setAction_status(String action_status) {
        this.action_status = action_status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRequest_to_logout() {
        return request_to_logout;
    }

    public void setRequest_to_logout(String request_to_logout) {
        this.request_to_logout = request_to_logout;
    }

    public String getEnd_broadcast() {
        return end_broadcast;
    }

    public void setEnd_broadcast(String end_broadcast) {
        this.end_broadcast = end_broadcast;
    }
}
