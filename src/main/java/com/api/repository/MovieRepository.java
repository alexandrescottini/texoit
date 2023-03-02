package com.api.repository;

import com.api.dto.YearWinnerMovieDTO;
import com.api.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    List<Movie> findByYear(Integer year);

    @Query(value = "SELECT new com.api.dto.YearWinnerMovieDTO(movie.year, COUNT(movie.winner)) " +
            "FROM Movie AS movie " +
            "WHERE movie.winner=true " +
            "GROUP BY movie.year " +
            "HAVING COUNT(movie.winner) > 1")
    List<YearWinnerMovieDTO> findYearsWithModeThanOneWinner();

}
