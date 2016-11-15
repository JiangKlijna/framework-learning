package com.jiangKlijna.web.service;

import com.jiangKlijna.web.app.ContextWrapper;
import com.jiangKlijna.web.bean.Result;
import com.jiangKlijna.web.dao.IDao;

public class BaseService extends ContextWrapper {

	protected final IDao dao() {
		return getApplicationContext().getBean("dao", IDao.class);
	}

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
