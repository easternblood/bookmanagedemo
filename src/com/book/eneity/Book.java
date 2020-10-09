package com.book.eneity;

public class Book {
    private Integer id;
    private String name;
    private String author;
    private String publish;
    private Integer pages;
    private Double price;
    private Integer bookcaseid;
    private String kindname;

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
                '}';
    }

    public Book(Integer id, String name, String author, String publish, Integer pages, Double price, Integer bookcaseid, String kindname) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.publish = publish;
        this.pages = pages;
        this.price = price;
        this.bookcaseid = bookcaseid;
        this.kindname = kindname;
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
}
