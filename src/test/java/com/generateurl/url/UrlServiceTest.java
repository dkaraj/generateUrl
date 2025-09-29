package com.generateurl.url;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UrlService.class})
@DisabledInAotMode
@ExtendWith(SpringExtension.class)
class UrlServiceTest {
    @MockitoBean
    private UrlRepository urlRepository;

    @Autowired
    private UrlService urlService;

    /**
     * Test {@link UrlService#createUrl(String)}.
     *
     * <ul>
     *   <li>Given {@link Url#Url()} Id is one.
     *   <li>Then return {@code null}.
     * </ul>
     *
     * <p>Method under test: {@link UrlService#createUrl(String)}
     */
    @Test
    @DisplayName("Test createUrl(String); given Url() Id is one; then return 'null'")
    @Tag("MaintainedByDiffblue")
    void testCreateUrl_givenUrlIdIsOne_thenReturnNull() {
        // Arrange
        Url url = new Url();
        url.setId(1L);
        url.setOriginalUrl("https://example.org/example");
        url.setShortCode("https://example.org/example");
        when(urlRepository.save(Mockito.<Url>any())).thenReturn(url);

        // Act
        String actualCreateUrlResult = urlService.createUrl("https://example.org/example");

        // Assert
        verify(urlRepository).save(isA(Url.class));
        assertNull(actualCreateUrlResult);
    }

    /**
     * Test {@link UrlService#createUrl(String)}.
     *
     * <ul>
     *   <li>Then throw {@link RuntimeException}.
     * </ul>
     *
     * <p>Method under test: {@link UrlService#createUrl(String)}
     */
    @Test
    @DisplayName("Test createUrl(String); then throw RuntimeException")
    @Tag("MaintainedByDiffblue")
    void testCreateUrl_thenThrowRuntimeException() {
        // Arrange
        when(urlRepository.save(Mockito.<Url>any())).thenThrow(new RuntimeException());

        // Act and Assert
        assertThrows(RuntimeException.class, () -> urlService.createUrl("https://example.org/example"));
        verify(urlRepository).save(isA(Url.class));
    }

    /**
     * Test {@link UrlService#getUrl(String)}.
     *
     * <ul>
     *   <li>Given {@link Url#Url()} OriginalUrl is {@code null}.
     *   <li>Then throw {@link RuntimeException}.
     * </ul>
     *
     * <p>Method under test: {@link UrlService#getUrl(String)}
     */
    @Test
    @DisplayName(
            "Test getUrl(String); given Url() OriginalUrl is 'null'; then throw RuntimeException")
    @Tag("MaintainedByDiffblue")
    void testGetUrl_givenUrlOriginalUrlIsNull_thenThrowRuntimeException() {
        // Arrange
        Url url = new Url();
        url.setId(1L);
        url.setOriginalUrl(null);
        url.setShortCode("https://example.org/example");
        Optional<Url> ofResult = Optional.of(url);
        when(urlRepository.findByShortCode(Mockito.<String>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(RuntimeException.class, () -> urlService.getUrl("https://example.org/example"));
        verify(urlRepository).findByShortCode("https://example.org/example");
    }

    /**
     * Test {@link UrlService#getUrl(String)}.
     *
     * <ul>
     *   <li>Given {@link UrlRepository} {@link UrlRepository#findByShortCode(String)} throw {@link
     *       RuntimeException#RuntimeException()}.
     * </ul>
     *
     * <p>Method under test: {@link UrlService#getUrl(String)}
     */
    @Test
    @DisplayName(
            "Test getUrl(String); given UrlRepository findByShortCode(String) throw RuntimeException()")
    @Tag("MaintainedByDiffblue")
    void testGetUrl_givenUrlRepositoryFindByShortCodeThrowRuntimeException() {
        // Arrange
        when(urlRepository.findByShortCode(Mockito.<String>any())).thenThrow(new RuntimeException());

        // Act and Assert
        assertThrows(RuntimeException.class, () -> urlService.getUrl("https://example.org/example"));
        verify(urlRepository).findByShortCode("https://example.org/example");
    }

    /**
     * Test {@link UrlService#getUrl(String)}.
     *
     * <ul>
     *   <li>Then return {@code https://example.org/example}.
     * </ul>
     *
     * <p>Method under test: {@link UrlService#getUrl(String)}
     */
    @Test
    @DisplayName("Test getUrl(String); then return 'https://example.org/example'")
    @Tag("MaintainedByDiffblue")
    void testGetUrl_thenReturnHttpsExampleOrgExample() {
        // Arrange
        Url url = new Url();
        url.setId(1L);
        url.setOriginalUrl("https://example.org/example");
        url.setShortCode("https://example.org/example");
        Optional<Url> ofResult = Optional.of(url);
        when(urlRepository.findByShortCode(Mockito.<String>any())).thenReturn(ofResult);

        // Act
        String actualUrl = urlService.getUrl("https://example.org/example");

        // Assert
        verify(urlRepository).findByShortCode("https://example.org/example");
        assertEquals("https://example.org/example", actualUrl);
    }
}
