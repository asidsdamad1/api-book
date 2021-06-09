package com.example.demo.service.impl;

import com.example.demo.dto.EmployeeDto;
import com.example.demo.dto.search.EmployeeSeachDto;
import com.example.demo.entity.EmployeeEntity;
import com.example.demo.excel.EmployeeExcelExporter;
import com.example.demo.repository.EmployeeRepository;
import com.example.demo.service.EmployeeService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import org.springframework.web.bind.annotation.PathVariable;


import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeRepository repository;
    @Autowired
    EntityManager entityManager;

    @Override
    public EmployeeEntity insertData(EmployeeDto dto) {
        EmployeeEntity entity = new EmployeeEntity();
        entity.setCode(dto.getCode());
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
        entity.setAge(dto.getAge());
        repository.save(entity);
        return entity;
    }

    @Override
    public List<EmployeeDto> getAll() {
        List<EmployeeDto> dtoList = new ArrayList<>();
        List<EmployeeEntity> all = repository.findAll();
        for (EmployeeEntity entity : all) {
            dtoList.add(new EmployeeDto(entity));
        }
        return dtoList;
    }

    @Override
    public List<EmployeeDto> search(EmployeeSeachDto seachDto) {
        String sql = "select a from EmployeeEntity a";
        sql += this.setSql(seachDto);
        Query query = entityManager.createQuery(sql);
        query = this.setParam(query, seachDto);

        List<EmployeeEntity> list = query.getResultList();
        return this.toListDto(list);
    }

    public List<EmployeeDto> toListDto(List<EmployeeEntity> entityList) {
        return entityList.stream()
                .map(employee -> {
                    EmployeeDto dto = new EmployeeDto();
                    dto.setId(employee.getId());
                    dto.setCode(employee.getCode());
                    dto.setName(employee.getName());
                    dto.setEmail(employee.getEmail());
                    dto.setPhone(employee.getPhone());
                    dto.setAge(employee.getId());
                    return dto;
                }).collect(Collectors.toList());
    }

    private Query setParam(Query query, EmployeeSeachDto dto) {
        if (this.isNotEmpty(dto.getCode())) {
            query = query.setParameter("code", dto.getCode());
        }
        return query;
    }

    private String setSql(EmployeeSeachDto dto) {
        String where = " where 1=1 ";
        if (this.isNotEmpty(dto.getCode())) {
            where += " and a.code = :code ";
        }
        return where;
    }

    private boolean isNotEmpty(String s) {
        return s.length() > 0 && s != null;
    }

    @Override
    public Boolean update(EmployeeDto dto) {
        if (dto != null) {
            EmployeeEntity employee = repository.findByCode(dto.getCode());
            employee.setName(dto.getName());
            employee.setEmail(dto.getEmail());
            employee.setPhone(dto.getPhone());
            employee.setAge(dto.getAge());
            repository.save(employee);
            return true;
        }
        return false;
    }

    @Override
    public Boolean delete(String code) {
        if (code != null) {
            int id = repository.findByCode(code).getId();
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Employee_info.xlsx";

        response.setHeader(headerKey, headerValue);
        List<EmployeeEntity> list = repository.findAll();
        EmployeeExcelExporter exp = new EmployeeExcelExporter(list);
        exp.export(response);
    }


}
