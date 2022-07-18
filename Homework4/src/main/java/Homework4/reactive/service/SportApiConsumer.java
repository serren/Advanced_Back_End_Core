package Homework4.reactive.service;


import Homework4.reactive.client.SportApiClient;
import Homework4.reactive.model.Sport;
import Homework4.reactive.repository.SportRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.SmartLifecycle;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Slf4j
@Service
@RequiredArgsConstructor
public class SportApiConsumer implements SmartLifecycle {

    private final SportApiClient apiClient;
    private final SportRepository repository;

    @Override
    public void start() {
        Flux<Sport> entityStream = apiClient.getAllSportsData()
                .flatMap(dto -> Flux.just(dto.toEntity()));
        repository.saveAll(entityStream).blockLast();
    }

    @Override
    public void stop() { /*No need to do something on stop */}

    @Override
    public boolean isRunning() {
        return false;
    }
}