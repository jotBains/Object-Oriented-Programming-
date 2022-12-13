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
public class Address implements Cloneable, Serializable, Comparable<Address> {

    private int streetNum;
    private String street;
    private String suburb;
    private String city;

    public Address(int streetNum, String street, String suburb, String city) {
        this.city = city;
        this.street = street;
        this.streetNum = streetNum;
        this.suburb = suburb;
    }

    public Address(Address address) {
        this.city = address.city;
        this.street = address.street;
        this.streetNum = address.streetNum;
        this.suburb = address.suburb;
    }

    public Address clone() throws CloneNotSupportedException {
        return (Address) super.clone();
    }

    public void print() {
        System.out.println("Street Number: " + streetNum + " Street: " + street + " suburb: " + suburb + " City: " + city);
    }

    public String toString() {
        return "Street Number: " + streetNum + " Street: " + street + " suburb: " + suburb + " City: " + city;
    }

    public String toDelimitedString() {
        return streetNum + "," + street + "," + suburb + "," + city;
    }

    public void setCity(String City) {
        this.city = City;
    }

    public void setStreet(String Street) {
        this.street = Street;
    }

    public void setSuburb(String Suburb) {
        this.suburb = Suburb;
    }

    public void setStreetNum(int StreetNum) {
        this.streetNum = StreetNum;
    }

    public String getCity() {
        return city;
    }

    public String getSuburb() {
        return suburb;
    }

    public String getStreet() {
        return street;
    }

    public int getStreetNum() {
        return streetNum;
    }

    public int compareTo(Address otherCity) {
        return city.compareTo(otherCity.city);
    }

}
