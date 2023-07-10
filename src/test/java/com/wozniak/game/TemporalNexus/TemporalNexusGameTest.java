package com.wozniak.game.TemporalNexus;

import com.wozniak.game.TemporalNexus.models.events.EventScenario;
import com.wozniak.game.TemporalNexus.utils.EventUtils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class TemporalNexusGameTest {

    @BeforeEach
    public void setup() throws IOException {

    }

    @Test
    public void testTemporalNexusGame() {
        String configFile = "C:\\Users\\Exagg\\coding\\git projects\\TemporalNexus\\TemporalNexus\\src\\test\\config.properties";
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

        // Invoke the main method of TemporalNexusGame
        TemporalNexusGame.main(new String[]{});

        // Assert that the expected number of EventScenario objects were created
        int expectedEventScenarioCount = 1;
        int actualEventScenarioCount = eventScenarios.size();
        Assertions.assertEquals(expectedEventScenarioCount, actualEventScenarioCount,
                "Number of EventScenario objects created should match the expected count");

        // Assert the details of the EventScenario objects if needed
        Assertions.assertEquals("Assassination of Julius Caesar", eventScenarios.get(0).getCurrentScene().getDescription());
    }
}
