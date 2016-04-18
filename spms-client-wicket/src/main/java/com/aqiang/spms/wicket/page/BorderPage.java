package com.aqiang.spms.wicket.page;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.link.Link;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BorderPage extends WebPage {
	private static final long serialVersionUID = 1L;
	protected Logger LOGGER = LoggerFactory.getLogger(BorderPage.class);

	public BorderPage() {
		
	}

	protected void onPrev() {

	}

	protected Link<Void> createReturnLink() {
		return new Link<Void>("return") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				onPrev();
			}
		};
	}

}
