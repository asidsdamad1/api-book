package com.example.deploy.rest;

import com.example.deploy.dto.BookDto;
import com.example.deploy.dto.BookDto;
import com.example.deploy.functiondto.BookSearchDto;
import com.example.deploy.functiondto.EmployeeSearchDto;
import com.example.deploy.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/books")
public class RestBookController {
    @Autowired
    BookService service;

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<BookDto> save(@RequestBody BookDto dto) {
        BookDto value = service.saveOrUpdate(dto, null);
        return new ResponseEntity<BookDto>(value, (value != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<BookDto> update(@RequestBody BookDto dto, @PathVariable("id") UUID id) {
        BookDto result = service.saveOrUpdate(dto, id);
        return new ResponseEntity<BookDto>(result, (result != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(path = "", method = RequestMethod.GET)
    public ResponseEntity<List<BookDto>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }

    @RequestMapping(path = "/search", method = RequestMethod.POST)
    public ResponseEntity<Page<BookDto>> search(@RequestBody BookSearchDto dto) {
        Page<BookDto> result = service.searchByPage(dto);
        return new ResponseEntity<Page<BookDto>>(result, (result != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(path = "/getByCode/{code}", method = RequestMethod.POST)
    public ResponseEntity<BookDto> getBookByCode(@PathVariable String code) {
        BookDto result = service.getByCode(code);
        return new ResponseEntity<BookDto>(result, (result != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }
    @RequestMapping(path = "/{id}", method = RequestMethod.POST)
    public ResponseEntity<BookDto> getBookById(@PathVariable UUID id) {
        BookDto result = service.getById(id);
        return new ResponseEntity<BookDto>(result, (result != null) ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> delete(@PathVariable("id") UUID id) {
        return ResponseEntity.ok().body(service.delete(id));
    }

}
