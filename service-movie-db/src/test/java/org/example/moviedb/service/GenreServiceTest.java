package org.example.moviedb.service;

import org.example.moviedb.dto.GenreDto;
import org.example.moviedb.model.Genre;
import org.example.moviedb.repository.GenreRepository;
import org.example.moviedb.service.impl.GenreServiceImpl;
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
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GenreServiceTest {
    @Mock
    GenreRepository genreRepository;

    @InjectMocks
    GenreServiceImpl genreService;

    @Test
    void should_find_all_genres() {
        List<Genre> genres = List.of(Genre.builder().name("genre1").build(), Genre.builder().name("genre2").build());
        when(genreRepository.findAll()).thenReturn(genres);
        assertThat(genreService.findAll()).hasSize(genres.size());
        verify(genreRepository, times(1)).findAll();
        verifyNoMoreInteractions(genreRepository);
    }

    @Test
    void should_save_one_genre() {
        Genre genre = Genre.builder().name("genre").build();
        when(genreRepository.save(any(Genre.class))).thenReturn(genre);
        Genre actual = genreService.save(new GenreDto());
        assertThat(actual).isEqualTo(genre);
        verify(genreRepository, times(1)).save(any(Genre.class));
        verifyNoMoreInteractions(genreRepository);
    }

    @Test
    void should_find_and_return_one_genre() {
        Genre expectedGenre = Genre.builder().name("genre").build();
        when(genreRepository.findById(anyLong())).thenReturn(Optional.of(expectedGenre));
        Genre actual = genreService.findById(1L);
        assertThat(actual).usingRecursiveComparison().isEqualTo(expectedGenre);
        verify(genreRepository, times(1)).findById(1L);
        verifyNoMoreInteractions(genreRepository);
    }

    @Test
    void should_not_found_an_genre() {
        when(genreRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> genreService.findById(1L));
        verify(genreRepository, times(1)).findById(1L);
        verifyNoMoreInteractions(genreRepository);
    }

    @Test
    void should_delete_genre() {
        doNothing().when(genreRepository).deleteById(anyLong());
        genreService.deleteById(1L);
        verify(genreRepository, times(1)).deleteById(anyLong());
        verifyNoMoreInteractions(genreRepository);
    }
}