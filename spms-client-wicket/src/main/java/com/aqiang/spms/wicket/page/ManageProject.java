package com.aqiang.spms.wicket.page;

import java.util.Iterator;
import java.util.List;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.extensions.markup.html.repeater.util.SortableDataProvider;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.LoadableDetachableModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aqiang.spms.entities.Project;
import com.aqiang.spms.entities.Team;
import com.aqiang.spms.service.ProjectService;
import com.aqiang.spms.wicket.componet.DateLabel;

public class ManageProject extends BorderPage {

	private static final long serialVersionUID = 1L;
	private WebMarkupContainer projectsTab;
	private IModel<List<Project>> projectsModel;
	@SpringBean
	private ProjectService projectService;
	private static final Logger logger = LoggerFactory
			.getLogger(ManageProject.class);

	@Override
	protected void onDetach() {
		super.onDetach();
		projectsModel.detach();
	}

	public ManageProject(final Team team) {
		projectsModel = new LoadableDetachableModel<List<Project>>() {

			private static final long serialVersionUID = 1L;

			@Override
			protected List<Project> load() {
				return projectService.getProjects(team);
			}
		};
		projectsTab = new WebMarkupContainer("projectsTab");
		add(projectsTab);
		projectsTab.add(new DataView<Project>("projects",
				new SortableDataProvider<Project, Integer>() {
					private static final long serialVersionUID = 1L;

					@Override
					public Iterator<? extends Project> iterator(long first,
							long count) {
						return projectsModel.getObject().iterator();
					}

					@Override
					public long size() {
						logger.info("get {} projects", projectsModel
								.getObject().size());
						return projectsModel.getObject().size();
					}

					@Override
					public IModel<Project> model(Project object) {
						return new CompoundPropertyModel<Project>(object);
					}

				}) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void populateItem(Item<Project> item) {
				AjaxLink<Void> manageIssue = new AjaxLink<Void>("manageIssue") {
					private static final long serialVersionUID = 1L;

					@Override
					public void onClick(AjaxRequestTarget target) {

					}
				};
				manageIssue.add(new Label("name"));
				item.add(manageIssue);
				item.add(new DateLabel("createTime"));
				item.add(new Label("team.name"));
				item.add(new Label("customer.name"));
			}
		});
	}
}
