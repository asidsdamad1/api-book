package com.example.deploy.service;

import com.example.deploy.dto.EmployeeDto;
import com.example.deploy.functiondto.EmployeeSearchDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.UUID;


@Service
public interface EmployeeService {
    public EmployeeDto saveOrUpdate(EmployeeDto dto, UUID id);

    public List<EmployeeDto> getAll();

    Page<EmployeeDto> searchByPage(EmployeeSearchDto dto);

    public Boolean delete(UUID id);

    public  EmployeeDto getByCode(String code);

    public  EmployeeDto getEmployeeById(UUID id);

    public  Boolean checkDuplicateCode(String code, UUID id);

    public void exportToExcel(HttpServletResponse response) throws IOException;


}
