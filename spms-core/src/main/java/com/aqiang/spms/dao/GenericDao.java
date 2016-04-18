package com.aqiang.spms.dao;

import java.util.List;
import java.util.Map;

public interface GenericDao {
	public <T> List<T> list(Class<T> targetClass, Order... orders);

	public <T> List<T> list(Class<T> targetClass,
			List<? extends Criterion> criterions, Order... orders);

	public <T> int count(Class<T> targetClass,
			List<? extends Criterion> criterions);

	public <T> int count(Class<T> targetClass,
			List<? extends Criterion> criterions,
			Map<String, List<Criterion>> joins);

	public <T> double sum(Class<T> targetClass, String fieldName,
			List<Criterion> criterions);

	public <T> List<T> list(Class<T> targetClass,
			List<? extends Criterion> criterions, List<Order> orders,
			long first, long count);

	public <T> List<T> list(Class<T> targetClass,
			List<? extends Criterion> criterions, List<Order> orders,
			Map<String, List<Criterion>> joins, long first, long count);

	public <T> T create(Class<T> entityClass);
}
