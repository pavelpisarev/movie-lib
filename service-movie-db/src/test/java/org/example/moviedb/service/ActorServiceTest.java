package org.example.moviedb.service;

import org.example.moviedb.dto.ActorDto;
import org.example.moviedb.model.Actor;
import org.example.moviedb.repository.ActorRepository;
import org.example.moviedb.service.impl.ActorServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ActorServiceTest {
    @Mock
    ActorRepository actorRepository;

    @InjectMocks
    ActorServiceImpl actorService;

    @Test
    void should_find_all_actors() {
        List<Actor> actors = List.of(Actor.builder().name("actor1").build(), Actor.builder().name("actor2").build());
        when(actorRepository.findAll()).thenReturn(actors);
        assertThat(actorService.findAll()).hasSize(actors.size());
        verify(actorRepository, times(1)).findAll();
        verifyNoMoreInteractions(actorRepository);
    }

    @Test
    void should_save_one_actor() {
        Actor actor = Actor.builder().name("actor").build();
        when(actorRepository.save(any(Actor.class))).thenReturn(actor);
        Actor actual = actorService.save(new ActorDto());
        assertThat(actual).isEqualTo(actor);
        verify(actorRepository, times(1)).save(any(Actor.class));
        verifyNoMoreInteractions(actorRepository);
    }

    @Test
    void should_find_and_return_one_actor() {
        Actor expectedActor = Actor.builder().name("actor").dateOfBirth(LocalDate.now()).build();
        when(actorRepository.findById(anyLong())).thenReturn(Optional.of(expectedActor));
        Actor actual = actorService.findById(1L);
        assertThat(actual).usingRecursiveComparison().isEqualTo(expectedActor);
        verify(actorRepository, times(1)).findById(1L);
        verifyNoMoreInteractions(actorRepository);
    }

    @Test
    void should_not_found_an_actor() {
        when(actorRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> actorService.findById(1L));
        verify(actorRepository, times(1)).findById(1L);
        verifyNoMoreInteractions(actorRepository);
    }

    @Test
    void should_delete_actor() {
        doNothing().when(actorRepository).deleteById(anyLong());
        actorService.deleteById(1L);
        verify(actorRepository, times(1)).deleteById(anyLong());
        verifyNoMoreInteractions(actorRepository);
    }
}