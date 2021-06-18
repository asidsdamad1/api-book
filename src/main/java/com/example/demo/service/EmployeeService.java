package com.example.demo.service;

import com.example.demo.dto.EmployeeDto;
import com.example.demo.dto.search.EmployeeSeachDto;
import com.example.demo.entity.Employee;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Service
public interface EmployeeService {
    public Employee insertData(EmployeeDto dto);

    public List<EmployeeDto> getAll();

    List<EmployeeDto> search(EmployeeSeachDto dto);

    public Boolean update(EmployeeDto dto);

    public Boolean delete(String code);

    public void exportToExcel(HttpServletResponse response) throws IOException;
}
