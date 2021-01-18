package com.whybread.tutorial.apiclient.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

/*
 * This provides a few number of models that controllers may need.
 * The ClientService object will call the api with an WebClient bean, which would be auto-wired.
 */
@Service
public class ClientService {

  @Autowired
  WebClient webClient;

  // Returns all clients.
  public Object getAllClients() {
    // Just for a pracice, some informations would be sent through HTTP header.
    // The API provider will check if the request is legal and valid.
    String username = "test-user";
    String token = "Maybe-a-hashed-string-generated-from-the-api-provider.";

    // Send an HTTP GET request to "<base-url>/users" with an authorization information inside the HTTP header, and converts the result as a Mono<Map> class.
    // To learn more about Mono<T> class, refer to this Korean material: https://tech.kakao.com/2018/05/29/reactor-programming/
    Mono<Map> bodyMono = webClient.get()
      .uri("/users")
      .header("Authorization", "Basic " + username + ":" + token)
      .retrieve()
      .bodyToMono(Map.class);

    // You can get the Map object inside the result immediately with a blocking I/O.
    Map<String, String> body = bodyMono.block();


    // SUCCESS
    // Return the data if api call was successful.
    if( body.get("apiResultType").equals("SUCCESS") ) {
      return body.get("data");

    // FAIL
    // Return an empty string if the api call failed. (Perhaps not a good way.)
    }else {
      return "";
    }


  }

  // Returns a certain client found by clientId.
  // Similar implementation with getAllClients method.
  public Object getClientById(int clientId) {
    Mono<Map> bodyMono = webClient.get()
      .uri("/users/{userId}", clientId)
      .retrieve()
      .bodyToMono(Map.class);

    Map<String, String> body = bodyMono.block();

    // SUCCESS
    if( body.get("apiResultType").equals("SUCCESS") ) {
      return body.get("data");

    // FAIL
    }else {
      return "";
    }

  }

}
