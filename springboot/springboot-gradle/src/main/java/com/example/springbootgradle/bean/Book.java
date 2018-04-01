package com.example.springbootgradle.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "book")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@SequenceGenerator(name = "SeqGen1", sequenceName = "book_id_seq")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private String author;

    public Book() {
    }

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    @Id
    @Column(name="id")
    @GeneratedValue(generator="SeqGen1")//写明使用哪个序列生成器
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

}
