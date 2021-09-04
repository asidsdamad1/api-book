package com.example.deploy.service.impl;

import com.example.deploy.domain.Employee;
import com.example.deploy.dto.EmployeeDto;
import com.example.deploy.excel.EmployeeExcelExporter;
import com.example.deploy.functiondto.EmployeeSearchDto;
import com.example.deploy.repository.EmployeeRepository;
import com.example.deploy.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service

public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeRepository repository;
    @Autowired
    EntityManager entityManager;


    @Override
    public EmployeeDto saveOrUpdate(EmployeeDto dto, UUID id) {
        if(dto != null) {
            Employee entity =null;
            if(id != null) {
                entity = repository.getById(id);
            }
            if(entity == null) {
                entity = new Employee();
            }
            entity.setCode(dto.getCode());
            entity.setEmail(dto.getEmail());
            entity.setName(dto.getName());
            entity.setAge(dto.getAge());
            entity.setPhone(dto.getPhone());

            repository.save(entity);
            if(entity != null) {
                return new EmployeeDto(entity);
            }
        }
        return null;
    }

    @Override
    public List<EmployeeDto> getAll() {
        List<EmployeeDto> dtoList = new ArrayList<>();
        List<Employee> all = repository.findAll();
        for (Employee entity : all) {
            dtoList.add(new EmployeeDto(entity));
        }
        return dtoList;
    }

    @Override
    public Page<EmployeeDto> searchByPage(EmployeeSearchDto searchDto) {
        if(searchDto == null){
            return  null;
        }
        int pageIndex = searchDto.getPageIndex();
        int pageSize = searchDto.getPageSize();
        if(pageIndex > 0) {
            pageIndex--;
        } else {
            pageIndex = 0;
        }
        String whereClause = "";
        String orderBy = "ORDER BY entity.code";
        String sqlCount = "select count(entity.id) from Employee as entity where (1=1)";
        String sql= "select new com.example.deploy.dto.EmployeeDto(entity) from Employee as entity where (1=1)";
        if(searchDto.getText() != null && StringUtils.hasText(searchDto.getText()) ) {
            whereClause = "AND (entity.name LIKE :text OR entity.code LIKE :text)";
        }
        sql += whereClause + orderBy;
        sqlCount += whereClause;
        Query q = entityManager.createQuery(sql, EmployeeDto.class);
        Query qCount = entityManager.createQuery(sqlCount);

        if(searchDto.getText() != null && StringUtils.hasText(searchDto.getText()) ) {
            q.setParameter("text", '%' + searchDto.getText().trim() + '%');
            qCount.setParameter("text", '%' + searchDto.getText().trim() + '%');
        }

        int startPosition = pageIndex * pageSize;
        q.setFirstResult(startPosition);
        q.setMaxResults(pageSize);
        List<EmployeeDto> entities = q.getResultList();
        long count= (long) qCount.getSingleResult();

        Pageable pageable = PageRequest.of(pageIndex, pageSize);
        Page<EmployeeDto> result = new PageImpl<EmployeeDto>(entities, pageable, count);

        return  result;
    }

    @Override
    public Boolean delete(UUID id) {
        if (id != null) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public EmployeeDto getByCode(String code) {
        Employee entity = repository.getByCode(code);
        if(code != null) {
            return new EmployeeDto(entity);
        }
        return null;
    }

    @Override
    public EmployeeDto getEmployeeById(UUID id) {
        Employee entity = repository.getById(id);
        if(entity != null) {
            return new EmployeeDto(entity);
        }
        return null;
    }

    @Override
    public Boolean checkDuplicateCode(String code, UUID id) {
        if(code != null && StringUtils.hasText(code)) {
            Long count = repository.checkCode(code, id);
            return count != 0l;
        }
        return null;
    }

    @Override
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Employee_info.xlsx";

        response.setHeader(headerKey, headerValue);
        List<Employee> list = repository.findAll();
        EmployeeExcelExporter exp = new EmployeeExcelExporter(list);
        exp.export(response);
    }



}
