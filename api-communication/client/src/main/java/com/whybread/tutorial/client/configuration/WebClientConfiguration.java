package com.whybread.tutorial.client.configuration;

import java.util.Collections;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.Setter;


/*
 * We can implement Non-blocking I/O using WebClient (while blocking is also possible).
 * 
 * Then what are the configurations and how can we change them? Refer to this Korean material: https://medium.com/@odysseymoon/spring-webclient-%EC%82%AC%EC%9A%A9%EB%B2%95-5f92d295edc0
 * There is also an easy tutorial (English): https://www.baeldung.com/spring-5-webclient
 * 
 *
 */
@Configuration
// External configuration (usually from application.yml)
@ConfigurationProperties(prefix = "api.web-client")
// Needed for external configuration (setters would be used).
@Setter
public class WebClientConfiguration {

  // The only field which is controlled by external configuration.
  // The default value is "http://localhost:8080"
  // You can add more fields if you need.
  private String baseUrl = "http://localhost:8080";

  // First way to make your own WebClient using WebClient's static create method.
  @Bean
  public WebClient myWebClient1() {

    WebClient myWebClient = WebClient.create(baseUrl);

    return myWebClient;

  }
  
  /*
  * Second way to make your own WebClient using WebClient's static builder method.
  * To learn more, follow the links above (the class declaration).
  * The attributes are not so useful in this tutorial (except for baseUrl), but I put them for an example.
  */
  @Bean
  @Primary
  public WebClient myWebClient2() {

    WebClient myWebClient = WebClient
      .builder()
        .baseUrl(baseUrl)
        .defaultCookie("cookieKey", "cookieValue")
        .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
        .defaultUriVariables(Collections.singletonMap("url", baseUrl))
      .build();

    return myWebClient;

  }
}