package dev.snri.spring.reactive.demo.web;

import dev.snri.spring.reactive.demo.web.demo.DemoHandler;
import dev.snri.spring.reactive.demo.web.session.WebSessionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.server.*;

import static org.springframework.http.MediaType.*;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
@EnableWebFlux
public class WebRouter {

    @Bean
    public RouterFunction<ServerResponse> routeDemoHandler(DemoHandler handler) {
        return RouterFunctions.route()
                .GET("/demo/test", accept(APPLICATION_JSON), handler::test)
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> routeWebSessionHandler(WebSessionHandler handler) {
        return RouterFunctions.route().path("/web-session", () ->
                        RouterFunctions.route().nest(accept(APPLICATION_JSON), () ->
                                RouterFunctions.route()
                                        .GET(handler::getSession)
                                        .GET("/test", handler::testSession)
                                        .build()
                        ).build()
        ).build();
    }

}
