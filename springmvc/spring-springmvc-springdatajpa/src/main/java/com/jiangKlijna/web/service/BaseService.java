package com.jiangKlijna.web.service;

import com.jiangKlijna.web.app.ContextWrapper;
import com.jiangKlijna.web.bean.Result;

public class BaseService extends ContextWrapper {

	protected Result sucessResult() {
		return getApplicationContext().getBean("sucessResult", Result.class);
	}

	protected Result sucessResult(Object data) {
		return sucessResult().setData(data);
	}

	protected Result errorResult(String message) {
		return getApplicationContext().getBean("errorResult", Result.class).setMessage(message);
	}
}
