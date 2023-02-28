package org.example.moviedb.service.impl;

import org.example.moviedb.dto.GenreDto;
import org.example.moviedb.model.Genre;
import org.example.moviedb.repository.GenreRepository;
import org.example.moviedb.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {
    @Autowired
    GenreRepository genreRepository;

    @Override
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    @Override
    public Genre findById(Long id) {
        return genreRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Genre save(GenreDto genreDto) {
        return genreRepository.save(Genre.builder().name(genreDto.name).description(genreDto.description).build());
    }

    @Override
    public Genre updateById(Long id, GenreDto genreDto) {
        Genre _genre = genreRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        if (genreDto.name != null) _genre.setName(genreDto.name);
        if (genreDto.description != null) _genre.setDescription(genreDto.description);
        return genreRepository.save(_genre);
    }

    @Override
    public void deleteById(Long id) {
        genreRepository.deleteById(id);
    }
}
