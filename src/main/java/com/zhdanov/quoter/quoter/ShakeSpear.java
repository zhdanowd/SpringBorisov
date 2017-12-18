package com.zhdanov.quoter.quoter;

public class ShakeSpear implements Quoter {

    private String message;

    @Override
    public void sayQuote() {
        System.out.println(message);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }
}
