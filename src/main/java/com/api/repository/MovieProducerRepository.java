package com.api.repository;

import com.api.entity.MovieProducer;
import com.api.entity.MovieProducerId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieProducerRepository extends JpaRepository<MovieProducer, MovieProducerId> {
    @Query(value = "SELECT mp " +
            "FROM MovieProducer AS mp " +
            "JOIN mp.movie as movie " +
            "JOIN mp.producer AS producer " +
            "WHERE movie.winner = true " +
            "ORDER BY producer.id, movie.year")
    List<MovieProducer> findByMovieWinnerOrderByProducerId(Boolean isWinner);

}
