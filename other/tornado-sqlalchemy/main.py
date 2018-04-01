
# http://demo.pythoner.com/itt2zh/

import tornado.ioloop
import tornado.web
import os

from handler import handlers
from orm import Session

class App(tornado.web.Application):
    """Application."""
    def __init__(self):
        settings = dict(
            debug=True,
            handlers=handlers,
            template_path=os.path.join(os.path.dirname(__file__), "templates"),
            static_path=os.path.join(os.path.dirname(__file__), "static"),
            cookie_secret="bZJc2sWbQLKos6GkHn/VB9oXwQt8S0R0kRvJ5/xJ89E="
        )
        tornado.web.Application.__init__(self, **settings)

    def Session(self):
        return Session()

if __name__ == "__main__":
    App().listen(5000, address='0.0.0.0')
    tornado.ioloop.IOLoop.instance().start()
