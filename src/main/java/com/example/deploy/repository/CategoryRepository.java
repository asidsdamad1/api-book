package com.example.deploy.repository;

import com.example.deploy.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
    @Query("select e from Category e where e.id=?1")
    Category getById(UUID id);
}
