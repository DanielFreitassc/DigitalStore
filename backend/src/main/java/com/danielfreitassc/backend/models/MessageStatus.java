package com.danielfreitassc.backend.models;

public enum MessageStatus {
    SENT("Enviado"),
    DELETED("Apagado");

    String status;

    private MessageStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
}
