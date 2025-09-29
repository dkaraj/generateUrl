package com.generateurl.shared;

import com.generateurl.url.UrlResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class DefaultResponse {
    HttpStatus status;
    String message;
    UrlResponse responseBody;
}
