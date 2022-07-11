package com.example.deploy.service;

import com.example.deploy.dto.BookCategoryDto;
import com.example.deploy.dto.BookDto;
import com.example.deploy.functiondto.SearchDto;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface BookCategoryService {
    BookCategoryDto saveOrUpdate(BookCategoryDto dto, UUID id);

    Page<BookCategoryDto> searchByPage(SearchDto dto);

    Boolean deletebyId(UUID id);

    BookCategoryDto getById(UUID id);
}
