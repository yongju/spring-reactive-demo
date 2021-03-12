package dev.snri.spring.reactive.demo.web.session;

import dev.snri.spring.reactive.demo.web.session.dto.UserResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Optional;

@Component
public class WebSessionHandler {

    public Mono<ServerResponse> testSession(ServerRequest request) {
        MultiValueMap<String, String> queryParams = request.queryParams();
        int id = Integer.parseInt(queryParams.getFirst("id"));
        String note = queryParams.getFirst("note");
        UserResponse userResponse = new UserResponse(id, note);

        request.session().doOnNext(webSession -> {
            webSession.getAttributes().put("user", userResponse);
        });

        return ServerResponse.ok().body(Mono.just(userResponse), UserResponse.class);
    }

    public Mono<ServerResponse> getSession(ServerRequest request) {

        return request.session().map(webSession -> {
            UserResponse userResponse = webSession.getAttribute("user");
            return Optional.ofNullable(userResponse);
        }).flatMap(userResponse -> {
            if (userResponse.isPresent()) {
                return ServerResponse.ok().body(userResponse, UserResponse.class);
            } else {
                return ServerResponse.ok().build();
            }
        });


    }

}
