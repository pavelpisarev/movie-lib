package org.example.moviedb.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.moviedb.dto.ActorDto;
import org.example.moviedb.model.Actor;
import org.example.moviedb.service.ActorService;
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

@WebMvcTest(ActorController.class)
class ActorControllerTest {
    @MockBean
    ActorService actorService;

    @Autowired
    MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();
    private final String url = "/api/v1/actors";

    @Test
    @DisplayName("/POST actors")
    void should_return_created_actor() throws Exception {
        ActorDto actorDto = new ActorDto();
        actorDto.name = "actor";
        Actor actor = Actor.builder().name(actorDto.name).build();
        when(actorService.save(any(ActorDto.class))).thenReturn(actor);
        mockMvc.perform(post(url)
            .content(objectMapper.writeValueAsString(actorDto))
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isCreated()).andExpect(jsonPath("$.name").value(actorDto.name));
        verify(actorService, times(1)).save(any(ActorDto.class));
        verifyNoMoreInteractions(actorService);
    }

    @Test
    @DisplayName("/GET actors")
    void should_return_all_actors() throws Exception {
        List<Actor> actors = List.of(Actor.builder().name("actor1").build(), Actor.builder().name("actor2").build());
        when(actorService.findAll()).thenReturn(actors);
        mockMvc.perform(get(url)
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(actors.size())));
        verify(actorService, times(1)).findAll();
        verifyNoMoreInteractions(actorService);
    }

    @Test
    @DisplayName("/POST actors/{id}")
    void should_find_one_actor() throws Exception {
        Actor actor = Actor.builder().name("actor").build();
        when(actorService.findById(anyLong())).thenReturn(actor);
        mockMvc.perform(get(url + "/1")
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk()).andExpect(jsonPath("$.name").value(actor.getName()));
        verify(actorService, times(1)).findById(anyLong());
        verifyNoMoreInteractions(actorService);
    }

    @Test
    @DisplayName("/DELETE actors/{id}")
    void should_delete_actor() throws Exception {
        doNothing().when(actorService).deleteById(anyLong());
        mockMvc.perform(delete(url + "/1")
            .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
        verify(actorService, times(1)).deleteById(anyLong());
        verifyNoMoreInteractions(actorService);
    }
}