package com.example.springbootgradle.controller;

import com.example.springbootgradle.bean.Book;
import com.example.springbootgradle.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookRepository br;

    @RequestMapping(value = "title/{title}")
    public List<Book> find(@PathVariable("title") String title) {
        return br.findByTitle(title);
    }

    @RequestMapping(value = "add")
    public Book add(String title, String author) {
        return br.save(new Book(title, author));
    }

    @RequestMapping(value = "del")
    public Book del(long id) {
        Book b = br.getOne(id);
        br.delete(b);
        return b;
    }
}