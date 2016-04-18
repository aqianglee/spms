package com.aqiang.spms.wicket;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;

import com.aqiang.spms.wicket.page.LoginPage;
import com.ttdev.wicketpagetest.MockableSpringBeanInjector;

public class MyAppForTest extends WebApplication {

	@Override
	public Class<? extends Page> getHomePage() {
		return LoginPage.class;
	}

	@Override
	protected void init() {
		super.init();
		MockableSpringBeanInjector.installInjector(this);
		getMarkupSettings().setDefaultMarkupEncoding("utf-8");
	}

}
