package com.example.deploy.repository;

import com.example.deploy.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    @Query("select r from Role r where r.name=?1")
    public Role findByName(String name);
}
