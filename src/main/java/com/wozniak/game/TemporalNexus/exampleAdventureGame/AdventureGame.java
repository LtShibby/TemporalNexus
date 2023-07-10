package com.wozniak.game.TemporalNexus.exampleAdventureGame;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AdventureGame {
    private final Scanner scanner;
    private final Map<String, AdventureScene> sceneMap;
    private AdventureScene currentScene;

    public AdventureGame() {
        this.scanner = new Scanner(System.in);
        this.sceneMap = new HashMap<>();
        this.currentScene = null;
    }

    public void startGame() {
        boolean gameRunning = true;

        while (gameRunning) {
            if (currentScene != null) {
                // Display the current scene and options to the player
                displayScene();
                displayOptions();

                // Get the player's choice
                String choice = scanner.nextLine();

                // Process the player's choice
                handleChoice(choice);

                System.out.println(); // Add a newline for better readability
            } else {
                System.out.println("The game is over. Thanks for playing!");
                gameRunning = false;
            }
        }
    }

    private void displayScene() {
        System.out.println(currentScene.getDescription());
    }

    private void displayOptions() {
        System.out.println("Options:");
        for (AdventureChoice choice : currentScene.getChoices()) {
            System.out.println(choice.getChoiceText());
        }
        System.out.print("Enter your choice: ");
    }

    private void handleChoice(String choice) {
        if (currentScene.getChoices().isEmpty()) {
            // Handle scene with no choices
            System.out.println("There are no options available. Proceeding to the default scene.");
            currentScene = sceneMap.get("defaultScene");
            return;
        }

        for (AdventureChoice adventureChoice : currentScene.getChoices()) {
            if (adventureChoice.getChoiceText().equalsIgnoreCase(choice)) {
                currentScene = sceneMap.get(adventureChoice.getNextScene());
                return;
            }
        }
        System.out.println("Invalid choice. Please try again.");
    }

    public void addScene(String sceneId, AdventureScene scene) {
        sceneMap.put(sceneId, scene);
    }

    public void setCurrentScene(String sceneId) {
        currentScene = sceneMap.get(sceneId);
    }

    public static void main(String[] args) {
        AdventureGame game = new AdventureGame();

        // Define the scenes and choices

        // Scene 1
        AdventureScene scene1 = new AdventureScene("You are in a dark room.");
        AdventureChoice choice1a = new AdventureChoice("Open the door", "scene2");
        AdventureChoice choice1b = new AdventureChoice("Look for a light switch", "scene3");
        scene1.addChoice(choice1a);
        scene1.addChoice(choice1b);

        // Scene 2
        AdventureScene scene2 = new AdventureScene("You opened the door and stepped into a hallway.");
        AdventureChoice choice2a = new AdventureChoice("Go left", "scene4");
        AdventureChoice choice2b = new AdventureChoice("Go right", "scene5");
        scene2.addChoice(choice2a);
        scene2.addChoice(choice2b);

        // Scene 3
        AdventureScene scene3 = new AdventureScene("You found a light switch and turned on the lights.");
        AdventureChoice choice3a = new AdventureChoice("Explore the room", "scene6");
        scene3.addChoice(choice3a);

        // Scene 4
        AdventureScene scene4 = new AdventureScene("You encounter a monster!");
        AdventureChoice choice4a = new AdventureChoice("Attack the monster", "scene7");
        AdventureChoice choice4b = new AdventureChoice("Run away", "scene8");
        scene4.addChoice(choice4a);
        scene4.addChoice(choice4b);

        // Scene 5
        AdventureScene scene5 = new AdventureScene("You found a treasure chest!");
        AdventureChoice choice5a = new AdventureChoice("Open the chest", "scene9");
        scene5.addChoice(choice5a);

        // Scene 6
        AdventureScene scene6 = new AdventureScene("You discover a hidden passage.");
        AdventureChoice choice6a = new AdventureChoice("Enter the passage", "scene10");
        scene6.addChoice(choice6a);

        // Scene 7
        AdventureScene scene7 = new AdventureScene("You defeat the monster and continue on your journey.");
        AdventureChoice choice7a = new AdventureChoice("Continue", "scene11");
        scene7.addChoice(choice7a);

        // Scene 8
        AdventureScene scene8 = new AdventureScene("You run away from the monster and find a shortcut.");
        AdventureChoice choice8a = new AdventureChoice("Take the shortcut", "scene12");
        scene8.addChoice(choice8a);

        // Scene 9
        AdventureScene scene9 = new AdventureScene("You open the chest and find a valuable artifact.");
        AdventureChoice choice9a = new AdventureChoice("Take the artifact", "scene13");
        scene9.addChoice(choice9a);

        // Scene 10
        AdventureScene scene10 = new AdventureScene("You follow the hidden passage and discover a secret chamber.");
        AdventureChoice choice10a = new AdventureChoice("Investigate the chamber", "scene14");
        scene10.addChoice(choice10a);

        // Scene 11
        AdventureScene scene11 = new AdventureScene("You continue your journey through the mysterious land.");
        scene11.setEndScene(true);

        // Scene 12
        AdventureScene scene12 = new AdventureScene("You take the shortcut and reach your destination quickly.");
        scene12.setEndScene(true);

        // Scene 13
        AdventureScene scene13 = new AdventureScene("You take the valuable artifact and become rich!");
        scene13.setEndScene(true);

        // Scene 14
        AdventureScene scene14 = new AdventureScene("You uncover ancient treasures and become a legendary explorer!");
        scene14.setEndScene(true);

        // Add the scenes to the game
        game.addScene("scene1", scene1);
        game.addScene("scene2", scene2);
        game.addScene("scene3", scene3);
        game.addScene("scene4", scene4);
        game.addScene("scene5", scene5);
        game.addScene("scene6", scene6);
        game.addScene("scene7", scene7);
        game.addScene("scene8", scene8);
        game.addScene("scene9", scene9);
        game.addScene("scene10", scene10);
        game.addScene("scene11", scene11);
        game.addScene("scene12", scene12);
        game.addScene("scene13", scene13);
        game.addScene("scene14", scene14);

        // Set the starting scene
        game.setCurrentScene("scene1");

        AdventureScene defaultScene = new AdventureScene(
                "There are no options available. You proceed to the default scene.");
        defaultScene.setEndScene(true);
        game.sceneMap.put("defaultScene", defaultScene);

        // Start the game
        game.startGame();
    }
}
