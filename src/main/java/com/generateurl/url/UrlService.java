package com.generateurl.url;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;


@Service
public class UrlService {
    private final UrlRepository urlRepository;

    public UrlService(UrlRepository urlRepository) {
        this.urlRepository = urlRepository;
    }

    @Transactional
    public String createUrl(String originalUrl) {
        Url mapping = new Url();
        mapping.setOriginalUrl(originalUrl);
        urlRepository.save(mapping);
        return mapping.getShortCode();
    }

    public String getUrl(String shortCode) {
        return urlRepository.findByShortCode(shortCode)
                .map(Url::getOriginalUrl)
                .orElseThrow(() -> new RuntimeException("Short URL not found"));
    }
}

