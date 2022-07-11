package com.example.deploy.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tbl_category")
public class Category extends BaseObject{
    @Column(name = "code")
    private String code;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "category")
    private List<BookCategory> bookCategory;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<BookCategory> getBookCategoryItem() {
        return bookCategory;
    }

    public void setBookCategoryItem(List<BookCategory> bookCategory) {
        this.bookCategory = bookCategory;
    }
}
