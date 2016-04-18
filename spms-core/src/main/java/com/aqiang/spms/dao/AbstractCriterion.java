package com.aqiang.spms.dao;

import java.util.Map;

public abstract class AbstractCriterion implements Criterion {
	private String fieldName;
	private Object parameter;

	public AbstractCriterion(String fieldName, Object parameter) {
		this.fieldName = fieldName;
		this.parameter = parameter;
	}

	public String getFieldName() {
		return fieldName;
	}

	public Object getParameter() {
		return parameter;
	}

	protected String getParamName(Map<String, Object> paramMap) {
		String name = "p" + paramMap.size();
		paramMap.put(name, parameter);
		return name;
	}
}
