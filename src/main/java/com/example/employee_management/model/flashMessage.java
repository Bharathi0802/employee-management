package com.example.employee_management.model;

public class flashMessage {
	private String message;
    private String type;

    public flashMessage(String message, String type) {
        this.message = message;
        this.type = type;
    }

    // Getters and setters
    public String getMessage() {
        return message;
    }

    public String getType() {
        return type;
    }

}
