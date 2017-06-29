package com.jiangKlijna.web.dao

import com.jiangKlijna.web.bean.User
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Created by leil7 on 2017/6/19. spring-springmvc-springdatajpa
 */
interface UserDao : JpaRepository<User, Int>
