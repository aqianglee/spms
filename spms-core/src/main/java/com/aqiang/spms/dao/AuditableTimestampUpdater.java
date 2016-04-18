package com.aqiang.spms.dao;

import java.util.Date;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuditableTimestampUpdater {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(AuditableTimestampUpdater.class);

	@PrePersist
	public void onPrePersist(Object entity) {
		if (entity instanceof Auditable) {
			Auditable auditable = (Auditable) entity;
			auditable.setCreateTime(new Date());
			LOGGER.debug("Set CreateTime = {}", auditable.getCreateTime());
		}
	}

	@PreUpdate
	public void onPreUpdate(Object entity) {
		if (entity instanceof Auditable) {
			Auditable auditable = (Auditable) entity;
			auditable.setUpdateTime(new Date());
			LOGGER.debug("Set UpdateTime = {}", auditable.getUpdateTime());
		}
	}
}
