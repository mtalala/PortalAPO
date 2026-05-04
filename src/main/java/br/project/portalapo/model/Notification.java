package br.project.portalapo.model;

public class Notification {

    private String id;
    private String role;
    private String topic;
    private String requestColor;
    private String createdAt;
    private boolean read;

    public Notification() {}

    public Notification(String id, String role, String topic, String requestColor, String createdAt, boolean read) {
        this.id = id;
        this.role = role;
        this.topic = topic;
        this.requestColor = requestColor;
        this.createdAt = createdAt;
        this.read = read;
    }

    public String getId() {
        return id;
    }

    public String getRole() {
        return role;
    }

    public String getTopic() {
        return topic;
    }

    public String getRequestColor() {
        return requestColor;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public boolean isRead() {
        return read;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public void setRequestColor(String requestColor) {
        this.requestColor = requestColor;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setRead(boolean read) {
        this.read = read;
    }
}