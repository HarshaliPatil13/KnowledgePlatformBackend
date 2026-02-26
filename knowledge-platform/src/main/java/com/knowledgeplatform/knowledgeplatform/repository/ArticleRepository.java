package com.knowledgeplatform.knowledgeplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import com.knowledgeplatform.knowledgeplatform.entity.Article;
import com.knowledgeplatform.knowledgeplatform.entity.User;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findByAuthor(User author);

    
    @Query("""
    	    SELECT a FROM Article a
    	    WHERE a.title LIKE CONCAT('%', :keyword, '%')
    	    OR a.content LIKE CONCAT('%', :keyword, '%')
    	    OR a.tags LIKE CONCAT('%', :keyword, '%')
    	""")
    	List<Article> searchArticles(@Param("keyword") String keyword);

   
    List<Article> findByCategoryIgnoreCase(String category);
}