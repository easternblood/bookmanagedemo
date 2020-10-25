package com.book.eneity;

import java.util.Arrays;

public class Book {
    private Integer id;
    private String name;
    private String author;
    private String publish;
    private Integer pages;
    private Double price;
    private Integer bookcaseid;
    private String kindname;
    private byte[] photoimg;
    private String bookimageurl;

    public Book(Integer id, String name, String author, String publish, Integer pages, Double price, Integer bookcaseid, String kindname, byte[] photoimg, String bookimageurl) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.publish = publish;
        this.pages = pages;
        this.price = price;
        this.bookcaseid = bookcaseid;
        this.kindname = kindname;
        this.photoimg = photoimg;
        this.bookimageurl = bookimageurl;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", publish='" + publish + '\'' +
                ", pages=" + pages +
                ", price=" + price +
                ", bookcaseid=" + bookcaseid +
                ", kindname='" + kindname + '\'' +
                ", photoimg=" + Arrays.toString(photoimg) +
                ", bookimageurl='" + bookimageurl + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getBookcaseid() {
        return bookcaseid;
    }

    public void setBookcaseid(Integer bookcaseid) {
        this.bookcaseid = bookcaseid;
    }

    public String getKindname() {
        return kindname;
    }

    public void setKindname(String kindname) {
        this.kindname = kindname;
    }

    public byte[] getPhotoimg() {
        return photoimg;
    }

    public void setPhotoimg(byte[] photoimg) {
        this.photoimg = photoimg;
    }

    public String getBookimageurl() {
        return bookimageurl;
    }

    public void setBookimageurl(String bookimageurl) {
        this.bookimageurl = bookimageurl;
    }
}
