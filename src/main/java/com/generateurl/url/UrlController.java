package com.generateurl.url;
import com.generateurl.shared.DefaultResponse;
import com.generateurl.shared.UrlRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class UrlController {
    private static final String BASE_URL = "http://localhost:8080/api/";

    private final UrlService service;

    public UrlController(UrlService service) {
        this.service = service;
    }


    @PostMapping("/")
    public ResponseEntity<DefaultResponse> shortenUrl(@RequestBody UrlRequest request) {
        String shortCode = service.createUrl(request.getUrl());
        UrlResponse response = new UrlResponse(BASE_URL + shortCode);
        DefaultResponse defaultResponse = new DefaultResponse(HttpStatus.OK, "Generated", response);
        return ResponseEntity.ok(defaultResponse);

    }
    @GetMapping("/{shortCode}")
    public ResponseEntity<DefaultResponse> getOriginalUrl(@PathVariable String shortCode) {
        String originalUrl = service.getUrl(shortCode); // Returns plain URL
        UrlResponse response = new UrlResponse(originalUrl);
        DefaultResponse defaultResponse = new DefaultResponse(HttpStatus.OK, "Success", response);
        return ResponseEntity.ok(defaultResponse);
    }


}
