package Homework4.reactive.repository;

import Homework4.reactive.model.Sport;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface SportRepository extends ReactiveCrudRepository<Sport, String> {

    Mono<Sport> findByName(String name);

    Mono<Sport> findByName(Mono<String> name);
}
