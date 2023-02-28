package org.example.moviedb.service.impl;

import org.example.moviedb.dto.ActorDto;
import org.example.moviedb.model.Actor;
import org.example.moviedb.repository.ActorRepository;
import org.example.moviedb.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class ActorServiceImpl implements ActorService {
    @Autowired
    ActorRepository actorRepository;

    @Override
    public List<Actor> findAll() {
        return actorRepository.findAll();
    }

    @Override
    public Actor findById(Long id) {
        return actorRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Actor save(ActorDto actorDto) {
        return actorRepository.save(Actor.builder().name(actorDto.name).dateOfBirth(actorDto.dateOfBirth).build());
    }

    @Override
    public Actor updateById(Long id, ActorDto actorDto) {
        Actor _actor = actorRepository.findById(id).orElseThrow(EntityNotFoundException::new);
        if (actorDto.name != null) _actor.setName(actorDto.name);
        if (actorDto.dateOfBirth != null) _actor.setDateOfBirth(actorDto.dateOfBirth);
        return actorRepository.save(_actor);
    }

    @Override
    public void deleteById(Long id) {
        actorRepository.deleteById(id);
    }
}
