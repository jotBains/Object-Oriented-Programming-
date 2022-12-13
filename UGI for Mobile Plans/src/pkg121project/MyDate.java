/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg121project;

import java.io.Serializable;

/**
 *
 * @author hp
 */
public class MyDate implements Cloneable, Serializable, Comparable<MyDate> {

    private int day;
    private int month;
    private int year;

    public MyDate(int day, int month, int year) {
        this.month = month;
        this.day = day;
        this.year = year;
    }

    public MyDate(MyDate date) {
        this.month = date.month;
        this.day = date.day;
        this.year = date.year;
    }

    public MyDate clone() throws CloneNotSupportedException {
        return (MyDate) super.clone();
    }

    public int getYear() {
        return year;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public String toString() {
        return day + "-" + month + "-" + year;
    }

    public String toDelimitedString() {
        return day + "," + month + "," + year;
    }

    public boolean isExpired(MyDate expiryDate) {
        if (this.year > expiryDate.year) {
            return true;
        } else if (this.year == expiryDate.year) {
            if (this.month > expiryDate.month) {
                return true;
            } else if (this.month == expiryDate.month) {
                if (this.day >= expiryDate.day) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public int compareTo(MyDate comparedDate) {
        if (this.year > comparedDate.year) {
            return 1;
        } else if (this.year == comparedDate.year) {
            if (this.month > comparedDate.month) {
                return 1;
            } else if (this.month == comparedDate.month) {
                if (this.day > comparedDate.day) {
                    return 1;
                }
            } else if (this.day == comparedDate.day) {
                return 0;
            } else if (this.day < comparedDate.day) {
                return -1;
            } else {
                return -1;
            }
        }

        return -1;

    }
}
