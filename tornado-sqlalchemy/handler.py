
import tornado.web
import tornado.httpclient

from model import User


class BaseHandler(tornado.web.RequestHandler):
    """BaseHandler."""
    def initialize(self):
        pass

    @property
    def db(self):
        if not hasattr(self, 'session'):
            self.session = self.application.Session()
        return self.session

    def on_finish(self):
        if hasattr(self, 'session'):
            self.session.commit()
            self.session.close()


class IndexHandler(BaseHandler):
    Path = r'/'

    def get(self):
        self.render('index.html')

class StatusHandler(BaseHandler):
    Path = r"/status/(\d+)"

    def get(self, input):
        self.write(input)
        self.set_status(int(input))

class ArgHandler(BaseHandler):
    Path = r"/arg"

    def get(self):
        self.post()

    def post(self):
        color = self.get_argument('color', "cce8cf")
        self.render('arg.html', color=color)

class AsyncHandler(BaseHandler):
    Path = r"/async"

    @tornado.web.asynchronous
    def get(self):
        client = tornado.httpclient.AsyncHTTPClient()
        client.fetch("http://libs.baidu.com/jquery/1.10.2/jquery.min.js", callback=self.on_response)

    def on_response(self, res):
        self.write(res.body)
        self.finish()

class CookieHandler(BaseHandler):
    Path = r"/cookie"

    def get(self):
        count = self.get_secure_cookie("count")
        count = 1 if not count else int(count) + 1
        self.set_secure_cookie("count", str(count))
        self.write(str(count))

class DbHandler(BaseHandler):
    Path = r"/api/db"

    def get(self):
        for v in range(20):
            self.db.add(User(username='username%s' % (v,), password='password%s' % (v,)))
        q = self.db.query(User)
        users = q.order_by('username desc').limit(8).all()
        for u in users:
            self.write("id=%s\tusername=%s\tpassword=%s" % (u.id, u.username, u.password))
            if u.id % 5 is 0:
                self.db.delete(u)

handlers = [
    (IndexHandler.Path, IndexHandler),
    (StatusHandler.Path, StatusHandler),
    (ArgHandler.Path, ArgHandler),
    (AsyncHandler.Path, AsyncHandler),
    (CookieHandler.Path, CookieHandler),
    (DbHandler.Path, DbHandler)
]
