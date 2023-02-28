package org.example.moviedb.repository;

import org.example.moviedb.model.Movie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class MovieRepositoryTest {
    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    MovieRepository movieRepository;

    @Test
    public void should_save_movie() {
        Movie movie = Movie.builder().title("title").build();
        movie = testEntityManager.persistAndFlush(movie);
        assertThat(movieRepository.findById(movie.getId())).isEqualTo(Optional.of(movie));
    }
}