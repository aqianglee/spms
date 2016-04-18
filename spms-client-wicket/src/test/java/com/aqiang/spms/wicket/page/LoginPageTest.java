package com.aqiang.spms.wicket.page;

import org.junit.Test;

import com.aqiang.spms.wicket.PageTest;
import com.ttdev.wicketpagetest.WebPageTestContext;
import com.ttdev.wicketpagetest.WicketSeleniumDriver;

public class LoginPageTest extends PageTest {

	@Test
	public void testDisplayCurrentInfo() {
		WicketSeleniumDriver ws = WebPageTestContext.getWicketSelenium();
		ws.openNonBookmarkablePage(LoginPage.class);
		isTrue(ws.isElementPresent("//login"));
	}
}
