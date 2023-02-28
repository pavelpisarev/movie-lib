package org.example.moviedb.service;

import org.example.moviedb.dto.GenreDto;
import org.example.moviedb.model.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> findAll();

    Genre findById(Long id);

    Genre save(GenreDto genreDto);

    Genre updateById(Long id, GenreDto genreDto);

    void deleteById(Long id);
}
