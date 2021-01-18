package com.whybread.tutorial.logging.controllers1;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class IndexController {

  @RequestMapping("/")
  public String index() {
    log.trace("Index Trace");
    log.debug("Index DEBUG");
    log.info("Index INFO");
    log.warn("Index WARN");
    log.error("Index ERROR");

    return "Hello Index";
  }

}