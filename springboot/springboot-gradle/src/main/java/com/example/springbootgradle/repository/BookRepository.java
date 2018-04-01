package com.example.springbootgradle.repository;

/**
 * Created by leil7 on 2016/11/22.
 */

import java.util.List;

import com.example.springbootgradle.bean.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByTitle(String reader);

}