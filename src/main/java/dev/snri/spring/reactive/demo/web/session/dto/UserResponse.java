package dev.snri.spring.reactive.demo.web.session.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data
@RequiredArgsConstructor
public class UserResponse implements Serializable {

    private final int id;

    private final String note;
}
