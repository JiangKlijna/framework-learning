package com.example.springbootkotlin.repository

/**
 * Created by leil7 on 2016/11/22.
 */

import com.example.springbootkotlin.bean.Book
import org.springframework.data.jpa.repository.JpaRepository

interface BookRepository : JpaRepository<Book, Long> {

    fun findByTitle(reader: String): List<Book>

}