package com.example.deploy.service.impl;

import com.example.deploy.domain.Role;
import com.example.deploy.domain.User;
import com.example.deploy.dto.RoleDto;
import com.example.deploy.dto.UserDto;
import com.example.deploy.repository.RoleRepository;
import com.example.deploy.repository.UserRepository;
import com.example.deploy.service.UserService;
import com.example.deploy.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;

    @Override
    public UserDto save(UserDto userDto) {
        if(userDto == null) {
            throw new IllegalArgumentException();
        } else {
            User user = null;
            if(userDto.getId() != null) {
                user = (User) userRepository.findById(userDto.getId()).get();
            }
            if(user != null) {
                user.setEmail(userDto.getEmail());
                user.setActive(userDto.getActive());
                if (userDto.getPassword() != null && userDto.getPassword().length() > 0 && userDto.getChangePass()) {
                    user.setPassword(SecurityUtils.getHashPassword(userDto.getPassword()));
                }
            } else {
                user = userDto.toEntity();
                if (userDto.getPassword() != null && userDto.getPassword().length() > 0) {
                    user.setPassword(SecurityUtils.getHashPassword(userDto.getPassword()));
                }
            }

            Iterator var5;
            ArrayList gs;
            if (userDto.getRoles() != null) {
                gs = new ArrayList();
                var5 = userDto.getRoles().iterator();

                while(var5.hasNext()) {
                    RoleDto d = (RoleDto)var5.next();
                    Role r = (Role)this.roleRepository.findById(d.getId()).get();
                    if (r != null) {
                        gs.add(r);
                    }
                }

                user.getRole().clear();
                user.getRole().addAll(gs);
            }

            user = (User)this.userRepository.save(user);
            return user != null ? new UserDto(user) : null;
        }

    }

    @Override
    public UserDto deleteById(Long userId) {

        return null;
    }
}
