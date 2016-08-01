package com.epam.newsmanager.service.security;


import com.epam.newsmanager.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserDetails userDetails = null;
        //User user = userService.findByLogin(s, true);
        //if (user != null) {
        //    List<GrantedAuthority> authorities = new ArrayList<>();
        //    for (Role role : user.getRoles()) {
        //        authorities.add(new SimpleGrantedAuthority(role.getName()));
        //    }
        //    userDetails = new org.springframework.security.core.userdetails.User(s, user.getPassword(), authorities);
        //}

        return userDetails;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
