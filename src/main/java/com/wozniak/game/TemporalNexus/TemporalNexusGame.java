package com.wozniak.game.TemporalNexus;

import com.wozniak.game.TemporalNexus.models.events.EventChoice;
import com.wozniak.game.TemporalNexus.models.events.EventScenario;
import com.wozniak.game.TemporalNexus.utils.EventUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class TemporalNexusGame {

    private static final String CONFIG_FILE = "TemporalNexus\\config.properties";
    private static final String JSON_FOLDER_PATH_KEY = "json.folderPath";
    private static final String EXIT_CHOICE = "exit";

    private Properties configProperties;
    private List<EventScenario> eventScenarios;
    private EventScenario currentEventScenario;

    public static void main(String[] args) {
        TemporalNexusGame game = new TemporalNexusGame();
        game.start();
    }

    public void start() {
        if (!loadConfigProperties()) {
            return;
        }

        if (!loadEventScenarios()) {
            return;
        }

        setCurrentEventScenario(eventScenarios.get(0));

        Scanner scanner = new Scanner(System.in);
        while (currentEventScenario != null) {
            System.out.println("Scene: " + currentEventScenario.getCurrentScene().getDescription());
            displayChoices();

            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase(EXIT_CHOICE)) {
                break;
            }

            EventScenario nextEventScenario = getNextEventScenario(choice);
            if (nextEventScenario != null) {
                setCurrentEventScenario(nextEventScenario);
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private boolean loadConfigProperties() {
        configProperties = new Properties();
        try (FileInputStream fileInputStream = new FileInputStream(CONFIG_FILE)) {
            configProperties.load(fileInputStream);
            return true;
        } catch (IOException e) {
            System.out.println("Error loading config.properties file: " + e.getMessage());
            return false;
        }
    }

    private boolean loadEventScenarios() {
        String folderPath = configProperties.getProperty(JSON_FOLDER_PATH_KEY);
        eventScenarios = new ArrayList<>();

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

        return !eventScenarios.isEmpty();
    }

    private void setCurrentEventScenario(EventScenario eventScenario) {
        currentEventScenario = eventScenario;
    }

    private void displayChoices() {
        Map<String, String> choices = currentEventScenario.getChoices();
        for (Map.Entry<String, String> entry : choices.entrySet()) {
            System.out.println("- " + entry.getKey());
        }
        System.out.println("- " + EXIT_CHOICE);
    }

    private EventScenario getNextEventScenario(String choice) {
        Map<String, String> choices = currentEventScenario.getChoices();
        String nextScenarioId = choices.get(choice);
        if (nextScenarioId != null) {
            return findEventScenarioById(nextScenarioId);
        }
        return null;
    }

    private EventScenario findEventScenarioById(String scenarioId) {
        for (EventScenario eventScenario : eventScenarios) {
            if (eventScenario.getId().equals(scenarioId)) {
                return eventScenario;
            }
        }
        return null;
    }
}
