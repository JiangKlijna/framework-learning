package com.jiangKlijna.web.bean

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "ssh_user")
class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0
    var username: String? = null
    var password: String? = null

    constructor() {

    }

    constructor(id: Int, username: String, password: String) {
        this.id = id
        this.username = username
        this.password = password
    }

    constructor(username: String, password: String) {
        this.username = username
        this.password = password
    }

    override fun toString(): String {
        return "User [username=" + username + ", password=" + password + ", hashcode=" + hashCode() + "]"
    }

}
