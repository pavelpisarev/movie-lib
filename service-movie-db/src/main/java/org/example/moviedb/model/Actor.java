package org.example.moviedb.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "actors")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Actor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", unique = true, length = 64, nullable = false)
    private String name;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @JsonIgnore
    @Builder.Default
    @EqualsAndHashCode.Exclude
    @ManyToMany(mappedBy = "actors")
    private Set<Movie> movies = new LinkedHashSet<>();
}
