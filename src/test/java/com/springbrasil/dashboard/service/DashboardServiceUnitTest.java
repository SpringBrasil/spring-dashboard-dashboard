package com.springbrasil.dashboard.service;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import javax.ws.rs.NotFoundException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import com.springbrasil.dashboard.dao.DashboardDao;
import com.springbrasil.dashboard.model.Dashboard;
import com.springbrasil.dashboard.service.DashboardService;

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
	
	@Test
	public void shouldFindAllDashboards() {
		dashboardService.getAll(0, 10);
		
		verify(dashboardDao, times(1)).findAll(any(PageRequest.class));
	}
	
	@Test
	public void shouldFindOneDashboard() {
		doReturn(new Dashboard()).when(dashboardDao).findOne(anyString());
		dashboardService.get("123");
		
		verify(dashboardDao, times(1)).findOne(anyString());
	}
	
	@Test(expected=NotFoundException.class)
	public void shouldThrowErrorWhenDoesntFindOneDashboard() {
		doReturn(null).when(dashboardDao).findOne(eq("unexisting_dashboard"));
		dashboardService.get("unexisting_dashboard");
	}

}
