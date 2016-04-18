package com.aqiang.spms.dao;

public interface UserAction {
	public void sync(Identifiable entity);

	public void update(Identifiable entity);

	public void delete(Identifiable entity);

	public void exec(String jpql);
}
