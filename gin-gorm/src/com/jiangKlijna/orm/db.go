package orm

import (
  "fmt"
  "database/sql"
  _ "github.com/bmizerany/pq"
)

func Test()  {
  fmt.Println("db test")
}

func Postgres() (*sql.DB, error) {
  return sql.Open("postgres", "user=postgres dbname=postgres password=74898489 sslmode=verify-full sslmode=disable")
}

func init() {
  db, err := Postgres()
  if err != nil{
    fmt.Println(err.Error())
    return
  }
  row := db.QueryRow("select * from base_user limit 1")
  var username string
  row.Scan(&username)
  fmt.Println(username)
  //db.Exec("truncate table t");

  db.Exec("insert into base_user (select * from base_user order by id ASC limit 5)")

  rows, err := db.Query("select id, password, username from base_user limit 10")
  if err != nil{
    fmt.Println(err.Error())
    return
  }
  for rows.Next() {
    var username string
    var id int
    var password string

    rows.Scan(&id, &username, &password)
    fmt.Println(id, username, password)
  }
  db.Close()

}
