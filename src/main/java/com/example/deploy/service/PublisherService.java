package com.example.deploy.service;

import com.example.deploy.domain.Publisher;
import com.example.deploy.dto.PublisherDto;
import com.example.deploy.functiondto.SearchDto;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface PublisherService {
    PublisherDto saveOrUpdate(PublisherDto dto, UUID id);
    Page<PublisherDto>  searchByPage(SearchDto dto);
    Boolean delete(UUID id);
    PublisherDto getById(UUID id);
    PublisherDto getByCode(String code);
    PublisherDto checkCode(String code, UUID id);
}
