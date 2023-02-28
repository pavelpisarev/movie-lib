package org.example.moviedb.service;

import org.example.moviedb.dto.MovieDto;
import org.example.moviedb.model.Actor;
import org.example.moviedb.model.Genre;
import org.example.moviedb.model.Movie;
import org.example.moviedb.repository.ActorRepository;
import org.example.moviedb.repository.GenreRepository;
import org.example.moviedb.repository.MovieRepository;
import org.example.moviedb.service.impl.MovieServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MovieServiceTest {
    @Mock
    MovieRepository movieRepository;

    @Mock
    ActorRepository actorRepository;

    @Mock
    GenreRepository genreRepository;

    @InjectMocks
    MovieServiceImpl movieService;

    @Test
    void should_find_all_movies() {
        List<Movie> movies = List.of(Movie.builder().title("title1").build(), Movie.builder().title("title2").build());
        when(movieRepository.findAll()).thenReturn(movies);
        assertThat(movieService.findAll()).hasSize(movies.size());
        verify(movieRepository, times(1)).findAll();
        verifyNoMoreInteractions(movieRepository);
    }

    @Test
    void should_save_one_movie() {
        Movie movie = Movie.builder().title("title").build();
        when(movieRepository.save(any(Movie.class))).thenReturn(movie);
        Movie actual = movieService.save(new MovieDto());
        assertThat(actual).isEqualTo(movie);
        verify(movieRepository, times(1)).save(any(Movie.class));
        verifyNoMoreInteractions(movieRepository);
    }

    @Test
    void should_find_and_return_one_movie() {
        Movie expectedMovie = Movie.builder().title("title").build();
        when(movieRepository.findById(anyLong())).thenReturn(Optional.of(expectedMovie));
        Movie actual = movieService.findById(1L);
        assertThat(actual).usingRecursiveComparison().isEqualTo(expectedMovie);
        verify(movieRepository, times(1)).findById(1L);
        verifyNoMoreInteractions(movieRepository);
    }

    @Test
    void should_not_found_an_movie() {
        when(movieRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> movieService.findById(1L));
        verify(movieRepository, times(1)).findById(1L);
        verifyNoMoreInteractions(movieRepository);
    }

    @Test
    void should_delete_movie() {
        doNothing().when(movieRepository).deleteById(anyLong());
        movieService.deleteById(1L);
        verify(movieRepository, times(1)).deleteById(anyLong());
        verifyNoMoreInteractions(movieRepository);
    }

    @Test
    void should_add_actor_to_movie() {
        Movie movie = Movie.builder().title("title").build();
        when(movieRepository.findById(anyLong())).thenReturn(Optional.of(movie));
        Actor actor = Actor.builder().name("name").build();
        when(actorRepository.findById(anyLong())).thenReturn(Optional.of(actor));
        movieService.addActor(1L, 1L);
        verify(movieRepository, times(1)).findById(anyLong());
        verify(actorRepository, times(1)).findById(anyLong());
        verify(actorRepository, times(1)).save(any(Actor.class));
        verifyNoMoreInteractions(movieRepository);
        verifyNoMoreInteractions(actorRepository);
    }

    @Test
    void should_remove_actor_from_movie() {
        Movie movie = Movie.builder().title("title").build();
        when(movieRepository.findById(anyLong())).thenReturn(Optional.of(movie));
        Actor actor = Actor.builder().name("name").build();
        when(actorRepository.findById(anyLong())).thenReturn(Optional.of(actor));
        movieService.removeActor(1L, 1L);
        verify(movieRepository, times(1)).findById(anyLong());
        verify(actorRepository, times(1)).findById(anyLong());
        verify(actorRepository, times(1)).save(any(Actor.class));
        verifyNoMoreInteractions(movieRepository);
        verifyNoMoreInteractions(actorRepository);
    }

    @Test
    void should_add_genre_to_movie() {
        Movie movie = Movie.builder().title("title").build();
        when(movieRepository.findById(anyLong())).thenReturn(Optional.of(movie));
        Genre genre = Genre.builder().name("name").build();
        when(genreRepository.findById(anyLong())).thenReturn(Optional.of(genre));
        movieService.addAGenre(1L, 1L);
        verify(movieRepository, times(1)).findById(anyLong());
        verify(genreRepository, times(1)).findById(anyLong());
        verify(genreRepository, times(1)).save(any(Genre.class));
        verifyNoMoreInteractions(movieRepository);
        verifyNoMoreInteractions(genreRepository);
    }

    @Test
    void should_remove_genre_from_movie() {
        Movie movie = Movie.builder().title("title").build();
        when(movieRepository.findById(anyLong())).thenReturn(Optional.of(movie));
        Genre genre = Genre.builder().name("name").build();
        when(genreRepository.findById(anyLong())).thenReturn(Optional.of(genre));
        movieService.removeGenre(1L, 1L);
        verify(movieRepository, times(1)).findById(anyLong());
        verify(genreRepository, times(1)).findById(anyLong());
        verify(genreRepository, times(1)).save(any(Genre.class));
        verifyNoMoreInteractions(movieRepository);
        verifyNoMoreInteractions(genreRepository);
    }
}