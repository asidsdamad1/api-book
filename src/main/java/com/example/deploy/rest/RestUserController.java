package com.example.deploy.rest;

import com.example.deploy.dto.UserDto;
import com.example.deploy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestUserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/api/users", method = RequestMethod.POST)
    public UserDto saveUser(@RequestBody UserDto dto) {
        return this.userService.save(dto);
    }


}
