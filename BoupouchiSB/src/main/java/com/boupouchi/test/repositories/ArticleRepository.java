package com.boupouchi.test.repositories;

import com.boupouchi.test.entities.Article;
import com.boupouchi.test.entities.Categorie;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findArticleByCategorie(Categorie categorie);

    List<Article> findByDateEmbaucheBetween(Date dateDebut, Date dateFin);
}
