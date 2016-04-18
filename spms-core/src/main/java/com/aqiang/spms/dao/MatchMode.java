package com.aqiang.spms.dao;

public abstract class MatchMode {
	protected abstract String toMatchStringPattern(String value);

	public static MatchMode EXACT = new MatchMode() {
		@Override
		protected String toMatchStringPattern(String value) {
			return value;
		}
	};
	public static MatchMode START = new MatchMode() {
		@Override
		protected String toMatchStringPattern(String value) {
			return value + "%";
		}
	};
	public static MatchMode END = new MatchMode() {
		@Override
		protected String toMatchStringPattern(String value) {
			return "%" + value;
		}
	};
	public static MatchMode ANYWHERE = new MatchMode() {
		@Override
		protected String toMatchStringPattern(String value) {
			return "%" + value + "%";
		}
	};
}
