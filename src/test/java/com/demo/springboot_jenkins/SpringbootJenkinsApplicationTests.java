package com.demo.springboot_jenkins;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.containsString;

/**
 * Test class for the SpringbootJenkinsApplication (URL Shortener).
 * This class contains unit tests to verify the API's behavior.
 */
@SpringBootTest
@AutoConfigureMockMvc
class SpringbootJenkinsApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    /**
     * Tests the entire workflow:
     * 1. Shorten a URL by calling the /shorten endpoint.
     * 2. Use the returned short code to call the redirect endpoint.
     * 3. Verify that the redirect goes to the correct original URL.
     */
    @Test
    void testShortenAndRedirectWorkflow() throws Exception {
        String originalUrl = "https://www.google.com";

        // Step 1: Call the /shorten endpoint and get the result
        MvcResult result = mockMvc.perform(post("/shorten")
                        .content(originalUrl))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("http://localhost:8080/")))
                .andReturn();

        String shortUrl = result.getResponse().getContentAsString();
        // Extract the 6-character short code from the full short URL
        String shortCode = shortUrl.substring(shortUrl.length() - 6);

        // Step 2: Call the redirect endpoint with the new short code
        mockMvc.perform(get("/" + shortCode))
                .andExpect(status().is3xxRedirection()) // Expect a redirect status (302)
                .andExpect(redirectedUrl(originalUrl)); // Expect it to redirect to the original URL
    }

    /**
     * Tests the scenario where a non-existent short code is used.
     * Expects a 404 Not Found status.
     */
    @Test
    void testInvalidShortCodeShouldReturnNotFound() throws Exception {
        mockMvc.perform(get("/invalid"))
                .andExpect(status().isNotFound());
    }
}
