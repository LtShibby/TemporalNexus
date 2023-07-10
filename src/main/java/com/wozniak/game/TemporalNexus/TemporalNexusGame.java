package com.wozniak.game.TemporalNexus;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.wozniak.game.TemporalNexus.models.events.EventScenario;
import com.wozniak.game.TemporalNexus.utils.EventUtils;

public class TemporalNexusGame {
    public static void main(String[] args) {
        String configFile = "TemporalNexus\\config.properties";
        Properties configProperties = new Properties();

        try (FileInputStream fileInputStream = new FileInputStream(configFile)) {
            configProperties.load(fileInputStream);
        } catch (IOException e) {
            System.out.println("Error loading config.properties file: " + e.getMessage());
            return;
        }

        String folderPath = configProperties.getProperty("json.folderPath");
        List<EventScenario> eventScenarios = new ArrayList<>();

        File folder = new File(folderPath);
        if (folder.isDirectory()) {
            File[] files = folder.listFiles((dir, name) -> name.endsWith(".json"));
            if (files != null) {
                for (File file : files) {
                    try {
                        EventScenario eventScenario = EventUtils.createEventScenarioFromJson(file.getAbsolutePath());
                        eventScenarios.add(eventScenario);
                    } catch (IOException e) {
                        System.out.println("Error reading JSON file: " + file.getName());
                        e.printStackTrace();
                    }
                }
            }
        }

        // Use the eventScenarios list as needed
        for (EventScenario eventScenario : eventScenarios) {
            System.out.println("Scenario: " + eventScenario.getCurrentScene().getDescription());
            System.out.println("Choices: " + eventScenario.getChoices());
            System.out.println();
        }
    }
}
