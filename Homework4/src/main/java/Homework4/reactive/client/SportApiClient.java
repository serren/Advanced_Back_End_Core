package Homework4.reactive.client;

import Homework4.reactive.model.SportDto;
import reactor.core.publisher.Flux;

public interface SportApiClient {

  Flux<SportDto> getAllSportsData();
}
