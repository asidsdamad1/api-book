package com.example.deploy.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "tbl_publisher")
public class Publisher extends BaseObject{
    @Column(name = "code")
    private  String code;
    @Column(name = "name")
    private  String name;

    @OneToMany(mappedBy = "publisher")
    private List<Book> books;

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

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}

