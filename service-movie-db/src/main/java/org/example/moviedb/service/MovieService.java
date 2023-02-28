package org.example.moviedb.service;

import org.example.moviedb.dto.MovieDto;
import org.example.moviedb.model.Actor;
import org.example.moviedb.model.Genre;
import org.example.moviedb.model.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> findAll();

    Movie findById(Long id);

    Movie save(MovieDto movieDto);

    Movie updateById(Long id, MovieDto movieDto);

    void deleteById(Long id);

    Actor addActor(Long movieId, Long actorId);

    Actor removeActor(Long movieId, Long actorId);

    Genre addAGenre(Long movieId, Long genreId);

    Genre removeGenre(Long movieId, Long genreId);
}
