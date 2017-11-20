package com.springbrasil.dashboard.service;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.springbrasil.dashboard.dao.DashboardDao;
import com.springbrasil.dashboard.model.Dashboard;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DashboardServiceUnitTest {
	
	@MockBean
	private DashboardDao dashboardDao;
	
	@Autowired
	private DashboardService dashboardService;
	
	@Test
	public void shouldSaveDashboard() {
		dashboardService.save(new Dashboard());
		
		verify(dashboardDao, times(1)).save(any(Dashboard.class));
	}

}
