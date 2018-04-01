package com.jiangKlijna.web.control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.ModelAttribute;

import com.jiangKlijna.web.app.ContextWrapper;
import com.jiangKlijna.web.bean.Result;

public class BaseControl extends ContextWrapper {

	protected static final Result errorParameterResult = new Result(1, "invalid parameter", "");

	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected HttpSession session;

	@ModelAttribute
	public void setReqAndRes(HttpServletRequest request, HttpServletResponse response) {
		this.request = request;
		this.response = response;
		this.session = request.getSession();
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
		for (String s : strs) if (s == null || s.isEmpty()) return false;
		return true;
	}
}
