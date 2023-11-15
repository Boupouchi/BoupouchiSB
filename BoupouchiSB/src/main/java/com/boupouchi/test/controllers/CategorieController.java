package com.boupouchi.test.controllers;

import org.springframework.http.ResponseEntity;
import com.boupouchi.test.entities.Categorie;
import com.boupouchi.test.services.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping("/api/v1/categorie")
public class CategorieController implements Serializable {
    @Autowired
    private CategorieService service;

    @GetMapping
    public List<Categorie> findAllCategories() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable Long id) {
        Categorie categorie = service.findById(id);
        if (categorie == null) {
            return new ResponseEntity<Object>("categorie avec Id " + id + " nexiste pas", HttpStatus.BAD_REQUEST);

        } else {
            return ResponseEntity.ok(categorie);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCategorie(@PathVariable Long id) {
        Categorie categorie= service.findById(id);
        if (categorie== null) {
            return new ResponseEntity<Object>("catgorie avec Id " + id + " nexiste pas", HttpStatus.BAD_REQUEST);
        } else {
            service.delete(categorie);
            return ResponseEntity.ok("categorie avec id " + id + " suprime");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateCategorie(@PathVariable Long id, @RequestBody Categorie newcat) {

        Categorie oldcat = service.findById(id);
        if (oldcat == null) {
            return new ResponseEntity<Object>("categorie avec id" + id + "n existe pas ", HttpStatus.BAD_REQUEST);

        } else {
            newcat.setId(id);
            return ResponseEntity.ok(service.update(newcat));
        }
    }

    @PostMapping
    public Categorie createCategorie(@RequestBody Categorie categorie) {
        categorie.setId(0L);
        return service.create(categorie);
    }
}

