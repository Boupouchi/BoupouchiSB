package com.boupouchi.test.services;


import com.boupouchi.test.dao.IDao;
import com.boupouchi.test.entities.Article;
import com.boupouchi.test.entities.Categorie;
import com.boupouchi.test.repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class ArticleService implements IDao<Article> {

    @Autowired
    ArticleRepository repository;
    @Override
    public Article create(Article o) {
        return repository.save(o);
    }

    @Override
    public List<Article> findAll() {
        return repository.findAll();
    }

    @Override
    public Article update(Article o) {
        return repository.save(o);
    }

    @Override
    public boolean delete(Article o) {
        try {
            repository.delete(o);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Article findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public List<Article> findArticleByCategorie(Categorie categorie) {
        return repository.findArticleByCategorie(categorie);
    }

    public List<Article> findByDateEmbaucheBetween(Date dateDebut, Date dateFin) {
        return repository.findByDateEmbaucheBetween(dateDebut, dateFin);
    }

}
