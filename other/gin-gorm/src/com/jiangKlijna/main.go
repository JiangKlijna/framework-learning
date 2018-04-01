package main

import (
  "net/http"
  "com/jiangKlijna/handler"
  "github.com/gin-gonic/gin"
)

func main() {
    var r *gin.Engine = gin.Default()
    //r.Static("/static", "./static")
    r.StaticFS("/static", http.Dir("static"))
    r.StaticFile("/", "./static/index.html")
    r.StaticFile("/favicon.ico", "./static/img/favicon.ico")
    r.LoadHTMLGlob("templates/*")
    handler.Handler(r)
    r.Run(":3000")
}
