package org.example.moviedb.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.moviedb.dto.MovieDto;
import org.example.moviedb.model.Movie;
import org.example.moviedb.service.MovieService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MovieController.class)
class MovieControllerTest {
    @MockBean
    MovieService movieService;

    @Autowired
    MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();
    private final String url = "/api/v1/movies";

    @Test
    @DisplayName("/POST movies")
    void should_return_created_movie() throws Exception {
        MovieDto movieDto = new MovieDto();
        movieDto.title = "movie";
        Movie movie = Movie.builder().title(movieDto.title).build();
        when(movieService.save(any(MovieDto.class))).thenReturn(movie);
        mockMvc.perform(post(url)
            .content(objectMapper.writeValueAsString(movieDto))
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated()).andExpect(jsonPath("$.title").value(movieDto.title));
        verify(movieService, times(1)).save(any(MovieDto.class));
        verifyNoMoreInteractions(movieService);
    }

    @Test
    @DisplayName("/GET movies")
    void should_return_all_movies() throws Exception {
        List<Movie> movies = List.of(Movie.builder().title("movie1").build(), Movie.builder().title("movie2").build());
        when(movieService.findAll()).thenReturn(movies);
        mockMvc.perform(get(url)
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(movies.size())));
        verify(movieService, times(1)).findAll();
        verifyNoMoreInteractions(movieService);
    }

    @Test
    @DisplayName("/GET movies/{id}")
    void should_find_one_movie() throws Exception {
        Movie movie = Movie.builder().title("movie").build();
        when(movieService.findById(anyLong())).thenReturn(movie);
        mockMvc.perform(get(url + "/1")
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andExpect(jsonPath("$.title").value(movie.getTitle()));
        verify(movieService, times(1)).findById(anyLong());
        verifyNoMoreInteractions(movieService);
    }

    @Test
    @DisplayName("/DELETE movies/{id}")
    void should_delete_movie() throws Exception {
        doNothing().when(movieService).deleteById(anyLong());
        mockMvc.perform(delete(url + "/1")
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
        verify(movieService, times(1)).deleteById(anyLong());
        verifyNoMoreInteractions(movieService);
    }

    @Test
    @DisplayName("/POST movies/{movieId}/actors/{actorId}")
    void should_add_actor_to_movie() throws Exception {
        mockMvc.perform(post(url + "/1/actors/1")
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
        verify(movieService, times(1)).addActor(anyLong(), anyLong());
        verifyNoMoreInteractions(movieService);
    }

    @Test
    @DisplayName("/DELETE movies/{movieId}/actors/{actorId}")
    void should_remove_actor_from_movie() throws Exception {
        mockMvc.perform(delete(url + "/1/actors/1")
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
        verify(movieService, times(1)).removeActor(anyLong(), anyLong());
        verifyNoMoreInteractions(movieService);
    }

    @Test
    @DisplayName("/POST movies/{movieId}/genres/{genreId}")
    void should_add_genre_to_movie() throws Exception {
        mockMvc.perform(post(url + "/1/genres/1")
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
        verify(movieService, times(1)).addAGenre(anyLong(), anyLong());
        verifyNoMoreInteractions(movieService);
    }

    @Test
    @DisplayName("/DELETE movies/{movieId}/genres/{genreId}")
    void removeGenre() throws Exception {
        mockMvc.perform(delete(url + "/1/genres/1")
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
        verify(movieService, times(1)).removeGenre(anyLong(), anyLong());
        verifyNoMoreInteractions(movieService);
    }
}