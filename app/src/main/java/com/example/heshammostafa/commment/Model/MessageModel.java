package com.example.heshammostafa.commment.Model;

/**
 * Created by HeshamMostafa on 8/6/2017.
 */

public class MessageModel {
    private String message = null;
    private Boolean isSender = null;
    private String time = null;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Boolean getSender() {
        return isSender;
    }

    public void setSender(Boolean sender) {
        isSender = sender;
    }
}

