package org.example.moviedb.repository;

import org.example.moviedb.model.Genre;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class GenreRepositoryTest {
    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    GenreRepository genreRepository;

    @Test
    public void should_save_genre() {
        Genre genre = Genre.builder().name("genre").build();
        genre = testEntityManager.persistAndFlush(genre);
        assertThat(genreRepository.findById(genre.getId())).isEqualTo(Optional.of(genre));
    }
}