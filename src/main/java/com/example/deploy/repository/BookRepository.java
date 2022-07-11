package com.example.deploy.repository;

import com.example.deploy.domain.Book;
import com.example.deploy.domain.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {
    @Query("select e from Book e where e.code = ?1")
    List<Book> getByCode(String code);

    @Query("select count(entity.id) FROM Book entity where entity.code =?1 and (entity.id <> ?2 or ?2 is null) ")
    Long checkCode(String code, UUID id);
}
