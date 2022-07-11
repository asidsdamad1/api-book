package com.example.deploy.repository;

import com.example.deploy.domain.BookCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookCategoryRepository extends JpaRepository<BookCategory, UUID> {
}
