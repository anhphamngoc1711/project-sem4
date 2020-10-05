package com.example.projectsem4.service;


import com.example.projectsem4.entity.Role;
import com.example.projectsem4.entity.User;
import com.example.projectsem4.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.getUserByUsername(username);

        if (user == null){
            throw new UsernameNotFoundException("Could not find user");
        }
        return new MyUserDetails(user);
    }
}
//@Override
//@Transactional(readOnly = true)
//public UserDetails loadUserByUsername(String username) {
//    User user = userRepository.getUserByUsername(username);
//    if (user == null) throw new UsernameNotFoundException(username);
//
//    Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
//    for (Role role : user.getRoles()){
//        grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
//    }
//
//    return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
//}
//}