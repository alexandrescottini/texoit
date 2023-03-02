package com.api.repository;

import com.api.entity.MovieStudio;
import com.api.entity.MovieStudioId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieStudioRepository extends JpaRepository<MovieStudio, MovieStudioId> {

}
