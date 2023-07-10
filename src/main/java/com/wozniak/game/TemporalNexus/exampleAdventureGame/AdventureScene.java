package com.wozniak.game.TemporalNexus.exampleAdventureGame;

import java.util.ArrayList;
import java.util.List;

public class AdventureScene {
    private final String description;
    private final List<AdventureChoice> choices;
    private boolean endScene;

    public AdventureScene(String description) {
        this.description = description;
        this.choices = new ArrayList<>();
        this.endScene = false;
    }

    public String getDescription() {
        return description;
    }

    public List<AdventureChoice> getChoices() {
        return choices;
    }

    public void addChoice(AdventureChoice choice) {
        choices.add(choice);
    }

    public boolean isEndScene() {
        return endScene;
    }

    public void setEndScene(boolean endScene) {
        this.endScene = endScene;
    }
}

