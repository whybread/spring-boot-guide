package com.whybread.tutorial.client.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

/*
 * This provides a few number of models that controllers may need.
 * The ClientService object will call the api with an auto-wired WebClient bean.
 */
@Service
public class ClientService {

  @Autowired
  WebClient webClient;

  // Returns the number of clients.
  public String getNumberOfClients() {
    // Just for a pracice, some informations would be sent through HTTP header.
    // The API provider will check if the request is legal and valid.
    String username = "test-user";
    String token = "Maybe-a-hashed-string-generated-from-the-api-provider.";

    // Send an HTTP GET request to "<base-url>/users" with an authorization
    // information
    // inside the HTTP header, and converts the result as a Mono<Map> class.
    // To learn more about Mono<T> class, refer to this Korean material:
    // https://tech.kakao.com/2018/05/29/reactor-programming/
    Mono<String> result = webClient.get().uri("/users").header("Authorization", "Basic " + username + ":" + token)
        .retrieve().bodyToMono(String.class);


    // You can get the Map object inside the result with a blocking I/O.
    return result.block();

  }

  // Returns the name of a certain client found by clientId.
  public String getClientNameById(int clientId) {
    Mono<String> result = webClient.get()
      .uri("/users/{userId}", clientId)
      .retrieve()
      .bodyToMono(String.class);


    return result.block();
    

  }

}
