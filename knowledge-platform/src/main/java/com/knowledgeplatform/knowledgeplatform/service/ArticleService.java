package com.knowledgeplatform.knowledgeplatform.service;

import java.util.List;
import com.knowledgeplatform.knowledgeplatform.DTOs.ArticleResponseDTO;
import com.knowledgeplatform.knowledgeplatform.entity.Article;

public interface ArticleService {

    ArticleResponseDTO createArticle(Article article);

    List<ArticleResponseDTO> getAllArticles();

    List<ArticleResponseDTO> getMyArticles();

    ArticleResponseDTO getArticleById(Long id);

    ArticleResponseDTO updateArticle(Long id, Article updatedArticle);

    void deleteArticle(Long id);
    List<ArticleResponseDTO> searchArticles(String keyword);

    List<ArticleResponseDTO> filterByCategory(String category);
}