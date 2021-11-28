package com.bacujlb.webfluxdemo.dto;

public class MultiplyRequestDto {
    private int first;
    private int second;

    public int getFirst() {
        return first;
    }

    public void setFirst(int first) {
        this.first = first;
    }

    public int getSecond() {
        return second;
    }

    public void setSecond(int second) {
        this.second = second;
    }

    @Override
    public String toString() {
        return "MultiplyRequestDto{" +
                "first=" + first +
                ", second=" + second +
                '}';
    }
}
