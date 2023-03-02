package com.api.service;

import com.api.dto.StudioDTO;
import com.api.entity.Movie;
import com.api.entity.MovieStudio;
import com.api.entity.Studio;
import com.api.repository.MovieStudioRepository;
import com.api.repository.StudioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
public class StudioService {
    Logger logger = LoggerFactory.getLogger(StudioService.class);
    private static final String REGEX = ",|\\ and ";

    @Autowired
    private StudioRepository studioRepository;

    @Autowired
    private MovieStudioRepository movieStudioRepository;

    public void saveStudios(Movie movie, String studios) {
        for (String strStudio : studios.split(REGEX)) {
            Studio studio = new Studio(strStudio.trim());
            Example<Studio> example = Example.of(studio);
            studio = studioRepository.exists(example) ?
                    studioRepository.findByName(strStudio.trim()) : studioRepository.save(studio);
            movieStudioRepository.save(new MovieStudio(movie, studio));
        }
    }

    public StudioDTO getGreatestWinners() {
        return new StudioDTO(studioRepository.findByWinners());
    }

}
