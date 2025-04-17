package com.ai.chatbot.model;

public class ChatRequest {
    private String message;
    // Add userId, sessionId if needed

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
}