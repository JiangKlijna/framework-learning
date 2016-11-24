package com.jiangKlijna.web.repository;

/**
 * Created by leil7 on 2016/11/22.
 */
import java.util.List;

import com.jiangKlijna.web.bean.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByReader(String reader);

}