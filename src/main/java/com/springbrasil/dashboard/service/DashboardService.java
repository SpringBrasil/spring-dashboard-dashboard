package com.springbrasil.dashboard.service;

import java.util.Optional;

import javax.ws.rs.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.springbrasil.dashboard.dao.DashboardDao;
import com.springbrasil.dashboard.model.Dashboard;

@Service
public class DashboardService {
	
	@Autowired
	private DashboardDao repositoryDao;
	
	public Dashboard save(Dashboard dashboard) {
		return repositoryDao.save(dashboard);
	}

	public Page<Dashboard> getAll(Integer page, Integer size) {
		return repositoryDao.findAll(new PageRequest(page, size));
	}

	public Dashboard get(String repositoryId) {
		return Optional
				.ofNullable(repositoryDao.findOne(repositoryId))
				.orElseThrow(() -> new NotFoundException(repositoryId));
	}

}
