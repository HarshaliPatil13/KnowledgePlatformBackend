package com.knowledgeplatform.knowledgeplatform.serviceImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.knowledgeplatform.knowledgeplatform.service.AiService;

import lombok.RequiredArgsConstructor;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
@RequiredArgsConstructor
public class OpenAiServiceImpl implements AiService {

    @Value("${openai.api.key}")
    private String apiKey;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private String callOpenAI(String prompt) {

        String url = "https://api.openai.com/v1/chat/completions";

        RestTemplate restTemplate = new RestTemplate();

        Map<String, Object> requestBody = Map.of(
                "model", "gpt-4o-mini",
                "messages", new Object[]{
                        Map.of("role", "user", "content", prompt)
                },
                "temperature", 0.2
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> entity =
                new HttpEntity<>(requestBody, headers);

        ResponseEntity<String> response =
                restTemplate.postForEntity(url, entity, String.class);

        try {
            JsonNode root = objectMapper.readTree(response.getBody());
            return root.get("choices")
                    .get(0)
                    .get("message")
                    .get("content")
                    .asText()
                    .trim();
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse OpenAI response", e);
        }
    }

   
    @Override
    public String improveContent(String content) {

        String prompt = """
You are a professional technical editor.

Improve the following content:
- Fix grammar
- Make sentences clearer
- Make it slightly more concise
- DO NOT change meaning
- DO NOT add new information
- DO NOT add introduction sentences
- DO NOT add explanations
- Return ONLY the improved content

Content:
""" + content;

        return callOpenAI(prompt);
    }

   
    @Override
    public String suggestTags(String content) {

        String prompt = """
You are an expert content categorizer.

Based on the content below:
- Suggest exactly 5 short relevant tags
- Each tag should be 1 or 2 words
- Separate tags with commas
- Do NOT add explanations
- Return ONLY comma separated tags

Content:
""" + content;

        return callOpenAI(prompt);
    }
    @Override
    public String generateSummary(String content) {

        String prompt = """
    You are a professional content summarizer.

    Summarize the following content:
    - Keep it concise
    - Maximum 3 sentences
    - Preserve main idea
    - Do NOT add extra commentary
    - Do NOT add introduction like "Here is the summary"
    - Return ONLY the summary

    Content:
    """ + content;

        return callOpenAI(prompt);
    }
}