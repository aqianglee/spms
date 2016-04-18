package com.aqiang.spms.service.impl;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.aqiang.spms.BaseTest;
import com.aqiang.spms.dao.GenericDao;
import com.aqiang.spms.dao.UserAction;
import com.aqiang.spms.entities.Project;
import com.aqiang.spms.entities.Team;
import com.aqiang.spms.entities.User;
import com.aqiang.spms.service.ProjectService;

public class ProjectServiceImplTest extends BaseTest {

	@Autowired
	private ProjectService service;
	@Autowired
	private UserAction action;
	@Autowired
	private GenericDao dao;

	@Test
	public void testGetProjects() {
		Team team = new Team();
		team.setName("aqiangTeam");
		action.sync(team);
		team = dao.list(Team.class).get(0);
		User customer = new User();
		customer.setName("qhu");
		action.sync(customer);
		customer = dao.list(User.class).get(0);
		createProject(team, customer, "GDMS", "2015/05/10");
		createProject(team, customer, "GDMS", "2015/05/10");
		createProject(team, customer, "GDMS", "2015/05/10");
		List<Project> projects = service.getProjects(team);
		assertEquals(3, projects.size());

	}

	private void createProject(Team team, User customer, String name,
			String createDate) {
		Project project = new Project();
		project.setCustomer(customer);
		project.setTeam(team);
		project.setName(name);
		project.setCreateTime(date(createDate, "yyyy/MM/dd"));
		action.sync(project);
	}

}
