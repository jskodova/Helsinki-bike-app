package com.solita.bikeapp.message;

public class Response {
    private String message;

    public Response(String message) {
        this.message = message;
    }

    public String getResponse() {
        return message;
    }

    public void setResponse(String message) {
        this.message = message;
    }
}
