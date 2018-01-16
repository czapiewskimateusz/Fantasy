package com.example.mateusz.fantasy.home.model.repo;



public class ServerResponse {
    public static final String MESSAGE_SUCCESS = "success";
    public static final String MESSAGE_FAILURE = "failure";

    private String message;

    public ServerResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
