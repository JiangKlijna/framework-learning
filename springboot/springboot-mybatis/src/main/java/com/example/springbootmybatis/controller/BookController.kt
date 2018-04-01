package com.example.springbootmybatis.controller

import com.example.springbootmybatis.bean.Book
import com.example.springbootmybatis.dao.IBookDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/book")
class BookController {

    @Autowired
    private val bd: IBookDao? = null

    @RequestMapping(value = ["title/{title}"])
    fun find(@PathVariable("title") title: String) =
            bd?.findByTitle(title)

    @RequestMapping(value = ["add"])
    fun pop(title: String, author: String) =
            Book(title = title, author = author).apply { bd?.save(this) }

    @RequestMapping(value = ["del"])
    fun del(id: Long) =
            bd?.let { it.getOne(id).apply { it.delete(this.id) } }
}