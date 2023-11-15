package com.boupouchi.test.controllers;
import com.boupouchi.test.entities.Categorie;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import com.boupouchi.test.entities.Article;
import com.boupouchi.test.services.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/article")
public class ArticleController implements Serializable {
    @Autowired
    private ArticleService service;

    @GetMapping
    public List<Article> findAllArticles() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        Article article = service.findById(id);
        if (article == null) {
            return new ResponseEntity<Object>("article avec Id " + id + " nexiste pas", HttpStatus.BAD_REQUEST);

        } else {
            return ResponseEntity.ok(article);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteArticle(@PathVariable Long id) {
        Article article = service.findById(id);
        if (article == null) {
            return new ResponseEntity<Object>("article avec Id " + id + " nexiste pas", HttpStatus.BAD_REQUEST);
        } else {
            service.delete(article);
            return ResponseEntity.ok("article avec id " + id + " suprime");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateArticle(@PathVariable Long id, @RequestBody Article newarticle) {

        Article oldart = service.findById(id);
        if (oldart == null) {
            return new ResponseEntity<Object>("article avec id" + id + "n existe pas ", HttpStatus.BAD_REQUEST);

        } else {
            newarticle.setId(id);
            return ResponseEntity.ok(service.update(newarticle));
        }
    }

    @PostMapping
    public Article createArticle(@RequestBody Article article) {
        article.setId(0L);
        return service.create(article);
    }

    @GetMapping("/categorie")
    public List<Article> findArticleByCategorie(@RequestBody Categorie categorie) {
        return service.findArticleByCategorie(categorie);
    }

    @GetMapping("/filterByDate")
    public List<Article> findByDateEmbaucheBetween(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateDebut, @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateFin) {
        return service.findByDateEmbaucheBetween(dateDebut, dateFin);
    }
}


