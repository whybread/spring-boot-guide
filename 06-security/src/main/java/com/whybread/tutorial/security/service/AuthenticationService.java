package com.whybread.tutorial.security.service;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;


/**
 * This service is used to customize your `@PreAuthorize` parameter.
 * You can utilize this service to customize your authorization condition.
 * 
 * **Usage**
 *    See the `userCPage` method of `UserController` class.
 *    The code works well with a `AuthenticationService` bean named `authenticationService`
 */
@Service
public class AuthenticationService {
  /*
   * Compares a parameter `usernameOnUrl` with the authenticatedUsername from the Spring Security Context.
   * 
   * You should be aware of the case that the user is not signed in.
   * This method will return `true` with a parameter `"AnonymousUser"` when the user is not authenticated.
   * Refer to the material below.
   *    [jakarta ee - Why is the 'anonymousUser' authenticated in Spring Security? - Stack Overflow]
   *    (https://stackoverflow.com/questions/26101738/why-is-the-anonymoususer-authenticated-in-spring-security)
   * So it's recommended to use this method with `isAnonymousUser()` method.
   * 
   * @Returns `true` if two are identical, `false otherwise.
   */
  public boolean isCurrentUsername(String usernameOnUrl) {
    // Implementation reference
    //    [How to Access the Current Logged-In Username in Spring Security - DZone Security]
    //    (https://dzone.com/articles/how-to-get-current-logged-in-username-in-spring-se)
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String authenticatedUsername;
    if (principal instanceof UserDetails) {
      authenticatedUsername = ((UserDetails) principal).getUsername();
    } else {
      authenticatedUsername = principal.toString();
    }
    // System.out.println(authenticatedUsername + " has arrived to " + usernameOnUrl);

    return authenticatedUsername.equals(usernameOnUrl);
  }

  /*
   * @Returns `true` if authenticated user has an authority `"ROLE_ADMIN"`, `false otherwise.
   */
  public boolean isAdmin() {
    // Implementation reference
    //    [Spring Security: Check If a User Has a Role in Java | Baeldung]
    //    (https://www.baeldung.com/spring-security-check-user-role)
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if (principal instanceof UserDetails) {
      return ((UserDetails) principal).getAuthorities().stream()
        .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
    }

    return false;
  }

  public boolean hasAccess(String usernameOnUrl) {
    if (isAnonymousUser())
      return false;

    return isAdmin() | isCurrentUsername(usernameOnUrl);
  }

  
  public boolean isAnonymousUser() {
    // Implementation reference
    //    [How to check if user is logged in or anonymous in Spring Security - Stack Overflow]
    //    (https://stackoverflow.com/questions/57053736/how-to-check-if-user-is-logged-in-or-anonymous-in-spring-security)
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return authentication instanceof AnonymousAuthenticationToken;
  }
}
