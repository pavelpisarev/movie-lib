package org.example.moviedb.controller;

import org.example.moviedb.dto.ActorDto;
import org.example.moviedb.model.Actor;
import org.example.moviedb.service.ActorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/actors", produces = "application/json")
public class ActorController {
    @Autowired
    ActorService actorService;

    @PostMapping
    public ResponseEntity<Actor> create(@RequestBody ActorDto actorDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(actorService.save(actorDto));
    }

    @GetMapping
    public List<Actor> getAll() {
        return actorService.findAll();
    }

    @GetMapping("{id}")
    public Actor getOne(@PathVariable Long id) {
        return actorService.findById(id);
    }

    @PutMapping("/{id}")
    public Actor updateById(@PathVariable Long id, @RequestBody ActorDto actorDto) {
        return actorService.updateById(id, actorDto);
    }

    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id) {
        actorService.deleteById(id);
    }
}
