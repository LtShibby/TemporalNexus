package com.wozniak.game.TemporalNexus;

import com.wozniak.game.TemporalNexus.models.events.EventScenario;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class TemporalNexusGameTest {

    private String testFolderPath;
    private List<EventScenario> eventScenarios;

    @BeforeEach
    public void setup() throws IOException {
        // Create a temporary test folder and populate it with sample JSON files
        testFolderPath = Files.createTempDirectory("TemporalNexusTest").toString();
        createSampleJsonFile("event1.json", "{\"scene\":{\"id\":\"scene1\",\"description\":\"You are in a dark room.\"},\"choices\":[]}");
        createSampleJsonFile("event2.json", "{\"scene\":{\"id\":\"scene2\",\"description\":\"You find yourself in a mysterious forest.\"},\"choices\":[]}");
        eventScenarios = new ArrayList<>();
    }

    private void createSampleJsonFile(String fileName, String jsonContent) throws IOException {
        Path filePath = Path.of(testFolderPath, fileName);
        Files.writeString(filePath, jsonContent, StandardOpenOption.CREATE);
    }

    @Test
    public void testTemporalNexusGame() {
        // Prepare the config.properties file with the test folder path
        String configFilePath = Path.of(testFolderPath, "config.properties").toString();
        Properties configProperties = new Properties();
        configProperties.setProperty("json.folderPath", testFolderPath);
        try {
            configProperties.store(Files.newOutputStream(Path.of(configFilePath)), "Unit test config.properties");
        } catch (IOException e) {
            Assertions.fail("Failed to create config.properties file: " + e.getMessage());
        }

        // Invoke the main method of TemporalNexusGame
        TemporalNexusGame.main(new String[]{});

        // Assert that the expected number of EventScenario objects were created
        int expectedEventScenarioCount = 2;
        int actualEventScenarioCount = eventScenarios.size();
        Assertions.assertEquals(expectedEventScenarioCount, actualEventScenarioCount,
                "Number of EventScenario objects created should match the expected count");

        // Assert the details of the EventScenario objects if needed
        Assertions.assertEquals("You are in a dark room.", eventScenarios.get(0).getCurrentScene().getDescription());
        Assertions.assertEquals("You find yourself in a mysterious forest.", eventScenarios.get(1).getCurrentScene().getDescription());
    }

    // Helper method to add EventScenario objects to the list
    private void addEventScenario(EventScenario eventScenario) {
        eventScenarios.add(eventScenario);
    }
}
