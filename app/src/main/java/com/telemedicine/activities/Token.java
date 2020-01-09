package com.telemedicine.activities;

public class Token {
    private static Token instance;

    private String data;

    private Token() {
    }

    public static synchronized Token getInstance() {
        if (instance == null) {
            instance = new Token();
        }
        return instance;
    }

    public String getData() {
        return this.data;
    }

    public void setData(String token) {
        this.data = token;
    }
}
