package com.knowledgeplatform.knowledgeplatform.serviceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import com.knowledgeplatform.knowledgeplatform.DTOs.ArticleResponseDTO;
import com.knowledgeplatform.knowledgeplatform.entity.Article;
import com.knowledgeplatform.knowledgeplatform.entity.User;
import com.knowledgeplatform.knowledgeplatform.repository.ArticleRepository;
import com.knowledgeplatform.knowledgeplatform.repository.UserRepository;
import com.knowledgeplatform.knowledgeplatform.service.AiService;
import com.knowledgeplatform.knowledgeplatform.service.ArticleService;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl implements ArticleService {

    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;  
    private final AiService aiService;

    
    private User getCurrentUser() {

        Object principal = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        if (principal instanceof String) {

            String email = (String) principal;

            return userRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("User not found"));
        }

        throw new RuntimeException("Invalid authentication principal");
    }

    @Override
    public ArticleResponseDTO createArticle(Article article) {

        User currentUser = getCurrentUser();
        article.setAuthor(currentUser);

        String summary = aiService.generateSummary(article.getContent());
        article.setSummary(summary);

        Article saved = articleRepository.save(article);

        return mapToDTO(saved);
    }

    @Override
    public List<ArticleResponseDTO> getAllArticles() {
        return articleRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ArticleResponseDTO> getMyArticles() {

        User currentUser = getCurrentUser();

        return articleRepository.findByAuthor(currentUser)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ArticleResponseDTO getArticleById(Long id) {

        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Article not found"));

        return mapToDTO(article);
    }

    @Override
    public ArticleResponseDTO updateArticle(Long id, Article updatedArticle) {

        Article existing = articleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Article not found"));

        User currentUser = getCurrentUser();

        if (!existing.getAuthor().getId().equals(currentUser.getId())) {
            throw new RuntimeException("You are not allowed to edit this article");
        }

        existing.setTitle(updatedArticle.getTitle());
        existing.setContent(updatedArticle.getContent());
        existing.setCategory(updatedArticle.getCategory());
        existing.setTags(updatedArticle.getTags());

        Article saved = articleRepository.save(existing);

        return mapToDTO(saved);
    }

    @Override
    public void deleteArticle(Long id) {

        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Article not found"));

        User currentUser = getCurrentUser();

        if (!article.getAuthor().getId().equals(currentUser.getId())) {
            throw new RuntimeException("You are not allowed to delete this article");
        }

        articleRepository.delete(article);
    }

    private ArticleResponseDTO mapToDTO(Article article) {

        ArticleResponseDTO dto = new ArticleResponseDTO();

        dto.setId(article.getId());
        dto.setTitle(article.getTitle());
        dto.setCategory(article.getCategory());
        dto.setContent(article.getContent());
        dto.setSummary(article.getSummary());
        dto.setTags(article.getTags());
        dto.setPublished(article.isPublished());
        dto.setCreatedAt(article.getCreatedAt());
        dto.setUpdatedAt(article.getUpdatedAt());

        dto.setAuthorId(article.getAuthor().getId());
        dto.setAuthorName(article.getAuthor().getUsername());

        return dto;
    }
    
    @Override
    public List<ArticleResponseDTO> searchArticles(String keyword) {

        return articleRepository.searchArticles(keyword)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ArticleResponseDTO> filterByCategory(String category) {

        return articleRepository.findByCategoryIgnoreCase(category)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
}