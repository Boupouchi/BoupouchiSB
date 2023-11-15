package com.boupouchi.test.services;

import com.boupouchi.test.dao.IDao;
import com.boupouchi.test.entities.Categorie;
import com.boupouchi.test.repositories.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorieService implements IDao<Categorie> {
    @Autowired
    CategorieRepository categorieRepository;
    @Override
    public Categorie create(Categorie o) {
        return categorieRepository.save(o);
    }

    @Override
    public List<Categorie> findAll() {
        return categorieRepository.findAll();
    }

    @Override
    public Categorie update(Categorie o) {
        return categorieRepository.save(o);
    }

    @Override
    public boolean delete(Categorie o) {
        try {
            categorieRepository.delete(o);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    @Override
    public Categorie findById(Long id) {
        return categorieRepository.findById(id).orElse(null);
    }

}
