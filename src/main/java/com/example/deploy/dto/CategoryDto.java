package com.example.deploy.dto;

import com.example.deploy.domain.BookCategory;
import com.example.deploy.domain.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryDto extends BaseObjectDto{
    private String code;
    private String name;
    private String description;
    private List<BookCategoryDto> bookCategoryItem;

    public CategoryDto() { }
    public CategoryDto(Category entity) { this(entity, true);}
    public CategoryDto(Category entity, boolean simple) {
        if(entity != null) {
            this.id = entity.getId();
            this.code = entity.getCode();
            this.name = entity.getName();
            this.description = entity.getDescription();
            if (entity.getBookCategoryItem() != null && entity.getBookCategoryItem().size() > 0) {
                this.bookCategoryItem = new ArrayList<>();
                for (BookCategory item : entity.getBookCategoryItem()) {
                    this.bookCategoryItem.add(new BookCategoryDto(item, false));
                }
            }
        }
    }
    public CategoryDto(Category entity, boolean simple, int type  ){
        if(entity != null) {
            this.id = entity.getId();
            this.code = entity.getCode();
            this.name = entity.getName();
            this.description = entity.getDescription();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<BookCategoryDto> getBookCategoryItem() {
        return bookCategoryItem;
    }

    public void setBookCategoryItem(List<BookCategoryDto> bookCategoryItem) {
        this.bookCategoryItem = bookCategoryItem;
    }
}
