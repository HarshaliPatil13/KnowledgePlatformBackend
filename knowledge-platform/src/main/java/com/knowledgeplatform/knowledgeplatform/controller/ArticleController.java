package com.knowledgeplatform.knowledgeplatform.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.knowledgeplatform.knowledgeplatform.DTOs.ArticleResponseDTO;
import com.knowledgeplatform.knowledgeplatform.entity.Article;
import com.knowledgeplatform.knowledgeplatform.service.ArticleService;

@RestController
@RequestMapping("/api/articles")
@RequiredArgsConstructor
public class ArticleController {

    private final ArticleService articleService;

    @PostMapping
    public ArticleResponseDTO createArticle(@RequestBody Article article) {
        return articleService.createArticle(article);
    }

    @GetMapping
    public List<ArticleResponseDTO> getAllArticles() {
        return articleService.getAllArticles();
    }

    @GetMapping("/my")
    public List<ArticleResponseDTO> getMyArticles() {
        return articleService.getMyArticles();
    }

    @GetMapping("/{id}")
    public ArticleResponseDTO getArticleById(@PathVariable Long id) {
        return articleService.getArticleById(id);
    }

    @PutMapping("/{id}")
    public ArticleResponseDTO updateArticle(
            @PathVariable Long id,
            @RequestBody Article updatedArticle) {

        return articleService.updateArticle(id, updatedArticle);
    }

    @DeleteMapping("/{id}")
    public void deleteArticle(@PathVariable Long id) {
        articleService.deleteArticle(id);
    }
    
 
    @GetMapping("/search")
    public List<ArticleResponseDTO> searchArticles(
            @RequestParam String keyword) {

        return articleService.searchArticles(keyword);
    }

    
    @GetMapping("/category")
    public List<ArticleResponseDTO> filterByCategory(
            @RequestParam String category) {

        return articleService.filterByCategory(category);
    }
}