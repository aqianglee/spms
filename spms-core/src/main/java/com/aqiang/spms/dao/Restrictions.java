package com.aqiang.spms.dao;

import java.util.List;
import java.util.Map;

public class Restrictions {

	public static Criterion memberOf(Object entity, final String collectionFieldName) {
		return new AbstractCriterion(collectionFieldName, entity) {
			@Override
			public void contributeWhereClause(List<String> whereClause, Map<String, Object> paramMap) {
				String paramName = getParamName(paramMap);
				whereClause.add(":" + paramName + " MEMBER OF e." + getFieldName());
			}
		};
	}

	public static Criterion isEmpty(final String collectionFieldName) {
		return new Criterion() {

			@Override
			public void contributeWhereClause(List<String> whereClause, Map<String, Object> paramMap) {
				whereClause.add(String.format("e.%s IS EMPTY", collectionFieldName));
			}
		};
	}

	public static Criterion isNull(final String fieldName) {
		return new Criterion() {

			@Override
			public void contributeWhereClause(List<String> whereClause, Map<String, Object> paramMap) {
				whereClause.add(String.format("e.%s IS NULL", fieldName));
			}
		};
	}

	public static Criterion isNotNull(final String fieldName) {
		return new Criterion() {

			@Override
			public void contributeWhereClause(List<String> whereClause, Map<String, Object> paramMap) {
				whereClause.add(String.format("e.%s IS NOT NULL", fieldName));
			}
		};
	}

	public static Criterion isNotEmpty(final String collectionFieldName) {
		return new Criterion() {

			@Override
			public void contributeWhereClause(List<String> whereClause, Map<String, Object> paramMap) {
				whereClause.add(String.format("e.%s IS NOT EMPTY", collectionFieldName));
			}
		};
	}

	public static Criterion eq(String fieldName, Object obj) {
		return new AbstractCriterion(fieldName, obj) {
			@Override
			public void contributeWhereClause(List<String> whereClause, Map<String, Object> paramMap) {
				String paramName = getParamName(paramMap);
				whereClause.add("e." + getFieldName() + " = :" + paramName);
			}
		};
	}

	public static Criterion ne(String fieldName, Object obj) {
		return new AbstractCriterion(fieldName, obj) {
			@Override
			public void contributeWhereClause(List<String> whereClause, Map<String, Object> paramMap) {
				String paramName = getParamName(paramMap);
				whereClause.add("e." + getFieldName() + " <> :" + paramName);
			}
		};
	}

	public static Criterion eq(String fieldName, Object obj, final boolean fieldsInCollection) {
		return new AbstractCriterion(fieldName, obj) {
			@Override
			public void contributeWhereClause(List<String> whereClause, Map<String, Object> paramMap) {
				String paramName = getParamName(paramMap);
				whereClause.add((fieldsInCollection ? "" : "e.") + getFieldName() + " = :" + paramName);
			}
		};
	}

	public static Criterion ne(String fieldName, Object obj, final boolean fieldsInCollection) {
		return new AbstractCriterion(fieldName, obj) {
			@Override
			public void contributeWhereClause(List<String> whereClause, Map<String, Object> paramMap) {
				String paramName = getParamName(paramMap);
				whereClause.add((fieldsInCollection ? "" : "e.") + getFieldName() + " <> :" + paramName);
			}
		};
	}

	public static Criterion ge(String fieldName, Object obj) {
		return new AbstractCriterion(fieldName, obj) {
			@Override
			public void contributeWhereClause(List<String> whereClause, Map<String, Object> paramMap) {
				String paramName = getParamName(paramMap);
				whereClause.add("e." + getFieldName() + " >= :" + paramName);
			}
		};
	}

	public static Criterion ge(String fieldName, Object obj, final String fieldPrefix) {
		return new AbstractCriterion(fieldName, obj) {
			@Override
			public void contributeWhereClause(List<String> whereClause, Map<String, Object> paramMap) {
				String paramName = getParamName(paramMap);
				whereClause.add(fieldPrefix + getFieldName() + " >= :" + paramName);
			}
		};
	}

	public static Criterion le(String fieldName, Object obj) {
		return new AbstractCriterion(fieldName, obj) {
			@Override
			public void contributeWhereClause(List<String> whereClause, Map<String, Object> paramMap) {
				String paramName = getParamName(paramMap);
				whereClause.add("e." + getFieldName() + " <= :" + paramName);
			}
		};
	}

	public static Criterion gt(String fieldName, Object obj) {
		return new AbstractCriterion(fieldName, obj) {
			@Override
			public void contributeWhereClause(List<String> whereClause, Map<String, Object> paramMap) {
				String paramName = getParamName(paramMap);
				whereClause.add("e." + getFieldName() + " > :" + paramName);
			}
		};
	}

	public static Criterion lt(String fieldName, Object obj) {
		return new AbstractCriterion(fieldName, obj) {
			@Override
			public void contributeWhereClause(List<String> whereClause, Map<String, Object> paramMap) {
				String paramName = getParamName(paramMap);
				whereClause.add("e." + getFieldName() + " < :" + paramName);
			}
		};
	}

	public static Criterion lt(String fieldName, Object obj, final String fieldNamePrefix) {
		return new AbstractCriterion(fieldName, obj) {
			@Override
			public void contributeWhereClause(List<String> whereClause, Map<String, Object> paramMap) {
				String paramName = getParamName(paramMap);
				whereClause.add(fieldNamePrefix + getFieldName() + " < :" + paramName);
			}
		};
	}

	// NOTE: case-insensitive like
	public static Criterion ilike(String fieldName, String value, MatchMode mode, final boolean collectionField) {
		return new AbstractCriterion(fieldName, mode.toMatchStringPattern(value.toLowerCase())) {
			@Override
			public void contributeWhereClause(List<String> whereClause, Map<String, Object> paramMap) {
				String paramName = getParamName(paramMap);
				String fullFieldName = (collectionField ? "" : "e.") + getFieldName();
				whereClause.add(String.format("LOWER(%s) LIKE :%s", fullFieldName, paramName));
			}
		};
	}

	public static Criterion like(String fieldName, String value, MatchMode mode) {
		return new AbstractCriterion(fieldName, mode.toMatchStringPattern(value)) {
			@Override
			public void contributeWhereClause(List<String> whereClause, Map<String, Object> paramMap) {
				String paramName = getParamName(paramMap);
				whereClause.add("e." + getFieldName() + " LIKE :" + paramName);
			}
		};
	}

	public static Criterion like(String fieldName, String value) {
		return like(fieldName, value, MatchMode.ANYWHERE);
	}

	public static Criterion ilike(String fieldName, String value) {
		return ilike(fieldName, value, MatchMode.ANYWHERE, false);
	}

	public static Disjunction or() {
		return new Disjunction();
	}

	public static Conjunction and() {
		return new Conjunction();
	}
}
