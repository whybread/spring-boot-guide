package com.whybread.tutorial.client.controller;


import com.whybread.tutorial.client.service.ClientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clients")
public class ClientController {

  @Autowired
  ClientService clientService;

  @GetMapping(value = "")
  public String getNumberOfClients() {
    return clientService.getNumberOfClients();
  }

  @GetMapping(value = "/{clientId}")
  public String getClientNameById(@PathVariable int clientId) {
    return clientService.getClientNameById(clientId);
  }

}