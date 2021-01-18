package com.whybread.tutorial.logging.controllers3;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class SafeController {

  @RequestMapping("/safe")
  public String index() {
    log.trace("Safe Trace");
    log.debug("Safe DEBUG");
    log.info("Safe INFO");
    log.warn("Safe WARN");
    log.error("Safe ERROR");

    return "Hello Safe";
  }

}
