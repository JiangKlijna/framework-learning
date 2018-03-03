package com.example.springbootmybatis

import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@MapperScan("com.example.springbootmybatis.dao")
@Controller
@SpringBootApplication
open class DemoApplication {

    @GetMapping("/")
    private fun html(map: MutableMap<String, Any>): String {
        map["key"] = "springboot-mybatis"
        return "templates"
    }

    companion object {

        @JvmStatic
        fun main(args: Array<String>) {
            SpringApplication.run(DemoApplication::class.java, *args)
        }
    }
}
