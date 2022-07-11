package com.example.deploy.dto;

import com.example.deploy.domain.Book;
import com.example.deploy.domain.BookCategory;

import java.util.ArrayList;
import java.util.List;

public class BookDto extends BaseObjectDto{
    private String code;
    private String name;
    private String author;
    private Long quantity;
    private boolean hasPhoto;
    private List<BookCategoryDto> bookCategoryItems ;
    private PublisherDto publisher;

    public BookDto() { }
    public BookDto(Book entity) {
        this(entity, true);
    }

    public BookDto(Book entity, boolean simple) {
        super();
        if(entity != null) {
            this.id = entity.getId();
            this.code = entity.getCode();
            this.name = entity.getName();
            this.author = entity.getAuthor();
            this.quantity = entity.getQuantity();
            this.hasPhoto = entity.getImagePath() != null && entity.getImagePath().length() > 0;
            if(entity.getPublisher() != null) {
                this.publisher = new PublisherDto(entity.getPublisher(), false, 1);
            }
            if(entity.getBookCategoryItems()!=null && entity.getBookCategoryItems().size()>0) {
                this.bookCategoryItems = new ArrayList<>();
                for(BookCategory item : entity.getBookCategoryItems()) {
                    this.bookCategoryItems.add(new BookCategoryDto(item));
                }
            }
        }
    }

    public BookDto(Book entity, boolean simple, int type) {
        if(entity != null) {
            this.id = entity.getId();
            this.code = entity.getCode();
            this.name = entity.getName();
            this.author = entity.getAuthor();
            this.quantity = entity.getQuantity();
            this.hasPhoto = entity.getImagePath() != null && entity.getImagePath().length() > 0;
            if(entity.getPublisher() != null) {
                this.publisher = new PublisherDto(entity.getPublisher(), false, 1);
            }
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public boolean isHasPhoto() {
        return hasPhoto;
    }

    public void setHasPhoto(boolean hasPhoto) {
        this.hasPhoto = hasPhoto;
    }

    public List<BookCategoryDto> getBookCategoryItems() {
        return bookCategoryItems;
    }

    public void setBookCategoryItems(List<BookCategoryDto> bookCategoryItems) {
        this.bookCategoryItems = bookCategoryItems;
    }

    public PublisherDto getPublisher() {
        return publisher;
    }

    public void setPublisher(PublisherDto publisher) {
        this.publisher = publisher;
    }
}
