package com.balzzak.accountservice.service;

import com.balzzak.accountservice.model.CustomUserDetails;
import com.balzzak.accountservice.model.User;
import com.balzzak.accountservice.model.UserDto;
import com.balzzak.accountservice.model.UserRole;
import com.balzzak.accountservice.repo.UserRepo;
import com.balzzak.accountservice.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtil jwtUtil;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username + " 에 해당하는 유저를 찾을 수 없습니다.");
        }
        return new CustomUserDetails(user);
    }

    public User createNewUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRoles(Set.of(UserRole.USER));

        return userRepo.save(user);
    }

    public String getToken(UserDto userDto) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userDto.getUsername(), userDto.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect username of password", e);
        }

        final UserDetails userDetails = loadUserByUsername(userDto.getUsername());
        return jwtUtil.generateToken(userDetails);
    }
}
