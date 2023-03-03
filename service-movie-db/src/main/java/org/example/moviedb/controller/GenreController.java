package org.example.moviedb.controller;

import org.example.moviedb.dto.GenreDto;
import org.example.moviedb.model.Genre;
import org.example.moviedb.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/genres", produces = "application/json")
public class GenreController {
    @Autowired
    GenreService genreService;

    @PostMapping
    public ResponseEntity<Genre> create(@RequestBody GenreDto genreDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(genreService.save(genreDto));
    }

    @GetMapping
    public List<Genre> getAll() {
        return genreService.findAll();
    }

    @GetMapping("/{id}")
    public Genre getOne(@PathVariable Long id) {
        return genreService.findById(id);
    }

    @PutMapping("/{id}")
    public Genre updateById(@PathVariable Long id, @RequestBody GenreDto genreDto) {
        return genreService.updateById(id, genreDto);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        genreService.deleteById(id);
    }
}
