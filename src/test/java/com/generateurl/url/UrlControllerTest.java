package com.generateurl.url;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.generateurl.shared.UrlRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {UrlController.class})
@DisabledInAotMode
@ExtendWith(SpringExtension.class)
class UrlControllerTest {
    @Autowired
    private UrlController urlController;

    @MockitoBean
    private UrlService urlService;

    /**
     * Test {@link UrlController#shortenUrl(UrlRequest)}.
     *
     * <p>Method under test: {@link UrlController#shortenUrl(UrlRequest)}
     */
    @Test
    @DisplayName("Test shortenUrl(UrlRequest)")
    @Tag("MaintainedByDiffblue")
    void testShortenUrl() throws Exception {
        // Arrange
        when(urlService.createUrl(Mockito.<String>any())).thenReturn("https://example.org/example");

        UrlRequest urlRequest = new UrlRequest();
        urlRequest.setUrl("https://example.org/example");

        MockHttpServletRequestBuilder requestBuilder =
                MockMvcRequestBuilders.post("/api/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(
                                JsonMapper.builder().findAndAddModules().build().writeValueAsString(urlRequest));

        // Act and Assert
        MockMvcBuilders.standaloneSetup(urlController)
                .build()
                .perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json"))
                .andExpect(
                        content()
                                .string(
                                        "{\"status\":\"OK\",\"message\":\"Generated\",\"responseBody\":{\"url\":\"http://localhost:8080/api/https://example"
                                                + ".org/example\"}}"));
    }

    /**
     * Test {@link UrlController#getOriginalUrl(String)}.
     *
     * <p>Method under test: {@link UrlController#getOriginalUrl(String)}
     */
    @Test
    @DisplayName("Test getOriginalUrl(String)")
    @Tag("MaintainedByDiffblue")
    void testGetOriginalUrl() throws Exception {
        // Arrange
        when(urlService.getUrl(Mockito.<String>any())).thenReturn("https://example.org/example");

        MockHttpServletRequestBuilder requestBuilder =
                MockMvcRequestBuilders.get("/api/{shortCode}", "https://example.org/example");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(urlController)
                .build()
                .perform(requestBuilder)
                .andExpect(status().isNotFound());
    }
}
