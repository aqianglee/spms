package com.aqiang.spms.dao;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.PersistenceContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DefaultUserAction implements UserAction {
	@SuppressWarnings("unused")
	private final static Logger LOGGER = LoggerFactory.getLogger(DefaultUserAction.class);
	@PersistenceContext
	private EntityManager em;

	@Transactional
	@Override
	public void sync(Identifiable entity) {
		boolean autoId = isAutoID(entity);
		boolean freshAutoIdEntity = autoId && entity.getId() == null;
		boolean nonAutoId = !autoId;
		if (freshAutoIdEntity) {
			em.persist(entity);
			return;
		}
		if (nonAutoId) {
			em.merge(entity);
			return;
		}
		em.merge(entity);
	}

	@SuppressWarnings("unchecked")
	private boolean isAutoID(Identifiable entity) {
		Class<? extends Identifiable> entityClass = entity.getClass();
		List<Field> fields = new ArrayList<Field>();
		while (entityClass != null) {
			fields.addAll(Arrays.asList(entityClass.getDeclaredFields()));
			entityClass = (Class<? extends Identifiable>) entityClass.getSuperclass();
		}
		for (Field field : fields) {
			if (field.isAnnotationPresent(GeneratedValue.class)) {
				return true;
			}
		}
		return false;
	}

	@Override
	@Transactional
	public void delete(Identifiable entity) {
		em.remove(em.merge(entity));
	}

	@Override
	@Transactional
	public void exec(String jpql) {
		em.createQuery(jpql).executeUpdate();
	}

	@Override
	@Transactional
	public void update(Identifiable entity) {
		em.merge(entity);
	}
}
