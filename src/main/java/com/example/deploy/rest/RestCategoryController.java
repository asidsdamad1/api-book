package com.example.deploy.rest;

import com.example.deploy.dto.CategoryDto;
import com.example.deploy.dto.CategoryDto;
import com.example.deploy.functiondto.BookSearchDto;
import com.example.deploy.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/category")
public class RestCategoryController {
    @Autowired
    CategoryService service;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<CategoryDto> save(@RequestBody CategoryDto dto) {
        CategoryDto value = service.saveOrUpdate(dto, null);
        return new ResponseEntity<CategoryDto>(value, (value != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<CategoryDto> update(@RequestBody CategoryDto dto, @PathVariable("id") UUID id) {
        CategoryDto result = service.saveOrUpdate(dto, id);
        return new ResponseEntity<CategoryDto>(result, (result != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<List<CategoryDto>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }

    @RequestMapping(path = "/search", method = RequestMethod.POST)
    public ResponseEntity<Page<CategoryDto>> search(@RequestBody BookSearchDto dto) {
        Page<CategoryDto> result = service.searchByPage(dto);
        return new ResponseEntity<Page<CategoryDto>>(result, (result != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(path = "/getByCode/{code}", method = RequestMethod.POST)
    public ResponseEntity<CategoryDto> getBookByCode(@PathVariable String code) {
        CategoryDto result = service.getByCode(code);
        return new ResponseEntity<CategoryDto>(result, (result != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }
    @RequestMapping(path = "/{id}", method = RequestMethod.POST)
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable UUID id) {
        CategoryDto result = service.getById(id);
        return new ResponseEntity<CategoryDto>(result, (result != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> delete(@PathVariable("id") UUID id) {
        return ResponseEntity.ok().body(service.delete(id));
    }
}
