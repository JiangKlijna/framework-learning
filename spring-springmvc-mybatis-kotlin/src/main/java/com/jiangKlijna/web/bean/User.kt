package com.jiangKlijna.web.bean

class User {

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
