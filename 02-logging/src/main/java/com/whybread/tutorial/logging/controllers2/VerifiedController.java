package com.whybread.tutorial.logging.controllers2;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class VerifiedController {

  @RequestMapping("/verified")
  public String index() {
    log.trace("Verified Trace");
    log.debug("Verified DEBUG");
    log.info("Verified INFO");
    log.warn("Verified WARN");
    log.error("Verified ERROR");

    return "Hello Verified";
  }

}