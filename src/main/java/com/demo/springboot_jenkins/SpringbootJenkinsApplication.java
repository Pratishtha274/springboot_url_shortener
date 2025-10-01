package com.demo.springboot_jenkins;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Main application class for the URL Shortener API.
 * This service provides endpoints to shorten a URL and to redirect to the original URL.
 */
@SpringBootApplication
@RestController // Add this annotation to enable REST endpoints
public class SpringbootJenkinsApplication {

    // A thread-safe map to store the mappings of short codes to long URLs.
    private final Map<String, String> urlStore = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        SpringApplication.run(SpringbootJenkinsApplication.class, args);
    }

    /**
     * Endpoint to shorten a long URL.
     * It takes a long URL in the request body, generates a 6-character short code,
     * stores the mapping, and returns the shortened URL.
     *
     * @param longUrl The original long URL to be shortened.
     * @return A response entity containing the shortened URL.
     */
    @PostMapping("/shorten")
    public ResponseEntity<String> shortenUrl(@RequestBody String longUrl) {
        // Generate a random alphanumeric string of 6 characters for the short code.
        String shortCode = RandomStringUtils.randomAlphanumeric(6);

        // Store the mapping.
        urlStore.put(shortCode, longUrl);

        String shortUrl = ServletUriComponentsBuilder
                .fromCurrentContextPath()  // e.g., http://localhost:9090
                .path("/")
                .path(shortCode)
                .toUriString();

        return ResponseEntity.ok(shortUrl+"\n");
    }

    /**
     * Endpoint to redirect from a short code to the original long URL.
     * It looks up the short code and performs an HTTP 302 redirect.
     *
     * @param shortCode The 6-character code from the shortened URL.
     * @param response  The HttpServletResponse object used to perform the redirect.
     */
    @GetMapping("/{shortCode}")
    public void redirectToOriginalUrl(@PathVariable String shortCode, HttpServletResponse response) throws IOException {
        // Look up the long URL from our storage.
        String longUrl = urlStore.get(shortCode);

        if (longUrl != null) {
            // If found, send a 302 redirect to the original URL.
            response.sendRedirect(longUrl);
        } else {
            // If not found, send a 404 Not Found error.
            response.sendError(HttpStatus.NOT_FOUND.value(), "URL not found for code: " + shortCode);
        }
    }
}
