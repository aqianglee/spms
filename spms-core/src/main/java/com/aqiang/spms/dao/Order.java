package com.aqiang.spms.dao;

import java.util.List;

public abstract class Order {

	public static Order desc(final String fieldName) {
		return new Order() {
			public void contribute(List<String> orderBy) {
				orderBy.add(fieldName + " DESC");
			}
		};
	}

	public static Order asc(final String fieldName) {
		return new Order() {
			@Override
			public void contribute(List<String> orderBy) {
				orderBy.add(fieldName + " ASC");
			}
		};
	}

	public abstract void contribute(List<String> orderBy);
}
