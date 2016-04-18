package com.aqiang.spms;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.After;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:beans-test.xml")
public class BaseTest extends AbstractTransactionalJUnit4SpringContextTests {

	protected Logger logger = LoggerFactory.getLogger(BaseTest.class);

	public void isTrue(boolean result) {
		assertEquals(true, result);
	}

	public void isTrue(Object object) {
		assertEquals(true, object != null);
	}

	@After
	public void destroy() {
		clearDB();
	}

	private void clearDB() {
	}

	public Date date(String dateStr, String format) {
		Date date = null;
		try {
			date = new SimpleDateFormat(format).parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

}
