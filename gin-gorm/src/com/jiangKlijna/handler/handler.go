package handler

import (
  "github.com/gin-gonic/gin"
  "net/http"
  "io"
  "log"
  "os"
  "time"
)

struct Handlers{
  Method string
  Path string
  Handler func(*gin.Context)
}

func main()  {

}

func Handler(e *gin.Engine)  {

}
