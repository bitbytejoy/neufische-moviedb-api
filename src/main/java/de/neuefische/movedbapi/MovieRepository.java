package de.neuefische.movedbapi;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class MovieRepository {
    private final Map<String, Movie> movies = new HashMap<>();
    private int id = 0;

    public Movie save (Movie movie) {
        String id = nextId();
        movie.setId(id);
        movies.put(id, movie);
        return movie;
    }

    private String nextId () {
        return String.valueOf(++id);
    }

    public List<Movie> findAll() {
        return movies.values().stream().toList();
    }

    public List<Movie> findByTitle (String title) {
        return this.findAll()
            .stream()
            .filter(movie -> movie.getTitle().contains(title))
            .collect(Collectors.toList());
    }
}
