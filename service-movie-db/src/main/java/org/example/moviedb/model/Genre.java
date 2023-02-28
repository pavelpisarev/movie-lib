package org.example.moviedb.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "genres")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", unique = true, length = 32, nullable = false)
    private String name;

    @Column(name = "description", length = 256)
    private String description;

    @JsonIgnore
    @Builder.Default
    @EqualsAndHashCode.Exclude
    @ManyToMany(mappedBy = "genres")
    private Set<Movie> movies = new LinkedHashSet<>();
}
