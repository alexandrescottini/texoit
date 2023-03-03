package com.api.service;

import com.api.dto.ProducerMinMaxPrizesDTO;
import com.api.dto.ProducerPrizesDTO;
import com.api.entity.Movie;
import com.api.entity.MovieProducer;
import com.api.entity.Producer;
import com.api.repository.MovieProducerRepository;
import com.api.repository.ProducerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProducerService {
    Logger logger = LoggerFactory.getLogger(ProducerService.class);
    private static final String REGEX = ",|\\ and ";

    @Autowired
    private ProducerRepository producerRepository;

    @Autowired
    private MovieProducerRepository movieProducerRepository;

    public void saveProducers(Movie movie, String producers) {
        for (String strProducer : producers.split(REGEX)) {
            Producer producer = new Producer(strProducer.trim());
            Example<Producer> example = Example.of(producer);
            producer = producerRepository.exists(example) ?
                    producerRepository.findByName(strProducer.trim()) : producerRepository.save(producer);
            movieProducerRepository.save(new MovieProducer(movie, producer));
        }
    }

    public ProducerMinMaxPrizesDTO getMaxAndMinPrizes() {
        List<MovieProducer> mpList = movieProducerRepository.findByMovieWinnerOrderByProducerId(true);
        List<ProducerPrizesDTO> producerPrizes = findAllProducerPrizes(mpList);
        Integer minInterval = producerPrizes.stream()
                .map(ProducerPrizesDTO::getInterval)
                .min(Comparator.naturalOrder()).get();
        Integer maxInterval = producerPrizes.stream()
                .map(ProducerPrizesDTO::getInterval)
                .max(Comparator.naturalOrder()).get();

        ProducerMinMaxPrizesDTO dto = new ProducerMinMaxPrizesDTO();
        dto.addMin(producerPrizes.stream().filter(c -> minInterval.equals(c.getInterval())).collect(Collectors.toList()));
        dto.addMax(producerPrizes.stream().filter(c -> maxInterval.equals(c.getInterval())).collect(Collectors.toList()));
        return dto;
    }

    private List<ProducerPrizesDTO> findAllProducerPrizes(List<MovieProducer> mpList) {
        List<ProducerPrizesDTO> producerPrizes = new LinkedList<>();
        for (int i = 0; i < mpList.size() - 1; i++) {
            for (int j = i + 1; j < mpList.size(); j++) {
                MovieProducer mpi = mpList.get(i);
                MovieProducer mpj = mpList.get(j);
                if (mpi.getProducer().equals(mpj.getProducer())) {
                    Integer interval = Math.abs(mpi.getMovie().getYear() - mpj.getMovie().getYear());
                    ProducerPrizesDTO producerPrizesDTO = new ProducerPrizesDTO(null, Integer.MIN_VALUE, null, null);
                    if (interval > producerPrizesDTO.getInterval()) {
                        producerPrizesDTO.setInterval(interval);
                        producerPrizesDTO.setProducer(mpi.getProducer().getName());
                        producerPrizesDTO.setPreviousWin(mpi.getMovie().getYear());
                        producerPrizesDTO.setFollowingWin(mpj.getMovie().getYear());
                        if (!producerPrizes.contains(producerPrizesDTO)){
                            producerPrizes.add(producerPrizesDTO);
                        }
                    }
                }
            }
        }
        return producerPrizes;
    }
}
