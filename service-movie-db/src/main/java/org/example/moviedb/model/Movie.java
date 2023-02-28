package org.example.moviedb.model;

import lombok.*;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "movies")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "title", length = 128, nullable = false)
    private String title;

    @Column(name = "description", length = 256)
    private String description;

    @Builder.Default
    @EqualsAndHashCode.Exclude
    @ManyToMany
    @JoinTable(name = "movies_genres",
        joinColumns = @JoinColumn(name = "movie_id"),
        inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Set<Genre> genres = new LinkedHashSet<>();

    @Builder.Default
    @EqualsAndHashCode.Exclude
    @ManyToMany
    @JoinTable(name = "movies_actors",
        joinColumns = @JoinColumn(name = "movie_id"),
        inverseJoinColumns = @JoinColumn(name = "actor_id"))
    private Set<Actor> actors = new LinkedHashSet<>();
}
