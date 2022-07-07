package Homework4.reactive.client;

import Homework4.reactive.model.SportDto;
import Homework4.reactive.model.SportDataDto;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Component
@RequiredArgsConstructor
@Slf4j
public class SportApiClientImpl implements SportApiClient {

  private final WebClient webClient;

  @Override
  public Flux<SportDto> getAllSportsData() {
    return webClient
        .get()
        .uri("/sports")
        .exchangeToMono(resp -> resp.bodyToMono(SportDataDto.class))
        .doOnError(err -> log.error("error getting response", err))
        .flatMapIterable(SportDataDto::getData)
        .take(20)
        .doOnNext(data -> log.info("sports data: {}", data));
  }
}
