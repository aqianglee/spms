package com.aqiang.spms.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.aqiang.spms.dao.Identifiable;

@Entity
@Table(name = "projects")
public class Project implements Identifiable {
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String name;
	private Date createTime;
	private User customer;
	private Team team;
	private List<Schedual> scheduals;

	public Project() {
		scheduals = new ArrayList<Schedual>();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@ManyToOne
	@JoinColumn(name = "CUSTOMER_ID")
	public User getCustomer() {
		return customer;
	}

	public void setCustomer(User customer) {
		this.customer = customer;
	}

	@ManyToOne
	@JoinColumn(name = "ITAM_ID")
	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	@OneToMany
	@JoinColumn(name = "PROJECT_ID")
	public List<Schedual> getScheduals() {
		return scheduals;
	}

	public void setScheduals(List<Schedual> scheduals) {
		this.scheduals = scheduals;
	}

}
