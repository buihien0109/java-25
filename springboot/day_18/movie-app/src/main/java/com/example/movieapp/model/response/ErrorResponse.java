package com.example.movieapp.model.response;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ErrorResponse {
    HttpStatus status;
    String message;

    // path, actor, time
}
