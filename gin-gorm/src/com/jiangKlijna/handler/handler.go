package handler

import (
  "github.com/gin-gonic/gin"
  "com/jiangKlijna/orm"
  "net/http"
  "io"
  "log"
  "os"
  "time"
)

var logger = func(c *gin.Context) {
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

var dataHandler = func(c *gin.Context) {
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
}

var uploadHandler = func(c *gin.Context) {
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
}

var argHandler = func(c *gin.Context) {
    id := c.Query("id")
    page := c.DefaultQuery("page", "0")
    name := c.PostForm("name")
    message := c.PostForm("message")

    c.String(http.StatusOK, "id: ; page: ; name: ; message: "+ id +page+ name+ message)
}

var addHandler = func(c *gin.Context) {
  db, err = orm.DB()
  if err != nil {
      log.Fatal(err)
  }
  db.Close()
  c.String(http.StatusOK, "addHandler")
}

var delHandler = func(c *gin.Context) {
  c.String(http.StatusOK, "delHandler")
}

func Handler(e *gin.Engine)  {
  e.Use(logger)
  e.GET("/data/:name", dataHandler)
  e.POST("/upload", uploadHandler)
  e.POST("/arg", argHandler)
  v1 := e.Group("/db")
  {
    v1.GET("/del", delHandler)
    v1.GET("/add", addHandler)
  }
}
