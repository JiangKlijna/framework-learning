package main

import (
  "github.com/gin-gonic/gin"
  "net/http"
  "io"
  "log"
  "os"
  "time"
  "com/jiangKlijna/orm"
)

type Bean struct{
  id int
  format string
  name string
}

func Logger() gin.HandlerFunc {
    return func(c *gin.Context) {
        t := time.Now()

        // Set example variable
        c.Set("example", "12345")

        // before request

        c.Next()

        // after request
        latency := time.Since(t)
        log.Print(latency)

        // access the status we are sending
        status := c.Writer.Status()
        log.Println(status)
    }
}

var Endpoint = func(c *gin.Context) {

  c.String(http.StatusOK, "Endpoint")
}

func main() {
    orm.Test()
    var r *gin.Engine = gin.Default()
    r.Use(Logger())

    //r.Static("/static", "./static")
    r.StaticFS("/static", http.Dir("static"))
    r.StaticFile("/", "./static/index.html")
    r.StaticFile("/favicon.ico", "./static/img/favicon.ico")
    r.LoadHTMLGlob("templates/*")

    r.GET("/data/:name", func(c *gin.Context) {
        name := c.Param("name")
        format := c.DefaultQuery("format", "html")
        switch format {
        case "string"://text/plain
          c.String(http.StatusOK, "name = " + name + "\tformat = " + format)
        case "json"://application/json
          c.JSON(http.StatusOK, gin.H{"id":1, "format":format, "name":name})
        case "xml"://application/xml
          c.XML(http.StatusOK, gin.H{"id":1, "format":format, "name":name})
        case "yaml"://application/x-yaml
          c.YAML(http.StatusOK, gin.H{"id":1, "format":format, "name":name})
        case "html"://text/html
          c.HTML(http.StatusOK, "bean.tmpl", gin.H{"id":1, "format":format, "name":name})
        case "redirect"://重定向
          c.Redirect(http.StatusMovedPermanently, "http://baidu.com/s?wd=" + name)
        }
    })

    r.POST("/upload", func(c *gin.Context) {
        file, header , err := c.Request.FormFile("upload")
        filename := header.Filename
        log.Println(header.Filename)
        out, err := os.Create("./tmp/"+filename+".png")
        if err != nil {
            log.Fatal(err)
        }
        defer out.Close()
        _, err = io.Copy(out, file)
        if err != nil {
            log.Fatal(err)
        }
    })

    r.POST("/post", func(c *gin.Context) {
        id := c.Query("id")
        page := c.DefaultQuery("page", "0")
        name := c.PostForm("name")
        message := c.PostForm("message")

        c.String(http.StatusOK, "id: ; page: ; name: ; message: "+ id +page+ name+ message)
    })

    v1 := r.Group("/v1")
    {
      v1.POST("/login", Endpoint)
      v1.GET("/submit", Endpoint)
    }

    r.Run(":3000")
}

//func main() {
//    router := gin.Default()
//    s := &http.Server{
//        Addr:           ":8080",
//        Handler:        router,
//        ReadTimeout:    10 * time.Second,
//        WriteTimeout:   10 * time.Second,
//        MaxHeaderBytes: 1 << 20,
//    }
//    s.ListenAndServe()
//}
