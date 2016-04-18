package com.aqiang.spms.wicket;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.aqiang.spms.wicket.page.ManageProjectTest;
import com.ttdev.wicketpagetest.Configuration;
import com.ttdev.wicketpagetest.WebPageTestContext;

@RunWith(Suite.class)
@SuiteClasses({
	ManageProjectTest.class
})
public class TestSuite {
	@BeforeClass
	static public void setUp() throws Exception {
		Configuration cfg = new Configuration();
		cfg.setOverrideWebXml("web-test.xml");
		WebPageTestContext.beforePageTests(cfg);
	}

	@AfterClass
	static public void tearDown() throws Exception {
		WebPageTestContext.afterPageTests();
	}
}
