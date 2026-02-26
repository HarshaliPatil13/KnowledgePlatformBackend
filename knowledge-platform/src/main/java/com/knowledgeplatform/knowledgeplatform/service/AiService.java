package com.knowledgeplatform.knowledgeplatform.service;



public interface AiService {

    String generateSummary(String content);

    String improveContent(String content);
    
    String suggestTags(String content);

}