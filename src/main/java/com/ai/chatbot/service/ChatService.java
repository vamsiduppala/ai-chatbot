package com.ai.chatbot.service;

import com.ai.chatbot.model.ChatRequest;
import com.ai.chatbot.model.ChatResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class ChatService {

    @Value("${gemini.api.key}")
    private String apiKey;

    private static final String GEMINI_API_URL = "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent";

    public ChatResponse getBotResponse(ChatRequest request) {
        RestTemplate restTemplate = new RestTemplate();

        // Construct the request body according to Gemini API format
        Map<String, Object> payload = new HashMap<>();
        Map<String, Object> part = new HashMap<>();
        part.put("text", request.getMessage());

        Map<String, Object> content = new HashMap<>();
        content.put("parts", new Object[]{part});

        payload.put("contents", new Object[]{content});

        // Set headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(payload, headers);

        try {
            // Build full URL with API key
            String fullUrl = GEMINI_API_URL + "?key=" + apiKey;

            // Send POST request
            ResponseEntity<Map> response = restTemplate.postForEntity(fullUrl, entity, Map.class);

            // Parse response to get the bot reply
            Map<String, Object> responseBody = response.getBody();
            if (responseBody != null &&
                responseBody.containsKey("candidates") &&
                ((java.util.List<?>) responseBody.get("candidates")).size() > 0) {

                Map<String, Object> firstCandidate = (Map<String, Object>) ((java.util.List<?>) responseBody.get("candidates")).get(0);
                Map<String, Object> message = (Map<String, Object>) firstCandidate.get("content");
                java.util.List<?> parts = (java.util.List<?>) message.get("parts");
                if (parts.size() > 0) {
                    Map<String, String> partMap = (Map<String, String>) parts.get(0);
                    String replyText = partMap.get("text");
                    return new ChatResponse(replyText);
                }
            }

            return new ChatResponse("No response from Gemini model.");
        } catch (HttpClientErrorException e) {
            System.err.println("Error Code: " + e.getStatusCode());
            System.err.println("Error Body: " + e.getResponseBodyAsString());
            throw new RuntimeException("Gemini API request failed: " + e.getMessage(), e);
        }
    }
}
