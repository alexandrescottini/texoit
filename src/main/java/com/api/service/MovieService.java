package com.api.service;

import com.api.dto.MovieDTO;
import com.api.dto.YearWinnerDTO;
import com.api.dto.YearWinnerMovieDTO;
import com.api.entity.Movie;
import com.api.exceptions.BadRequestException;
import com.api.exceptions.ResourceNotFoundException;
import com.api.repository.MovieRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    Logger logger = LoggerFactory.getLogger(MovieService.class);

    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> getMoviesFromAYear(Integer year) {
        return movieRepository.findByYear(year);
    }

    public List<MovieDTO> getMoviesByYear(Integer year) {
        List<Movie> movies = movieRepository.findByYear(year);
        if (movies == null || movies.isEmpty()) {
            return new ArrayList<>();
        }
        List<MovieDTO> moviesDto = new ArrayList<>();
        for (Movie m : movies) {
            moviesDto.add(new MovieDTO(m));
        }
        return moviesDto;
    }

    public YearWinnerDTO getYearsWithMoreThanOneWinners() {
        List<YearWinnerMovieDTO> years = movieRepository.findYearsWithModeThanOneWinner();
        if (years == null || years.isEmpty()) {
            return new YearWinnerDTO();
        }
        return new YearWinnerDTO(years);
    }

    public void remove(Long id) {
        Optional<Movie> optional = movieRepository.findById(id);
        if (!optional.isPresent()) {
            throw new ResourceNotFoundException();
        }
        Movie movie = optional.get();
        if (movie.getWinner()) {
            throw new BadRequestException();
        }
        movieRepository.delete(movie);
    }

}
