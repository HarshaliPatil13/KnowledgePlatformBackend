package com.knowledgeplatform.knowledgeplatform.DTOs;

import lombok.Data;

@Data
public class ArticleRequestDTO {

    private String title;
    private String category;
    private String content;
    private String tags;
}