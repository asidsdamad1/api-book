package com.example.deploy.repository;

import com.example.deploy.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u where u.username=:username")
    User getUserByUserName(@Param("username") String username);

    @Query("select u from User u left join fetch u.role where u.username = ?1")
    User findByUsernameAndPerson(String username);
}
