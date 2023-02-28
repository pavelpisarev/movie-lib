package org.example.moviedb.service;

import org.example.moviedb.dto.ActorDto;
import org.example.moviedb.model.Actor;

import java.util.List;

public interface ActorService {
    List<Actor> findAll();

    Actor findById(Long id);

    Actor save(ActorDto actorDto);

    Actor updateById(Long id, ActorDto actorDto);

    void deleteById(Long id);
}
