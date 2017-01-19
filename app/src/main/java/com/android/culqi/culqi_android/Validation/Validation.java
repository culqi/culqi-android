package com.android.culqi.culqi_android.Validation;

import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by culqi on 1/19/17.
 */

public class Validation {

    public static boolean luhn(String number){
        int s1 = 0, s2 = 0;
        String reverse = new StringBuffer(number).reverse().toString();
        for(int i = 0 ;i < reverse.length();i++){
            int digit = Character.digit(reverse.charAt(i), 10);
            if(i % 2 == 0){//this is for odd digits, they are 1-indexed in the algorithm
                s1 += digit;
            }else{//add 2 * digit for 0-4, add 2 * digit - 9 for 5-9
                s2 += 2 * digit;
                if(digit >= 5){
                    s2 -= 9;
                }
            }
        }
        return (s1 + s2) % 10 == 0;
    }

    public int bin(String bin, final TextView kind_card) {

        if(bin.length() > 0) {
            if(Integer.valueOf(""+bin.charAt(0)) == 3) {
                kind_card.setText("AMEX");
                return 4;
            } else if(Integer.valueOf(""+bin.charAt(0)) == 4) {
                kind_card.setText("VISA");
                return 3;
            } else if (Integer.valueOf(""+bin.charAt(0)) == 5) {
                kind_card.setText("MasterCard");
                return 3;
            } else {
            }
        } else {
            kind_card.setText("");
        }

        if(bin.length() > 1) {
            if(Integer.valueOf(bin.substring(0,2)) == 36){
                kind_card.setText("Diners Club");
                return 3;
            } else if(Integer.valueOf(bin.substring(0,2)) == 38){
                kind_card.setText("Diners Club");
                return 3;
            } else if(Integer.valueOf(bin.substring(0,2)) == 37){
                kind_card.setText("AMEX");
                return 3;
            } else {
            }
        }

        if(bin.length() > 2) {
            if(Integer.valueOf(bin.substring(0,3)) == 300){
                kind_card.setText("Diners Club");
                return 3;
            } else if(Integer.valueOf(bin.substring(0,3)) == 305){
                kind_card.setText("Diners Club");
                return 3;
            } else {
            }
        }
        return 0;
    }

    public boolean month(String month) {
        if(!month.equals("")){
            if(Integer.valueOf(""+month) > 12) {
                return true;
            }
        }
        return false;
    }

    public boolean year(String year){
        Date today = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        if(!year.equals("")){
            if(Integer.valueOf("20"+year) < calendar.get(Calendar.YEAR)) {
                return true;
            }
        }
        return false;
    }

}
