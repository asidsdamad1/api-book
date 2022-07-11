package com.example.deploy.dto;

import com.example.deploy.domain.BookCategory;

public class BookCategoryDto extends BaseObjectDto{
    private BookDto book;
    private CategoryDto category;

    public BookCategoryDto(BookCategory entity) { this(entity, true); }
    public BookCategoryDto(BookCategory entity, boolean simple) {
        if(entity != null) {
            this.id = entity.getId();
            if (entity.getBook() != null) {
                this.book = new BookDto(entity.getBook(), false, 1);
            }
            if (entity.getCategory() != null) {
                this.category = new CategoryDto(entity.getCategory(), false, 1);
            }
        }
    }
    public BookDto getBook() {
        return book;
    }

    public void setBook(BookDto book) {
        this.book = book;
    }

    public CategoryDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDto category) {
        this.category = category;
    }
}
