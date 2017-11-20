package com.springbrasil.dashboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springbrasil.dashboard.dao.DashboardDao;
import com.springbrasil.dashboard.model.Dashboard;

@Service
public class DashboardService {
	
	@Autowired
	private DashboardDao dashboardDao;
	
	public Dashboard save(Dashboard dashboard) {
		return dashboardDao.save(dashboard);
	}

}
