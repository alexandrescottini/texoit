package com.api.repository;

import com.api.entity.Producer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProducerRepository extends JpaRepository<Producer, Long> {
    Producer findByName(String name);
}
