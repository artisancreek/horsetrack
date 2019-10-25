package com.artisancreek.horsetrack.repository;

import com.artisancreek.horsetrack.model.Horse;
import org.springframework.data.repository.CrudRepository;

public interface HorseRepository extends CrudRepository<Horse, Integer> {
}
