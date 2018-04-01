#'数据库类型+数据库驱动名称://用户名:口令@机器地址:端口号/数据库名'

DB = 'postgresql'
DB_DRIVE = 'psycopg2'
DB_USER = 'postgres'
DB_PWD = '74898489'
DB_HOST = '127.0.0.1'
DB_PORT = '5432'
DB_NAME = 'test'
DB_CONNECT_STRING = '%s+%s://%s:%s@%s:%s/%s' % (DB, DB_DRIVE, DB_USER, DB_PWD, DB_HOST, DB_PORT, DB_NAME)

from sqlalchemy.orm import sessionmaker
from sqlalchemy import create_engine
from model import Base

print(DB_CONNECT_STRING)

# init database connection
engine = create_engine(DB_CONNECT_STRING, encoding='utf-8', echo=False, pool_size=100, pool_recycle=10)
Base.metadata.drop_all(engine)
Base.metadata.create_all(engine)
Session = sessionmaker(bind=engine)
