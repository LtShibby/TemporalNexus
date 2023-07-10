package com.wozniak.game.TemporalNexus.models.events;

import java.util.HashMap;
import java.util.Map;

public class EventScenario {
    private String id;
    private EventScene currentScene;
    private Map<String, String> choices;

    public EventScenario() {
        this.choices = new HashMap<>();
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setCurrentScene(EventScene currentScene) {
        this.currentScene = currentScene;
    }

    public EventScene getCurrentScene() {
        return currentScene;
    }

    public void addChoice(EventChoice choice, EventScenario nextScenario) {
        choices.put(choice.getChoiceText(), nextScenario.getId());
    }

    public Map<String, String> getChoices() {
        return choices;
    }
}
