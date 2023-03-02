package com.api.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "MOVIE_PRODUCER")
public class MovieProducer {
    @EmbeddedId
    private MovieProducerId id;

    @ManyToOne
    @MapsId("idMovie")
    private Movie movie;

    @ManyToOne
    @MapsId("idProducer")
    private Producer producer;

    public MovieProducer() {
    }

    public MovieProducer(Movie movie, Producer producer) {
        this.movie = movie;
        this.producer = producer;
        this.id = new MovieProducerId(movie.getId(), producer.getId());
    }

    public MovieProducerId getId() {
        return id;
    }

    public void setId(MovieProducerId id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Producer getProducer() {
        return producer;
    }

    public void setProducer(Producer producer) {
        this.producer = producer;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        MovieProducer other = (MovieProducer) obj;
        return Objects.equals(movie, other.getMovie()) &&
                Objects.equals(producer, other.getProducer());
    }

    @Override
    public int hashCode() {
        return Objects.hash(movie, producer);
    }

}
