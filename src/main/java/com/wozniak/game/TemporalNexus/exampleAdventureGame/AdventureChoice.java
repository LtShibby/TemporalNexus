package com.wozniak.game.TemporalNexus.exampleAdventureGame;

public class AdventureChoice {
    private final String choiceText;
    private final String nextScene;

    public AdventureChoice(String choiceText, String nextScene) {
        this.choiceText = choiceText;
        this.nextScene = nextScene;
    }

    public String getChoiceText() {
        return choiceText;
    }

    public String getNextScene() {
        return nextScene;
    }
}
