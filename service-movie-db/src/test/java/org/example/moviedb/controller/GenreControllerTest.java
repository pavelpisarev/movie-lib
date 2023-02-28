package org.example.moviedb.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.moviedb.dto.GenreDto;
import org.example.moviedb.model.Genre;
import org.example.moviedb.service.GenreService;
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

@WebMvcTest(GenreController.class)
class GenreControllerTest {
    @MockBean
    GenreService genreService;

    @Autowired
    MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();
    private final String url = "/api/v1/genres";

    @Test
    @DisplayName("/POST genres")
    void should_return_created_genre() throws Exception {
        GenreDto genreDto = new GenreDto();
        genreDto.name = "genre";
        Genre genre = Genre.builder().name(genreDto.name).build();
        when(genreService.save(any(GenreDto.class))).thenReturn(genre);
        mockMvc.perform(post(url)
            .content(objectMapper.writeValueAsString(genreDto))
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated()).andExpect(jsonPath("$.name").value(genreDto.name));
        verify(genreService, times(1)).save(any(GenreDto.class));
        verifyNoMoreInteractions(genreService);
    }

    @Test
    @DisplayName("/GET genres")
    void should_return_all_genres() throws Exception {
        List<Genre> genres = List.of(Genre.builder().name("genre1").build(), Genre.builder().name("genre2").build());
        when(genreService.findAll()).thenReturn(genres);
        mockMvc.perform(get(url)
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(genres.size())));
        verify(genreService, times(1)).findAll();
        verifyNoMoreInteractions(genreService);
    }

    @Test
    @DisplayName("/GET genres/{id}")
    void should_find_one_genre() throws Exception {
        Genre genre = Genre.builder().name("genre").build();
        when(genreService.findById(anyLong())).thenReturn(genre);
        mockMvc.perform(get(url + "/1")
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andExpect(jsonPath("$.name").value(genre.getName()));
        verify(genreService, times(1)).findById(anyLong());
        verifyNoMoreInteractions(genreService);
    }

    @Test
    @DisplayName("/DELETE genres/{id}")
    void should_delete_genre() throws Exception {
        doNothing().when(genreService).deleteById(anyLong());
        mockMvc.perform(delete(url + "/1")
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
        verify(genreService, times(1)).deleteById(anyLong());
        verifyNoMoreInteractions(genreService);
    }
}