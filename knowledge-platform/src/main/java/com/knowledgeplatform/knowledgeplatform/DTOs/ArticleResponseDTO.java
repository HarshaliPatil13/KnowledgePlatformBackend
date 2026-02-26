package com.knowledgeplatform.knowledgeplatform.DTOs;

import lombok.Data;

import java.time.Instant;
import java.time.LocalDateTime;

@Data
public class ArticleResponseDTO {

    private Long id;
    private String title;
    private String category;
    private String content;
    private String summary;
    private String tags;
    private boolean published;
    private Instant createdAt;
    private Instant updatedAt;

    private Long authorId;
    private String authorName;
}