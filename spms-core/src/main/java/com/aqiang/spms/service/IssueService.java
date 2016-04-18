package com.aqiang.spms.service;

import java.util.List;

import com.aqiang.spms.entities.Issue;
import com.aqiang.spms.entities.Project;

public interface IssueService {
	public List<Issue> getIssues(Project project);
}
