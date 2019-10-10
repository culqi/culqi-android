package com.android.culqi.culqi_android.utils

import java.util.*

class Validation {

    fun bin(bin: String): Pair<String,Int> {

        if (bin.length > 1) {
            when (Integer.valueOf(bin.substring(0, 2))) {
                36 -> {
                    return Pair("Diners Club",3)
                }
                37 -> {
                    return Pair("Diners Club",3)
                }
                38 -> {
                    return Pair("Diners Club",3)
                }
                41 -> {
                    return Pair("VISA",3)
                }
                51 -> {
                    return Pair("MasterCard",3)
                }
            }
        }

        if (bin.length > 2) {
            when (Integer.valueOf(bin.substring(0, 3))) {
                300 -> {
                    return Pair("Diners Club",3)
                }
                305 -> {
                    return Pair("Diners Club",3)
                }
            }
        }
        return Pair("",0)
    }

    fun month(month: String): Boolean {
        if (month != "") {
            if (month.toInt() > 12) {
                return true
            }
        }
        return false
    }

    fun year(year: String): Boolean {
        val today = Date()
        val calendar = Calendar.getInstance()
        calendar.time = today
        if (year != "") {
            if (Integer.valueOf("20$year") < calendar.get(Calendar.YEAR)) {
                return true
            }
        }
        return false
    }

    fun luhn(number: String): Boolean {
        var s1 = 0
        var s2 = 0
        val reverse = StringBuffer(number).reverse().toString()
        for (i in 0 until reverse.length) {
            val digit = Character.digit(reverse[i], 10)
            if (i % 2 == 0) {//this is for odd digits, they are 1-indexed in the algorithm
                s1 += digit
            } else {//add 2 * digit for 0-4, add 2 * digit - 9 for 5-9
                s2 += 2 * digit
                if (digit >= 5) {
                    s2 -= 9
                }
            }
        }
        return (s1 + s2) % 10 == 0
    }

}