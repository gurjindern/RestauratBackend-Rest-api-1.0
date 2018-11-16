package com.gurjinder.tandooriBackend.model;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

public class Customer extends RegisteredUser {

    private List<Address> address;
    private int numberOfOrders;

    public int getNumberOfOrders() {
        return numberOfOrders;
    }

    public void setNumberOfOrders(int numberOfOrders) {
        this.numberOfOrders = numberOfOrders;
    }

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "address=" + address +
                ", numberOfOrders=" + numberOfOrders +
                '}';
    }
}
