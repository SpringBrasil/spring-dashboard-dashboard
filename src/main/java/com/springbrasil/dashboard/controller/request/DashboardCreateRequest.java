package com.springbrasil.dashboard.controller.request;

import org.hibernate.validator.constraints.NotBlank;

import com.springbrasil.dashboard.model.Dashboard;

public class DashboardCreateRequest {
	
	@NotBlank
	private String name;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public Dashboard toModel() {
		Dashboard dashboard = new Dashboard();
		dashboard.setName(this.name);
		return dashboard;
	}
	
}
