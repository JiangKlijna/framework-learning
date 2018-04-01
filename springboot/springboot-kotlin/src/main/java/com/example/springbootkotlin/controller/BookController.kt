package com.example.springbootkotlin.controller

import com.example.springbootkotlin.bean.Book
import com.example.springbootkotlin.repository.BookRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/book")
class BookController {

    @Autowired
    private val br: BookRepository? = null

    @RequestMapping(value = ["title/{title}"])
    fun find(@PathVariable("title") title: String) =
            br?.findByTitle(title)

    @RequestMapping(value = ["add"])
    fun add(title: String, author: String) =
            br?.save(Book(title, author))

    @RequestMapping(value = ["del"])
    fun del(id: Long) =
            br?.let { it.getOne(id).apply { it.delete(this) } }
}