package com.example.deploy.service;

import com.example.deploy.dto.BookDto;
import com.example.deploy.dto.EmployeeDto;
import com.example.deploy.functiondto.BookSearchDto;
import com.example.deploy.functiondto.EmployeeSearchDto;
import org.springframework.data.domain.Page;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public interface BookService {
    public BookDto saveOrUpdate(BookDto dto, UUID id);

    public List<BookDto> getAll();

    Page<BookDto> searchByPage(BookSearchDto dto);

    public Boolean delete(UUID id);

    public  BookDto getByCode(String code);

    public  BookDto getById(UUID id);

    public  Boolean checkDuplicateCode(String code, UUID id);

}
