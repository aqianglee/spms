package com.aqiang.spms.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;

public abstract class AbstractDao implements GenericDao {

	@Override
	public <T> List<T> list(Class<T> targetClass, Order... orders) {
		return list(targetClass, new ArrayList<Criterion>(), orders);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> list(Class<T> targetClass,
			List<? extends Criterion> criterions, Order... orders) {
		return createSelectQuery(targetClass, criterions, emptyJoins(), orders)
				.getResultList();
	}

	private HashMap<String, List<Criterion>> emptyJoins() {
		return new HashMap<String, List<Criterion>>();
	}

	private <T> Query createSelectQuery(Class<T> targetClass,
			List<? extends Criterion> criterions,
			Map<String, List<Criterion>> joins, Order... orders) {
		StringBuffer buffer = createSelectStatement(targetClass);
		contributeJoins(buffer, joins);
		Map<String, Object> paramMap = contributeWhereClause(buffer, criterions);
		contributeJoinWhereClause(paramMap, buffer, joins);
		contributeOrderByClause(buffer, orders);
		Query query = createQuery(buffer, paramMap);
		return query;
	}

	private void contributeJoins(StringBuffer buffer,
			Map<String, List<Criterion>> joins) {
		Set<String> joinFieldNames = joins.keySet();
		for (String joinFieldName : joinFieldNames) {
			buffer.append(" LEFT JOIN e." + joinFieldName + " " + joinFieldName);
		}

	}

	private Query createQuery(StringBuffer buffer, Map<String, Object> paramMap) {
		Query query = getEm().createQuery(buffer.toString());
		Set<String> paramNames = paramMap.keySet();
		for (String name : paramNames) {
			query.setParameter(name, paramMap.get(name));
		}
		return query;
	}

	public abstract EntityManager getEm();

	private <T> StringBuffer createSelectStatement(Class<T> targetClass) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("select e from " + targetClass.getSimpleName() + " e");
		return buffer;
	}

	private <T> StringBuffer createCountStatement(Class<T> targetClass) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("select count(e) from " + targetClass.getSimpleName()
				+ " e");
		return buffer;
	}

	private <T> StringBuffer createSumStatement(Class<T> targetClass,
			String fieldName) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("select sum(e." + fieldName + ") from "
				+ targetClass.getSimpleName() + " e");
		return buffer;
	}

	private Map<String, Object> contributeWhereClause(StringBuffer buffer,
			List<? extends Criterion> criterions) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		List<String> whereClause = new ArrayList<String>();
		for (Criterion criterion : criterions) {
			criterion.contributeWhereClause(whereClause, paramMap);
		}
		if (!whereClause.isEmpty()) {
			buffer.append(" WHERE (" + StringUtils.join(whereClause, ") AND (")
					+ ")");
		}
		return paramMap;
	}

	private void contributeJoinWhereClause(Map<String, Object> paramMap,
			StringBuffer buffer, Map<String, List<Criterion>> joins) {
		List<String> whereClause = new ArrayList<String>();
		for (String joinField : joins.keySet()) {
			List<Criterion> criterions = joins.get(joinField);
			for (Criterion criterion : criterions) {
				criterion.contributeWhereClause(whereClause, paramMap);
			}
		}
		if (!whereClause.isEmpty()) {
			String prefix = buffer.toString().contains("WHERE") ? "AND"
					: "WHERE";
			buffer.append(" " + prefix + " ("
					+ StringUtils.join(whereClause, ") AND (") + ")");

		}
	}

	private void contributeOrderByClause(StringBuffer buffer, Order... orders) {
		List<String> orderBy = new ArrayList<String>();
		for (Order order : orders) {
			order.contribute(orderBy);
		}

		if (!orderBy.isEmpty()) {
			buffer.append(" ORDER BY "
					+ StringUtils.join(orderBy.toArray(), ","));
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> list(Class<T> targetClass,
			List<? extends Criterion> criterions, List<Order> orders,
			long first, long count) {
		Query query = createSelectQuery(targetClass, criterions, emptyJoins(),
				orders.toArray(new Order[] {}));
		query.setFirstResult(new Long(first).intValue());
		query.setMaxResults(new Long(count).intValue());
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> list(Class<T> targetClass,
			List<? extends Criterion> criterions, List<Order> orders,
			Map<String, List<Criterion>> joins, long first, long count) {
		Query query = createSelectQuery(targetClass, criterions, joins,
				orders.toArray(new Order[] {}));
		query.setFirstResult(new Long(first).intValue());
		query.setMaxResults(new Long(count).intValue());
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> int count(Class<T> targetClass,
			List<? extends Criterion> criterions) {
		StringBuffer buffer = createCountStatement(targetClass);
		Map<String, Object> paramMap = contributeWhereClause(buffer, criterions);
		List<Number> numbers = (List<Number>) createQuery(buffer, paramMap)
				.getResultList();
		int total = 0;
		for (Number number : numbers) {
			total += number.intValue();
		}
		return total;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> int count(Class<T> targetClass,
			List<? extends Criterion> criterions,
			Map<String, List<Criterion>> joins) {
		StringBuffer buffer = createCountStatement(targetClass);
		contributeJoins(buffer, joins);
		Map<String, Object> paramMap = contributeWhereClause(buffer, criterions);
		contributeJoinWhereClause(paramMap, buffer, joins);
		List<Number> numbers = (List<Number>) createQuery(buffer, paramMap)
				.getResultList();
		int total = 0;
		for (Number number : numbers) {
			total += number.intValue();
		}
		return total;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> double sum(Class<T> targetClass, String fieldName,
			List<Criterion> criterions) {
		StringBuffer buffer = createSumStatement(targetClass, fieldName);
		Map<String, Object> paramMap = contributeWhereClause(buffer, criterions);
		List<Number> numbers = (List<Number>) createQuery(buffer, paramMap)
				.getResultList();
		double total = 0;
		for (Number number : numbers) {
			total += (number == null ? 0.0 : number.doubleValue());
		}
		return total;
	}

	@Override
	public <T> T create(Class<T> entityClass) {
		try {
			T entity = entityClass.newInstance();
			getEm().persist(entity);
			return entity;
		} catch (Throwable e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
