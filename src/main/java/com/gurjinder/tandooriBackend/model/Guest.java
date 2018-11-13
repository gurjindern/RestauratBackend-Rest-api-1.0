package com.gurjinder.tandooriBackend.model;

public class Guest extends User {
    private Address address;

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

}
