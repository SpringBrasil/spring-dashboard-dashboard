package com.springbrasil.dashboard.controller.response;

import com.springbrasil.dashboard.model.Dashboard;

public class DashboardCreateResponse {
	
	private String id;
	
	public DashboardCreateResponse() {}
	
	public DashboardCreateResponse(Dashboard repository) {
		this.id = repository.getId();
	}
	
	public String getId() {
		return id;
	}
	
	
}
