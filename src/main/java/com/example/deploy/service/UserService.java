package com.example.deploy.service;

import com.example.deploy.dto.UserDto;

public interface UserService {
    UserDto save(UserDto userDto);
    UserDto deleteById(Long userId);
}
