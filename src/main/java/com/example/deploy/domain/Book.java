package com.example.deploy.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tbl_book")
public class Book extends BaseObject{

    @Column(name = "code")
    private String code;
    @Column(name = "name")
    private String name;
    @Column(name = "author")
    private String author;
    @Column(name = "quantity")
    private Long quantity;
    @Basic(
            fetch = FetchType.LAZY
    )
    @Transient
    protected byte[] photo;
    @Column(
            name = "photo_cropped",
            nullable = true
    )
    private Boolean photoCropped;
    @Column(
            name = "image_path",
            nullable = true
    )
    private String imagePath;
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookCategory> bookCategories;

    @ManyToOne
    @JoinColumn(name = "publisher_id")
    private Publisher publisher;

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

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public Boolean getPhotoCropped() {
        return photoCropped;
    }

    public void setPhotoCropped(Boolean photoCropped) {
        this.photoCropped = photoCropped;
    }

    public List<BookCategory> getBookCategoryItems() {
        return bookCategories;
    }

    public void setBookCategoryItems(List<BookCategory> bookCategories) {
        this.bookCategories = bookCategories;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
