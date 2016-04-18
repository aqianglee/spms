package com.aqiang.spms.wicket;

import com.ttdev.wicketpagetest.WebAppJettyConfiguration;
import com.ttdev.wicketpagetest.WicketAppJettyLauncher;

public class ManualTest {
	public static void main(String[] args) {
		WicketAppJettyLauncher launcher = new WicketAppJettyLauncher();
		WebAppJettyConfiguration cfg = new WebAppJettyConfiguration();
		launcher.startAppInJetty(cfg);
	}
}
