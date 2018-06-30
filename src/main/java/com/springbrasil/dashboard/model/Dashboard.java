package com.springbrasil.dashboard.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;

public class Dashboard {

	@Id
	private String id;
	private String name;
	private List<DashboardRepository> dashboardRepositories = new ArrayList<>();
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<DashboardRepository> getDashboardRepositories() {
		return dashboardRepositories;
	}
	public void setDashboardRepositories(List<DashboardRepository> dashboardRepositories) {
		this.dashboardRepositories = dashboardRepositories;
	}

}
