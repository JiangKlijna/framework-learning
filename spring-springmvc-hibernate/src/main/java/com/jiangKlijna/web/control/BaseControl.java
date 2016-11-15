package com.jiangKlijna.web.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ModelAttribute;

import com.alibaba.fastjson.JSON;
import com.jiangKlijna.web.app.ContextWrapper;
import com.jiangKlijna.web.bean.Result;
import com.thoughtworks.xstream.XStream;

public class BaseControl extends ContextWrapper {

	protected static final Result errorParameterResult = new Result(1, "invalid parameter", "");
	protected static final XStream xs = new XStream();

	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected HttpSession session;

	@ModelAttribute
	public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.session = request.getSession();
	}

	protected final void json(Result re) {
		response("application/json;charset=utf-8", JSON.toJSONString(re));
	}

	protected final void xml(Result re) {
		response("application/xml;charset=utf-8", xs.toXML(re));
	}

	protected final void text(String re) {
		response("text/plain;charset=utf-8", re);
	}

	protected final void html(String re) {
		response("text/html;charset=utf-8", re);
	}

	protected final void response(String contentType, String content) {
		try {
			response.setContentType(contentType);
			PrintWriter w = response.getWriter();
			w.print(content);
			w.flush();
			w.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected static boolean testParameter(String... strs) {
		for (String s : strs) {
			if (s == null || s.isEmpty()) {
				return false;
			}
		}
		return true;
	}
}
