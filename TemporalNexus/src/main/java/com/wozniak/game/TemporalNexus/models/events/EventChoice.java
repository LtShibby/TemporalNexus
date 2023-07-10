package com.wozniak.game.TemporalNexus.models.events;

public class EventChoice {
    private String choiceText;
    private String nextScenarioId;

    public EventChoice() {
    }

    public EventChoice(String choiceText, String nextScenarioId) {
        this.choiceText = choiceText;
        this.nextScenarioId = nextScenarioId;
    }

    public String getChoiceText() {
        return choiceText;
    }

    public void setChoiceText(String choiceText) {
        this.choiceText = choiceText;
    }

    public String getNextScenarioId() {
        return nextScenarioId;
    }

    public void setNextScenarioId(String nextScenarioId) {
        this.nextScenarioId = nextScenarioId;
    }
}
