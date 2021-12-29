package com.ezloc.app.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.ezloc.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ezloc.app.entities.User;




@Service
public class JwtInMemoryUserDetailsService implements UserDetailsService {

  static List<User> userList = new ArrayList<>();

  private final UserService userService;

  @Autowired
  public JwtInMemoryUserDetailsService(UserService userService) {
    this.userService = userService;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	  userList = userService.findAll();
    Optional<User> findFirst = userList.stream()
        .filter(user -> user.getUsername().equals(username)).findFirst();

    if (!findFirst.isPresent()) {
      throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", username));
    }

    return findFirst.get();
  }

}


