package org.example.moviedb.service.impl;

import org.example.moviedb.dto.MovieDto;
import org.example.moviedb.model.Actor;
import org.example.moviedb.model.Genre;
import org.example.moviedb.model.Movie;
import org.example.moviedb.repository.ActorRepository;
import org.example.moviedb.repository.GenreRepository;
import org.example.moviedb.repository.MovieRepository;
import org.example.moviedb.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    MovieRepository movieRepository;
    @Autowired
    GenreRepository genreRepository;
    @Autowired
    ActorRepository actorRepository;

    @Override
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public Movie findById(Long id) {
        return movieRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Movie save(MovieDto movieDto) {
        return movieRepository.save(Movie.builder().title(movieDto.title).description(movieDto.description).build());
    }

    @Override
    public Movie updateById(Long id, MovieDto movieDto) {
        Movie _movie = movieRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        if (movieDto.title != null) _movie.setTitle(movieDto.title);
        if (movieDto.description != null) _movie.setDescription(movieDto.description);
        return movieRepository.save(_movie);
    }

    @Override
    public void deleteById(Long id) {
        movieRepository.deleteById(id);
    }

    @Override
    public Actor addActor(Long movieId, Long actorId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(EntityNotFoundException::new);
        Actor actor = actorRepository.findById(actorId).orElseThrow(EntityNotFoundException::new);
        actor.getMovies().add(movie);
        movie.getActors().add(actor);
        return actorRepository.save(actor);
    }

    @Override
    public Actor removeActor(Long movieId, Long actorId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(EntityNotFoundException::new);
        Actor actor = actorRepository.findById(actorId).orElseThrow(EntityNotFoundException::new);
        actor.getMovies().remove(movie);
        movie.getActors().remove(actor);
        return actorRepository.save(actor);
    }

    @Override
    public Genre addAGenre(Long movieId, Long genreId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(EntityNotFoundException::new);
        Genre genre = genreRepository.findById(genreId).orElseThrow(EntityNotFoundException::new);
        genre.getMovies().add(movie);
        movie.getGenres().add(genre);
        return genreRepository.save(genre);
    }

    @Override
    public Genre removeGenre(Long movieId, Long genreId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(EntityNotFoundException::new);
        Genre genre = genreRepository.findById(genreId).orElseThrow(EntityNotFoundException::new);
        genre.getMovies().remove(movie);
        movie.getGenres().remove(genre);
        return genreRepository.save(genre);
    }
}
