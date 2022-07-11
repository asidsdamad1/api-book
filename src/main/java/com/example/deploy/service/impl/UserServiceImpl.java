package com.example.deploy.service.impl;

import com.example.deploy.domain.Role;
import com.example.deploy.domain.User;
import com.example.deploy.dto.UserDto;
import com.example.deploy.repository.RoleRepository;
import com.example.deploy.repository.UserRepository;
import com.example.deploy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDto save(UserDto dto) {
        if(dto == null){
            return null;
        }
        User user = null;
        if(dto.getEmail() != null && StringUtils.hasText(dto.getEmail())){
            user = new User();

        }

        user.setEmail(dto.getEmail());

        user.setActive(true);


        Role role =	roleRepository.findByName("ROLE_USER");

        Set<Role> roles = new HashSet();
        roles.add(role);

        user.setRole(roles);
        user.setUsername(dto.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(dto.getPassword()));


        user =  userRepository.save(user);



        if(user != null){
            return new UserDto(user);
        }


        return null;

    }

    @Override
    public UserDto deleteById(Long userId) {

        return null;
    }

    @Override
    public UserDto getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();
        String userName = null;
        if (principal instanceof UserDetails) {
            UserDetails userDetail = (UserDetails)principal;
            userName = userDetail.getUsername();
        } else {
            userName = principal.toString();
        }

        if (userName != null) {
            User entity = this.userRepository.findByUsernameAndPerson(userName);
            if (entity != null) {
                return new UserDto(entity);
            }
        }

        return null;
    }
}
