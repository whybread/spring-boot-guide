package com.whybread.tutorial.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * This configuration is adding a new static resource mapping.
 * 
 * Reference
 *    [Serve Static Resources with Spring | Baeldung]
 *    (https://www.baeldung.com/spring-mvc-static-resources)
 */
@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    // In this project, all the static resources will be accessed through `/public/**`.
    registry
      .addResourceHandler("/public/**")
      .addResourceLocations("classpath:/public/");

    // You can also access to your local files.
    /*
    registry
      .addResourceHandler("/files/**")
      .addResourceLocations("file:/home/yhj/Desktop/web-files/");
    */
  }
}