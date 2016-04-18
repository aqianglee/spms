package com.aqiang.spms.wicket.page;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aqiang.spms.entities.Project;
import com.aqiang.spms.entities.Team;
import com.aqiang.spms.entities.User;
import com.aqiang.spms.service.ProjectService;
import com.aqiang.spms.wicket.PageTest;
import com.ttdev.wicketpagetest.MockableSpringBeanInjector;
import com.ttdev.wicketpagetest.WebPageTestContext;
import com.ttdev.wicketpagetest.WicketSeleniumDriver;

public class ManageProjectTest extends PageTest {
	private static final Logger logger = LoggerFactory
			.getLogger(ManageProjectTest.class);

	abstract class MockProjectService implements ProjectService {
		ArrayList<Project> projects = new ArrayList<Project>();

		@Override
		public List<Project> getProjects(Team team) {
			User customer = new User();
			customer.setName("青海大学");
			if (projects.size() == 0) {
				logger.info("add a project into projects");
				createProject(team, customer, "GDMS", "2015/05/10");
				createProject(team, customer, "QHUSM", "2015/05/20");
				createProject(team, customer, "SPMS", "2015/12/10");
			}
			logger.info("get {} projects", projects.size());
			return projects;
		}

		private void createProject(Team team, User customer, String name,
				String createDate) {
			Project project = new Project();
			project.setCustomer(customer);
			project.setTeam(team);
			project.setName(name);
			project.setCreateTime(date(createDate, "yyyy/MM/dd"));
			project.setId(projects.size() + 1);
			projects.add(project);
		}
	}

	@Test
	public void test() {
		MockableSpringBeanInjector.mockBean("projectService",
				factory.implementAbstractMethods(MockProjectService.class));
		WicketSeleniumDriver ws = WebPageTestContext.getWicketSelenium();
		Team team = new Team();
		team.setName("强强工作室");
		ws.openNonBookmarkablePage(ManageProject.class, team);
		isTrue(ws.isElementPresent("//projectsTab"));
		isTrue(ws.isElementPresent("//projects"));
		isTrue(ws.getText("//projects[0]//name").equals("GDMS"));
		isTrue(ws.getText("//projects[1]//name").equals("QHUSM"));
		isTrue(ws.getText("//projects[2]//name").equals("SPMS"));
		
		isTrue(ws.getText("//projects[0]//createTime").equals("2015-05-10"));
		isTrue(ws.getText("//projects[1]//createTime").equals("2015-05-20"));
		isTrue(ws.getText("//projects[2]//createTime").equals("2015-12-10"));
		
		isTrue(ws.getText("//projects[0]//team.name").equals("强强工作室"));
		isTrue(ws.getText("//projects[1]//team.name").equals("强强工作室"));
		isTrue(ws.getText("//projects[2]//team.name").equals("强强工作室"));
		
		isTrue(ws.getText("//projects[0]//customer.name").equals("青海大学"));
		isTrue(ws.getText("//projects[1]//customer.name").equals("青海大学"));
		isTrue(ws.getText("//projects[2]//customer.name").equals("青海大学"));
	}

}
