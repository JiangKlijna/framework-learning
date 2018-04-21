package com.jiangKlijna.web.service.impl;

import com.jiangKlijna.web.bean.Result;
import com.jiangKlijna.web.service.BaseService;
import com.jiangKlijna.web.service.UserService;
import org.jooq.DSLContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Map;

import static com.jiangKlijna.web.generation.Tables.SSJ_USER;


@Service("userService")
public class UserServiceImpl extends BaseService implements UserService {

	@Resource(name = "dsl")
	private DSLContext dsl;

	@Override
	@Transactional
	public Result regist(String username, String password) {
		try {
			dsl.insertInto(SSJ_USER)
					.columns(SSJ_USER.USERNAME, SSJ_USER.PASSWORD)
					.values(username, password).execute();

			throw new RuntimeException();
//			return sucessResult();
		} catch (Exception e) {
			return errorResult(e.toString());
		}
	}

	@Override
	public Result remove(int id) {
		try {
			dsl.delete(SSJ_USER).where(SSJ_USER.ID.eq(id)).execute();
			return sucessResult();
		} catch (Exception e) {
			return errorResult(e.toString());
		}
	}

	@Override
	public Result find(int id) {
		try {
//			SsjUserRecord u = dsl.select().from(SSJ_USER).where(SSJ_USER.ID.eq(id)).fetchSingle().into(SsjUserRecord.class);
			Map u = dsl.select().from(SSJ_USER).where(SSJ_USER.ID.eq(id)).fetchSingle().intoMap();
			return sucessResult(u);
		} catch (Exception e) {
			return errorResult(e.toString());
		}
	}

}
