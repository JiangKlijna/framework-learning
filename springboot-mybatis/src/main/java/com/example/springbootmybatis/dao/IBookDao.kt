package com.example.springbootmybatis.dao

import com.example.springbootmybatis.bean.Book

/**
 * Created by leil7 on 2016/11/22.
 */
interface IBookDao {
    fun findByTitle(title: String): List<Book>

    fun save(book: Book)

    fun getOne(id: Long): Book

    fun delete(id: Long)
}