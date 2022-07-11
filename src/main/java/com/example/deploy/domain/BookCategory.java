package com.example.deploy.domain;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_book_category")
public class BookCategory extends BaseObject{
    @ManyToOne
    @JoinColumn(name = "book_id")
    @NotFound(action = NotFoundAction.IGNORE)
    private Book book;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @NotFound(action = NotFoundAction.IGNORE)
    private Category category;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
