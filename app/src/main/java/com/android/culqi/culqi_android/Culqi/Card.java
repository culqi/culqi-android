package com.android.culqi.culqi_android.Culqi;

/**
 * Created by culqi on 2/7/17.
 */

public class Card {

    public Card(String card_number, String cvv, int expiration_month, int expiration_year, String email) {
        this.card_number = card_number;
        this.cvv = cvv;
        this.expiration_month = expiration_month;
        this.expiration_year = expiration_year;
        this.email = email;
    }

    private String card_number;

    private String cvv;

    private int expiration_month;

    private int expiration_year;

    private String email;

    public String getCard_number() {
        return card_number;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public int getExpiration_month() {
        return expiration_month;
    }

    public void setExpiration_month(int expiration_month) {
        this.expiration_month = expiration_month;
    }

    public int getExpiration_year() {
        return expiration_year;
    }

    public void setExpiration_year(int expiration_year) {
        this.expiration_year = expiration_year;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
