package com.whybread.tutorial.security.config;

import com.whybread.tutorial.security.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/*
 * `WebSecurityConfig` is a custom configuration for Spring Security overall.
 * 
 * 1. You can set authentication and authorization levels for various URLs through overriding `configure` methods.
 * 
 *    - Basic information about `configure` methods
 *          [Spring boot Security : 1. 설치 및 페이지 설정 - 가리사니]
 *          (https://gs.saro.me/dev?tn=480)
 * 
 * 2. When an anonymous user (i.e. not authenticated) sends a request to an authenticated URL, Spring Security automatically redirects the user to the login page.
 *    - In the process, it even generates a default login page.
 *          [2 Spring Security 기본 - 로그인, 로그아웃 인증 · 콩정의 개발 정리 블로그]
 *          (https://jungeunlee95.github.io/java/2019/07/17/2-Spring-Security/)
 * 
 * 3. I recommend you to take the first step with Spring official tutorial below.
 *    The basic information about Spring Security - what it does and how it works - is descripted very kindly.
 * 
 *    - An official tutorial for Spring Security
 *          [Getting Started | Securing a Web Application]
 *          (https://spring.io/guides/gs/securing-web/)
 * 
 * 4. It should be considered where to locate `@Pre` and `@Post` annotations: Controller methods or Service methods
 *    And what is `@EnableGlobalMethodSecurity` annotation?
 *    For those questions, I recommend you to read the link below carefully.
 * 
 *    - When `@PreAuthorize` annotations don't work on controller methods.
 *          [spring - PreAuthorize not working on Controller - Stack Overflow]
 *          (https://stackoverflow.com/questions/32442408/preauthorize-not-working-on-controller)
 */
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  UserService userService;
  @Autowired
  PasswordEncoder passwordEncoder;

  
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
	protected void configure(HttpSecurity http) throws Exception {
    http
    /* ACL (Access control list) */
    /* 
     * In this project, `HttpSecurity` is not used for request authorization.
     * Instead, method level security is applied using `@PreAuthorize` annotation.
     * 
     * Why is method level security recommended? The difference between `@PreAuthorize` and `HttpSecurity`.
     * 
     *    [Spring Method Security with PreAuthorize | Okta Developer]
     *    (https://developer.okta.com/blog/2019/06/20/spring-preauthorize)
     * 
     *    [java - Controller (web request/antMatcher) security vs method(service) level security - Stack Overflow]
     *    (https://stackoverflow.com/questions/53358424/controller-web-request-antmatcher-security-vs-methodservice-level-security)
     * 
     */
    
      // .authorizeRequests()
      //   .antMatchers("/", "/index").permitAll()
      //   .antMatchers("/login", "/logout", "/register", "removeAccount").permitAll()
      //   .antMatchers("/admin/**").hasRole("ADMIN")
      //   .antMatchers("/{username}/**").hasAnyRole("USER", "ADMIN")
      //   .anyRequest().authenticated()
      //   .and()
      
    /* login */
      .formLogin()
        .loginPage("/login") 	// A URL for login page.
        .loginProcessingUrl("/login")
        .failureUrl("/login?result=fail") // Redirect URL after login failure
        .defaultSuccessUrl("/", true) // Redirect URL after login success (not used because of a new `successHandler` customed below).
        .usernameParameter("username")
        .passwordParameter("password")
        .successHandler(new RefererAuthenticationSuccessHandler()) // Custom success handler.
        .and()

    /* logout */
      .logout()
        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        .logoutSuccessUrl("/") // Redirect URL after logout success
        .invalidateHttpSession(true);
    ;
  }
  
  // https://www.baeldung.com/spring-security-login
  @Override
  public void configure(AuthenticationManagerBuilder auth) throws Exception {

    /* 
     * You can add in-memory users.
     * 
     * Implementation reference
     *    [Spring Security Form Login | Baeldung]
     *    (https://www.baeldung.com/spring-security-login)
     */
    
    // auth.inMemoryAuthentication()
    //   .withUser("testusername01").password(passwordEncoder.encode("testpassword01")).roles("USER", "TESTER")
    //   .and()
    //   .withUser("anonymousUser").password(passwordEncoder.encode("anonymousUser")).roles("USER");

    // This is needed for Spring Security's provided authentication.
    auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
  }

  /*
   * `RefererAuthenticationSuccessHandler` is used to redirect a user back to the originally requested URL after they log in.
   * 
   * In my case, extending `SavedRequestAwareAuthenticationSuccessHandler` works well while `SimpleUrlAuthenticationSuccessHandler` doesn't.
   * My implementation reference
   *    [Redirect to the Previous URL After Login with Spring Security | Baeldung]
   *    (https://www.baeldung.com/spring-security-redirect-login) 
   */
  private class RefererAuthenticationSuccessHandler
    extends SavedRequestAwareAuthenticationSuccessHandler
    implements AuthenticationSuccessHandler {

      public RefererAuthenticationSuccessHandler() {
        super();
        setUseReferer(true);
      }

  }
}
