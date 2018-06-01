package com.gurjinder.tandooriBackend.model;

import java.util.List;

public class Order {

    private int id;
    private long timeSubmitted;
    private long timeAccepted;
    private boolean accepted;
    private int customerId;

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public List<Integer> getOrderedItemsId() {
        return orderedItemsId;
    }

    public void setOrderedItemsId(List<Integer> orderedItemsId) {
        this.orderedItemsId = orderedItemsId;
    }

    List<Integer> orderedItemsId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getTimeSubmitted() {
        return timeSubmitted;
    }

    public void setTimeSubmitted(long timeSubmitted) {
        this.timeSubmitted = timeSubmitted;
    }

    public long getTimeAccepted() {
        return timeAccepted;
    }

    public void setTimeAccepted(long timeAccepted) {
        this.timeAccepted = timeAccepted;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }


}
