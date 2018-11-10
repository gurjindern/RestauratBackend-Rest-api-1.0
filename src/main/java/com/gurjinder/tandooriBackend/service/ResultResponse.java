package com.gurjinder.tandooriBackend.service;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class ResultResponse<T> {

    private String status;
    private Date date;
    private T result;

    public ResultResponse() {
    }

    public ResultResponse(String status, Date date) {
        this.status = status;
        this.date = date;
    }

    public ResultResponse(String status, Date date, T result) {
        this.status = status;
        this.date = date;
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
