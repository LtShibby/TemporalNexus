package com.wozniak.game.TemporalNexus.models.events;

public class EventScene {
    private String id;
    private String description;

    public EventScene() {
        // Default constructor for deserialization
    }

    public EventScene(String id, String description) {
        this.id = id;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
