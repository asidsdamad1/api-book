package com.example.deploy.dto;

import com.example.deploy.domain.Book;
import com.example.deploy.domain.Publisher;

import java.util.ArrayList;
import java.util.List;

public class PublisherDto extends BaseObjectDto{
    private String code;
    private String name;
    private List<BookDto> books;

    public PublisherDto() { }
    public PublisherDto(Publisher entity) {
        this(entity, true);
    }
    public PublisherDto(Publisher entity, boolean simple) {
        if(entity !=null){
            this.id = entity.getId();
            this.code = entity.getCode();
            this.name = entity.getName();
            if(entity.getBooks()!=null && entity.getBooks().size()>0) {
                this.books = new ArrayList<>();
                for(Book item: entity.getBooks()) {
                    this.books.add(new BookDto(item, false));
                }
            }
        }

    }

    public PublisherDto(Publisher entity, boolean simple, int type) {
        if(entity != null) {
            this.id = entity.getId();
            this.code = entity.getCode();
            this.name = entity.getName();
        }
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<BookDto> getBooks() {
        return books;
    }

    public void setBooks(List<BookDto> books) {
        this.books = books;
    }
}
