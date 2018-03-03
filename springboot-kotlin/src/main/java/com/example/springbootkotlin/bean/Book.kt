package com.example.springbootkotlin.bean

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

import javax.persistence.*

@Entity
@Table(name = "book")
@JsonIgnoreProperties("hibernateLazyInitializer", "handler")
@SequenceGenerator(name = "SeqGen1", sequenceName = "book_id_seq")
class Book(var title: String? = null, var author: String? = null) {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @get:Id
    @get:Column(name = "id")
    @get:GeneratedValue(generator = "SeqGen1")//写明使用哪个序列生成器
    var id: Long? = null

}
