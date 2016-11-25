package orm

import (
    "github.com/jinzhu/gorm"
    // _ "github.com/jinzhu/gorm/dialects/mysql"
    _ "github.com/jinzhu/gorm/dialects/postgres"
    // _ "github.com/jinzhu/gorm/dialects/sqlite"
    // _ "github.com/jinzhu/gorm/dialects/mssql"
)

func DB() (*orm.DB, error) {
  return gorm.Open("postgres", "host=127.0.0.1 user=postgres dbname=test sslmode=disable password=74898489")
  // return gorm.Open("mysql", "user:password@/dbname?charset=utf8&parseTime=True&loc=Local")
  // return gorm.Open("sqlite3", "/tmp/gorm.db")
}
