package com.example.deploy.models;

import com.example.deploy.domain.Role;
import com.example.deploy.domain.User;

import java.util.Set;

public class RegistrationUser extends User {

    private Set<Role> role;

    public Set<Role> getRole() {
        return role;
    }

    public void setRole(Set<Role> role) {
        this.role = role;
    }

}