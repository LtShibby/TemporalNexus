package com.wozniak.game.TemporalNexus.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wozniak.game.TemporalNexus.models.events.EventScenario;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class EventUtils {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static EventScenario createEventScenarioFromJson(String jsonFilePath) throws IOException {
        String jsonString = readFileAsString(jsonFilePath);
        return createEventScenarioFromJsonString(jsonString);
    }

    public static EventScenario createEventScenarioFromJsonString(String jsonString) {
        try {
                return objectMapper.readValue(jsonString, EventScenario.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error parsing JSON: " + e.getMessage(), e);
        }
    }

    private static String readFileAsString(String filePath) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filePath)));
    }
}
