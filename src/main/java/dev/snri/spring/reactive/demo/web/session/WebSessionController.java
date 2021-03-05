package dev.snri.spring.reactive.demo.web.session;

import dev.snri.spring.reactive.demo.web.session.dto.UserResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.WebSession;
import reactor.core.publisher.Mono;

@RestController

public class WebSessionController {

    @GetMapping("/web-session/test")
    public Mono<UserResponse> testSession(
            @RequestParam(value = "id") int id,
            @RequestParam(value = "note") String note,
            WebSession webSession
    ) {
        UserResponse userResponse = new UserResponse(id, note);

        webSession.getAttributes().put("user", userResponse);

        return Mono.just(userResponse);
    }

    @GetMapping("/web-session")
    public Mono<UserResponse> getSession(WebSession webSession) {
        UserResponse userResponse = webSession.getAttribute("user");

        return Mono.justOrEmpty(userResponse);
    }

}
