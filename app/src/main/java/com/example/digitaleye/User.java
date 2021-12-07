package com.example.digitaleye;

public class User {
    private String currency_worth;
    private String timestamp;

    public User(String currency_worth, String timestamp) {
        this.currency_worth = currency_worth;
        this.timestamp = timestamp;
    }
    public String getCurrency_worth() {
        return currency_worth;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setCurrency_worth(String currency_worth) {
        this.currency_worth = currency_worth;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
