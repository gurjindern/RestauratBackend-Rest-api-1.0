package com.gurjinder.tandooriBackend.service;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class ResultResponse<T> {

    private String status;
    private Date timeStamp;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private T result;

    public ResultResponse() {
    }

    public ResultResponse(String status, Date timeStamp) {
        this.status = status;
        this.timeStamp = timeStamp;
    }

    public ResultResponse(String status, Date timeStamp, T result) {
        this.status = status;
        this.timeStamp = timeStamp;
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
