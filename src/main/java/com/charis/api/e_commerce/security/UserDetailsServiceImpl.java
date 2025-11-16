package com.charis.api.e_commerce.security;

import com.charis.api.e_commerce.identity.domain.User;
import com.charis.api.e_commerce.identity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
   private final UserService userService;

   @Override
   public UserDetails loadUserByUsername(String userid) throws UsernameNotFoundException {
       try {
           UUID userId = UUID.fromString(userid);
           User user = userService.getUserById(userId);
           return UserPrincipal.from(user);
       } catch (IllegalArgumentException ex) {
           User user = userService.getUserByEmail(userid);
           return UserPrincipal.from(user);
       }
   }
}
