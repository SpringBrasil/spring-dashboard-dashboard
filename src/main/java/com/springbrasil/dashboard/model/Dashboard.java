package com.springbrasil.dashboard.model;

import org.springframework.data.annotation.Id;

public class Dashboard {

	@Id
	public String id;
	public String name;
	
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

}
