package com.SoloProject.solo.models;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Table;

@Embeddable
@Table(name = "address")
public class Address {
    protected String line1;
    protected String line2;
    protected String city;
    protected String state;
    protected String postalCode;

    public Address() {}

    public Address(String line1, String line2, String city, String state, String postalCode){
        this.line1 = line1;
        this.line2 = line2;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
    }

    public String getLine1() {
        return line1;
    }

    public String getLine2() {
        return line2;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setLine1(String line1) {
        this.line1 = line1;
    }

    public void setLine2(String line2) {
        this.line2 = line2;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
