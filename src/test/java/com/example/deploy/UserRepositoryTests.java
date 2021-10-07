package com.example.deploy;

import com.example.deploy.domain.Role;
import com.example.deploy.domain.User;
import com.example.deploy.repository.RoleRepository;
import com.example.deploy.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTests {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setEmail("asidsdamad1@gmail.com");
        user.setPassword("123456");
        user.setUsername("hieunt");
        user.setActive(true);

        User savedUser = userRepository.save(user);

        User existUser = entityManager.find(User.class, savedUser.getId());

        assertThat(existUser.getUsername()).isEqualTo(user.getUsername());
    }

    @Test
    public void addRoleToUser() {
        User user = new User();
        user.setEmail("asidsdamad2@gmail.com");
        user.setPassword("admin");
        user.setUsername("admin");
        user.setActive(true);

        Role roleAdmin = roleRepository.findByName("ROLE_ADMIN");
        user.addRole(roleAdmin);

        User savedUser = userRepository.save(user);

        assertThat(savedUser.getRole().size()).isEqualTo(1);

    }

    @Test
    public void addRoleToExistUser() {
        User user = userRepository.findById(2L).get();

        Role roleUser = roleRepository.findByName("ROLE_USER");
        user.addRole(roleUser);

        Role roleAdmin  = new Role(2L);
        user.addRole(roleAdmin);

        User savedUser = userRepository.save(user);

        assertThat(savedUser.getRole().size()).isEqualTo(2);
    }
}
