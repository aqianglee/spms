package com.aqiang.spms.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

public class Conjunction implements Criterion {

	private List<Criterion> criterions;

	public Conjunction() {
		this.criterions = new ArrayList<Criterion>();
	}

	public Conjunction add(Criterion criterion) {
		criterions.add(criterion);
		return this;
	}

	@Override
	public void contributeWhereClause(List<String> whereClause,
			Map<String, Object> paramMap) {
		List<String> subWhereClause = new ArrayList<String>();
		for (Criterion criterion : criterions) {
			criterion.contributeWhereClause(subWhereClause, paramMap);
		}
		if (subWhereClause.size() == 1) {
			whereClause.addAll(subWhereClause);
		}
		if (subWhereClause.size() > 1) {
			whereClause.add("(" + StringUtils.join(subWhereClause, ") AND (")
					+ ")");
		}
	}
}
