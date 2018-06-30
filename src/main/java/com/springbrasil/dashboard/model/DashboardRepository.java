package com.springbrasil.dashboard.model;

import org.springframework.data.mongodb.core.index.Indexed;

public class DashboardRepository {

	@Indexed
	private String repositoryId;
	private RepositoryConfig config;
	
	public String getRepositoryId() {
		return repositoryId;
	}
	public void setRepositoryId(String repositoryId) {
		this.repositoryId = repositoryId;
	}
	public RepositoryConfig getConfig() {
		return config;
	}
	public void setConfig(RepositoryConfig config) {
		this.config = config;
	}

}