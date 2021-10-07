package com.example.deploy;

import com.example.deploy.domain.Role;
import com.example.deploy.repository.RoleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class RoleRepositoryTests {
    @Autowired
    RoleRepository repo;

    @Test
    public void testCreateRoles() {
        Role user = new Role("ROLE_USER");
        Role admin = new Role("ROLE_ADMIN");
        Role superAdmin = new Role("ROLE_SUPER_ADMIN");

        repo.saveAll(List.of(user, admin, superAdmin));

        List<Role> roleList = repo.findAll();

        assertThat(roleList.size()).isEqualTo(3);
    }
}
