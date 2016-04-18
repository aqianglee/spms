package com.aqiang.spms.wicket.page;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebPage;

public class LoginPage extends WebPage {

	private static final long serialVersionUID = 1L;

	public LoginPage() {
		add(new AjaxLink<Void>("login") {

			private static final long serialVersionUID = 1L;

			@Override
			public void onClick(AjaxRequestTarget target) {
				setResponsePage(Desktop.class);
			}
		});
	}

}
