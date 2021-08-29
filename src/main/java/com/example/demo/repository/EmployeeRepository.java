package com.example.demo.repository;

import com.example.demo.domain.Employee;
import com.example.demo.dto.EmployeeDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, UUID> {
    @Query("select e from Employee e where e.code = :code")
    EmployeeDto getByCode(String code);

    @Query("select count(entity.id) FROM Employee entity where entity.code =?1 and (entity.id <> ?2 or ?2 is null) ")
    Long checkCode(String code, UUID id);
}
