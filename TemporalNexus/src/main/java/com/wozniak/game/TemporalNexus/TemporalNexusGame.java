package com.wozniak.game.TemporalNexus;

import java.io.IOException;

import com.wozniak.game.TemporalNexus.models.events.EventScenario;
import com.wozniak.game.TemporalNexus.utils.EventUtils;

public class TemporalNexusGame {
    public static void main(String[] args) {
        String jsonFilePath = "C:\\Users\\Exagg\\coding\\java projects\\TemporalNexus\\src\\main\\resources\\eventJsons\\exampleEventJSON.json"; // Replace with the actual JSON file path

        try {
            EventScenario eventScenario = EventUtils.createEventScenarioFromJson(jsonFilePath);
            // Use the eventScenario object as needed
            System.out.println(eventScenario.getCurrentScene());
            System.out.println(eventScenario.getChoices());
        } catch (IOException e) {
            System.out.println("Error reading JSON file: " + e.getMessage());
        }
    }
}
