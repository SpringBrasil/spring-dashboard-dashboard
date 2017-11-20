package com.springbrasil.dashboard.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;

import com.springbrasil.dashboard.model.Dashboard;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DashboardServiceIntegrationTest {
	
	@Autowired
	private DashboardService dashboardService;
	
	@Test
	public void shouldSaveDashboard() {
		Dashboard dashboard = new Dashboard();
		dashboard.setName("My Dashboard");
		Dashboard savedDashboard = dashboardService.save(dashboard);
		
		assertThat(savedDashboard.getId()).isNotNull();
		
	}

}
