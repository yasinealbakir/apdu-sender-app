package com.example.mathservice;

public class Response {
    private int s1;
    private int s2;
    private int result;

    public Response(int s1, int s2, int result) {
        this.s1 = s1;
        this.s2 = s2;
        this.result = result;
    }

    public int getS1() {
        return s1;
    }

    public int getS2() {
        return s2;
    }

    public int getResult() {
        return result;
    }
}
