# Spring Boot Tutorial
A Spring Boot Tutorial to study basic functions of Spring Boot and make a good Web Application.

To implement various functions with Spring Boot, take a look at the tutorial.
This document simply explains about the role Spring Boot.

## 1. What is Spring Boot?
Spring Boot has 4 major goals refer to the [official document](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#getting-started-introducing-spring-boot).
- Provide a radically faster and widely accessible getting started experience for all Spring development.
- Be opinionated out of the box, but get out of the way quickly as requirements start to diverge from the defaults.
- Provide a range of non-functional features that are common to large classes of projects (e.g. embedded servers, security, metrics, health checks, externalized configuration).
- Absolutely no code generation and no requirement for XML configuration.

So Spring Boot provides a fast development for us and a lot of conveniences such as annotation driven configurations.

When you do something, there are various ways to implement the same function. So it's important to understand the pros and cons for each alternative, and make a good choice, perhaps recommended by the providers.

## 2. `@SpringBootApplication` Annotation.

When you make a new Spring Boot project with [Spring Initializr](https://start.spring.io/), there is a class with `@SpringBootApplication` annotation.

This is a convenience annotation that is equivalent to declaring three:
- `@Configuration`: allow to register extra beans in the context or import additional configuration classes
- `@EnableAutoConfiguration`: enable Spring Boot’s auto-configuration mechanism
- `@ComponentScan`: enable `@Component` scan on the package where the application is located (see the best practices)


## 3. Getting Started.

Now, you know all the basic concepts of Spring Boot. The rest is to face concerns and solve problems.

Let's go to our first tutorial: [Hello World](./01-helloworld)!

> **Tips**: If you want to study with a fine-grained materials more, refer to Spring Boot official document (above).
