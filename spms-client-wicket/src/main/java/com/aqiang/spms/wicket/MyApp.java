package com.aqiang.spms.wicket;

import org.apache.wicket.Page;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;

import com.aqiang.spms.wicket.page.ManageProject;

public class MyApp extends WebApplication {

	@Override
	public Class<? extends Page> getHomePage() {
		return ManageProject.class;
	}
	
	@Override
	protected void init() {
		super.init();
		getComponentInstantiationListeners().add(new SpringComponentInjector(this));
		getMarkupSettings().setDefaultMarkupEncoding("utf-8");
	}

}
