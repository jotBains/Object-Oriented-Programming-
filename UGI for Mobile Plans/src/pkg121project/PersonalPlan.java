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
public class PersonalPlan extends Plan implements Serializable {

    protected String city;

    public PersonalPlan(String userName, int id, MobilePhone handset, int internetQuota, int capLimit, MyDate date, String city) {
        super(userName, id, handset, internetQuota, capLimit, date);
        this.city = city;

    }

    @Override
    public void print() {
        super.print();
        System.out.println(" City: " + city);
    }

    @Override
    public String toString() {
        return super.toString() + " City: " + city;
    }

    public void setciti(String City) {
        this.city = City;
    }

    public String toDelimitedString() {
        return "PP" + "," + userName + "," + id + "," + handset.toDelimitedString() + "," + internetQuota + "," + capLimit + "," + expiryDate.toDelimitedString() + "," + city;
    }

    @Override
    public double calMonthlPay(int flatRate) {
        return handset.getPrice() / 24 + capLimit / 20 + internetQuota * 5 + flatRate;
    }
}
