package com.knowledgeplatform.knowledgeplatform.controller;



import lombok.RequiredArgsConstructor;

import java.util.Map;

import org.springframework.web.bind.annotation.*;

import com.knowledgeplatform.knowledgeplatform.service.AiService;

@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
public class AiController {

	 private final AiService aiService;

	    @PostMapping("/improve")
	    public String improve(@RequestBody Map<String, String> request) {
	        return aiService.improveContent(request.get("content"));
	    }

	    @PostMapping("/suggest-tags")
	    public String suggestTags(@RequestBody Map<String, String> request) {
	        return aiService.suggestTags(request.get("content"));
	    }
	    @PostMapping("/summary")
	    public String generateSummary(@RequestBody Map<String, String> request) {
	        return aiService.generateSummary(request.get("content"));
	    }
	}