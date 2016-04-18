package com.aqiang.spms.service;

import java.util.List;

import com.aqiang.spms.entities.Project;
import com.aqiang.spms.entities.Team;

public interface ProjectService {
	
	public List<Project> getProjects(Team team);
}
