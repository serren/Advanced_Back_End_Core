package Homework4.reactive.route;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class SportRouter {

    @Bean
    public RouterFunction<ServerResponse> routeById(SportApiHandler sportApiHandler) {
        return RouterFunctions
                .nest(path("/api/v1/sport"),
                    // get all sports or particular one by sending name as a parameter
                    route(GET(""), sportApiHandler::get)
                    // get sport by id
                    .andRoute(GET("/{id}"), sportApiHandler::getById)
                    // create a new sport
                    .andRoute(POST("").and(accept(MediaType.APPLICATION_JSON)), sportApiHandler::post));
    }
}
