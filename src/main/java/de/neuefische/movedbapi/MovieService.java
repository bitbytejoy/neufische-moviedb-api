package de.neuefische.movedbapi;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;

    public Movie create (Movie movie) {
        return movieRepository.save(movie);
    }

    public List<Movie> getAll(String title) {
        return title != null && !title.isBlank()
            ? movieRepository.findByTitle(title)
            : movieRepository.findAll();
    }
}
