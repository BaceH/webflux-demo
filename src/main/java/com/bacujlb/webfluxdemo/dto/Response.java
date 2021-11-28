package com.bacujlb.webfluxdemo.dto;

import java.util.Date;

public class Response {
    private Date date = new Date();
    private int output;

    public Response() {
    }

    public Response(int output) {
        this.output = output;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getOutput() {
        return output;
    }

    public void setOutput(int output) {
        this.output = output;
    }

    @Override
    public String toString() {
        return "Response{" +
                "date=" + date +
                ", output=" + output +
                '}';
    }
}
