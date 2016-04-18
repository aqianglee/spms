package com.aqiang.spms.service.impl;

import java.util.Arrays;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aqiang.spms.dao.GenericDao;
import com.aqiang.spms.dao.Restrictions;
import com.aqiang.spms.entities.Project;
import com.aqiang.spms.entities.Team;
import com.aqiang.spms.service.ProjectService;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private GenericDao dao;

	@Override
	public List<Project> getProjects(Team team) {
		return dao.list(Project.class,
				Arrays.asList(Restrictions.eq("team", team)));
	}

}
