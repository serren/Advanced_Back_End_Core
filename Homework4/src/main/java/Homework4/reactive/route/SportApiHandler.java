package Homework4.reactive.route;

import Homework4.reactive.model.Sport;
import Homework4.reactive.repository.SportRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.ServerWebInputException;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.util.Optional;
import java.util.function.Predicate;

@Component
@RequiredArgsConstructor
@Slf4j
public class SportApiHandler {

    private final SportRepository repository;

    public Mono<ServerResponse> get(ServerRequest request) {
        return request.queryParam("name")
                .map(name -> repository.findByName(name)
                        .flatMap(s ->
                            ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(Mono.just(s), Sport.class))
                        .switchIfEmpty(ServerResponse.notFound().build()))
                .orElseGet(() ->
                        ServerResponse.ok()
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(repository.findAll(), Sport.class));
    }

    public Mono<ServerResponse> getById(ServerRequest request) {
        var id =
                Optional.of(request.pathVariable("id"))
                        .filter(Predicate.not(String::isBlank))
                        .orElseThrow(() -> new ServerWebInputException("invalid id parameter"));
        return repository
                .findById(id)
                .flatMap(sports -> ServerResponse.ok().bodyValue(sports))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> post(ServerRequest request) {
        final Mono<Sport> sport = request.bodyToMono(Sport.class);
        return repository
                // Got crasy here. Couldn't make to work findByName(Mono<String> name) with mono input
                // So checking if a sport with the same name exists does not work
                // In the other hand I couldn't find out how extract name value
                // from Mono<Sport> sport to use in findByName(String name) which works fine.
                .findByName(sport.flatMap(s -> Mono.just(s.getName())))
                .flatMap(s ->
                        ServerResponse.badRequest().bodyValue(s.getName() + " already exist!"))
                .switchIfEmpty(
                        ServerResponse.created(UriComponentsBuilder.fromPath("sport").build().toUri())
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(BodyInserters.fromPublisher(repository.saveAll(sport), Sport.class)));
    }
}