package com.api.controller;

import com.api.dto.MovieDTO;
import com.api.dto.YearWinnerDTO;
import com.api.service.MovieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie")
public class MovieController {
    Logger logger = LoggerFactory.getLogger(MovieController.class);

    @Autowired
    private MovieService movieService;

    @GetMapping("/{year}")
    public ResponseEntity<List<MovieDTO>> getMovies(@PathVariable(name = "year") Integer year) {
        List<MovieDTO> movies = movieService.getMoviesByYear(year);
        HttpStatus status = HttpStatus.OK;
        if (movies.isEmpty()) {
            status = HttpStatus.NO_CONTENT;
        }
        return new ResponseEntity<List<MovieDTO>>(movies, status);
    }

    @GetMapping("/years")
    public ResponseEntity<YearWinnerDTO> getYearsWithMoreThanOneWinners() {
        YearWinnerDTO dto = movieService.getYearsWithMoreThanOneWinners();
        HttpStatus status = HttpStatus.OK;
        if (dto.getYears().isEmpty()) {
            status = HttpStatus.NO_CONTENT;
        }
        return new ResponseEntity<YearWinnerDTO>(dto, status);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeMovie(@PathVariable(name = "id") Long id) {
        movieService.remove(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
