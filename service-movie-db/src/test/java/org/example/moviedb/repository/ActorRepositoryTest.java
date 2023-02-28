package org.example.moviedb.repository;

import org.example.moviedb.model.Actor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class ActorRepositoryTest {
    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    ActorRepository actorRepository;

    @Test
    public void should_save_actor() {
        Actor actor = Actor.builder().name("Actor").build();
        actor = testEntityManager.persistAndFlush(actor);
        assertThat(actorRepository.findById(actor.getId())).isEqualTo(Optional.of(actor));
    }
}