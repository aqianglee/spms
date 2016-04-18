package com.aqiang.spms.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class DefaultJPADao extends AbstractDao {
	@PersistenceContext
	private EntityManager em;

	public EntityManager getEm() {
		return em;
	}
}
