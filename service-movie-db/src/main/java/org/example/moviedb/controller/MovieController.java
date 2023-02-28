package org.example.moviedb.controller;

import org.example.moviedb.dto.MovieDto;
import org.example.moviedb.model.Actor;
import org.example.moviedb.model.Genre;
import org.example.moviedb.model.Movie;
import org.example.moviedb.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {
    @Autowired
    MovieService movieService;

    @PostMapping
    public ResponseEntity<Movie> create(@RequestBody MovieDto movieDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(movieService.save(movieDto));
    }

    @GetMapping
    public List<Movie> getAll() {
        return movieService.findAll();
    }

    @GetMapping("{id}")
    public Movie getById(@PathVariable Long id) {
        return movieService.findById(id);
    }

    @PutMapping("{id}")
    public Movie updateById(@PathVariable Long id, @RequestBody MovieDto movieDto) {
        return movieService.updateById(id, movieDto);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id) {
        movieService.deleteById(id);
    }

    @PostMapping("/{movieId}/actors/{actorId}")
    public Actor addActor(@PathVariable Long movieId, @PathVariable Long actorId) {
        return movieService.addActor(movieId, actorId);
    }

    @DeleteMapping("/{movieId}/actors/{actorId}")
    public Actor removeActor(@PathVariable Long movieId, @PathVariable Long actorId) {
        return movieService.removeActor(movieId, actorId);
    }

    @PostMapping("/{movieId}/genres/{genreId}")
    public Genre addGenre(@PathVariable Long movieId, @PathVariable Long genreId) {
        return movieService.addAGenre(movieId, genreId);
    }

    @DeleteMapping("/{movieId}/genres/{genreId}")
    public Genre removeGenre(@PathVariable Long movieId, @PathVariable Long genreId) {
        return movieService.removeGenre(movieId, genreId);
    }
}
