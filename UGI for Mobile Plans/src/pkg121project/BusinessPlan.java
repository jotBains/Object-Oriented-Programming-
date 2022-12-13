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
public class BusinessPlan extends Plan implements Serializable {

    protected int numberOfEmployess;
    protected int ABN;

    public BusinessPlan(String userName, int id, MobilePhone handset, int internetQuota, int caplimit, MyDate myDate, int numberOfEmployess, int ABN) {
        super(userName, id, handset, internetQuota, caplimit, myDate);
        this.ABN = ABN;
        this.numberOfEmployess = numberOfEmployess;
    }

    //copy constructor
    @Override
    public void print() {
        super.print();
        System.out.print(" NumberOfEmployess: " + numberOfEmployess + ", ABN: " + ABN);
    }

    @Override
    public String toString() {
        return super.toString() + " NumberOfEmployess: " + numberOfEmployess + ", ABN: " + ABN;
    }

    public String toDelimitedString() {
        return "BP" + "," + userName + "," + id + "," + handset.toDelimitedString() + "," + internetQuota + "," + capLimit + "," + expiryDate.toDelimitedString() + "," + numberOfEmployess + "," + ABN;
    }

    @Override
    public double calMonthlPay(int flatRate) {
        if (numberOfEmployess > 10) {

            return handset.getPrice() / 24 + capLimit / 10 + internetQuota * 10 + flatRate + (numberOfEmployess - 10) * 50;
        } else {

            return handset.getPrice() / 24 + capLimit / 10 + internetQuota * 10 + flatRate;
        }
    }
}
