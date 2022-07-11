package com.example.deploy.service;

import com.example.deploy.dto.CategoryDto;
import com.example.deploy.functiondto.SearchDto;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface CategoryService {
    public CategoryDto saveOrUpdate(CategoryDto dto, UUID id);

    public List<CategoryDto> getAll();

    Page<CategoryDto> searchByPage(SearchDto dto);

    public Boolean delete(UUID id);

    public  CategoryDto getByCode(String code);

    public  CategoryDto getById(UUID id);

    public  Boolean checkDuplicateCode(String code, UUID id);

}
