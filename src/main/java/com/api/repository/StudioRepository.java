package com.api.repository;

import com.api.dto.StudioWinDTO;
import com.api.entity.Studio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudioRepository extends JpaRepository<Studio, Long> {
    Studio findByName(String name);

    @Query(value = "SELECT new com.api.dto.StudioWinDTO(studio.name, COUNT(movie.winner)) " +
            "FROM MovieStudio AS ms " +
            "JOIN ms.movie AS movie " +
            "JOIN ms.studio AS studio " +
            "WHERE movie.winner=true " +
            "GROUP BY studio.name " +
            "ORDER BY 2 DESC")
    List<StudioWinDTO> findByWinners();

}
