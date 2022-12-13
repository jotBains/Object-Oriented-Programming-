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
enum MobileType {
    Android,
    IOS,
    Windows
}

public class MobilePhone implements Cloneable, Serializable {

    private String model;
    protected MobileType type;
    protected int memorySize;
    protected double price;

    public MobilePhone(String model, MobileType type, int memorySize, double price) {
        this.model = model;
        this.type = type;
        this.memorySize = memorySize;
        this.price = price;
    }

    public MobilePhone(MobilePhone mobilePhone) {
        this.model = mobilePhone.model;
        this.type = mobilePhone.type;
        this.memorySize = mobilePhone.memorySize;
        this.price = mobilePhone.price;
    }

    public void print() {
        System.out.print("Model: " + model + ", MobileType: " + type + ", MemorySize: " + memorySize + ", Price: " + price);
    }

    @Override
    public String toString() {
        return "Model: " + model + ", MobileType: " + type + ", MemorySize: " + memorySize + ", Price: " + price;
    }

    public String toDelimitedString() {
        return model + "," + type + "," + memorySize + "," + price;
    }

    public String getModel() {
        return model;
    }

    public String setModel(String newModel) {
        return model = newModel;
    }

    public double getPrice() {
        return price;
    }

    public MobileType getType() {
        return type;
    }

    public int getSize() {
        return memorySize;
    }

    public double setPrice(double newPrice) {
        return price = newPrice;
    }

    public double priceRise(double rise) {
        return price *= (1 + rise);
    }

    @Override
    public MobilePhone clone() throws CloneNotSupportedException {
        return (MobilePhone) super.clone();
    }
}
