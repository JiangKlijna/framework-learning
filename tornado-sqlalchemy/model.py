
from sqlalchemy import *
from sqlalchemy.ext.declarative import declarative_base

Base = declarative_base()

class User(Base):
    __tablename__ = 'ts_user'

    id = Column(Integer, primary_key=True, autoincrement=True)
    username = Column(String(20))
    password = Column(String(20))
