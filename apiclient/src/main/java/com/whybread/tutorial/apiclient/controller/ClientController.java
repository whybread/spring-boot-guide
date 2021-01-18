package com.whybread.tutorial.apiclient.controller;

import com.whybread.tutorial.apiclient.service.ClientService;

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
  public Object getAllClients() {
    return clientService.getAllClients();
  }

  @GetMapping(value = "/{clientId}")
  public Object getClientById(@PathVariable int clientId) {
    return clientService.getClientById(clientId);
  }

}