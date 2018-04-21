package com.jiangKlijna.web;

import com.jiangKlijna.web.generation.tables.records.SsjUserRecord;
import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static com.jiangKlijna.web.generation.Tables.SSJ_USER;
import static org.junit.Assert.assertNotNull;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath*:/jooqConfig.xml"})
public class JooqTest {

	@Autowired
	private ApplicationContext ctx;

	//	@Autowired
//	@Resource(name = "dsl")
	public DSLContext dsl;

	@Before
	public void init() throws Exception {
//		assertNotNull(ctx);
		dsl = getDSLContext();
		assertNotNull(dsl);
	}

	@Test
	public void crud_transaction() throws Exception {
		dsl.transaction(cfg -> {
			DSLContext ctx = DSL.using(cfg);
			crud(ctx);
		});
	}

	@Test
	public void crud(DSLContext ctx) throws Exception {
		ctx.insertInto(SSJ_USER).columns(SSJ_USER.USERNAME, SSJ_USER.PASSWORD).values("159", "357").execute();
		ctx.insertInto(SSJ_USER).columns(SSJ_USER.USERNAME, SSJ_USER.PASSWORD).values("159", "357").execute();
		ctx.insertInto(SSJ_USER).columns(SSJ_USER.USERNAME, SSJ_USER.PASSWORD).values("159", "357").execute();
		dsl.select(SSJ_USER.USERNAME).from(SSJ_USER).fetch()
				.map(record -> record.into(SsjUserRecord.class))
				.forEach(it -> System.out.println(it));
		throw new SQLException();
	}

	private DSLContext getDSLContext() throws Exception {
		Connection connection =
				DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/test", "postgres", "74898489");
		return DSL.using(connection, SQLDialect.POSTGRES);
	}

}
