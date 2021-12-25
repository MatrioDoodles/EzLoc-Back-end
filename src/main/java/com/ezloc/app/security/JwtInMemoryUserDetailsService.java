package com.ezloc.app.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ezloc.app.entities.User;
import com.ezloc.app.repositories.UserRepository;




@Service
public class JwtInMemoryUserDetailsService implements UserDetailsService {

  static List<User> userList = new ArrayList<>();
  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	  userList = userRepository.findAll();
    Optional<User> findFirst = userList.stream()
        .filter(user -> user.getUsername().equals(username)).findFirst();

    if (!findFirst.isPresent()) {
      throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", username));
    }

    return findFirst.get();
  }

}


