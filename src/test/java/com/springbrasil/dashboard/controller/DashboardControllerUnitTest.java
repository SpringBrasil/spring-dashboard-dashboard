package com.springbrasil.dashboard.controller;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.springbrasil.dashboard.helper.JsonHelper;
import com.springbrasil.dashboard.model.Dashboard;
import com.springbrasil.dashboard.service.DashboardService;

@RunWith(SpringRunner.class)
@WebMvcTest(DashboardController.class)
public class DashboardControllerUnitTest {
	
	@MockBean
	private DashboardService dashboardService;
	
	@Autowired
    private MockMvc mvc;
	
	private Dashboard dashboard;
	
	
	
	@Before
	public void setUp() {
		dashboard = new Dashboard();
		dashboard.setId("6e71d0d568e134c029203593b00a0103e7cdf30b");
		dashboard.setName("My Dashboard");
		doReturn(dashboard).when(dashboardService).save(any(Dashboard.class));
	}
	
	@Test
	public void shouldCreateDashboard() throws Exception {
		String request = JsonHelper.getRequestFileAsString("dashboard/create_dashboard_success.json");
		String response = JsonHelper.getResponseFileAsString("dashboard/create_dashboard_success.json");
		
		mvc.perform(post("/dashboard")
			.contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
			.content(request)
			.accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
			)
	        .andExpect(status().isCreated()).andExpect(content().json(response));
		
		verify(dashboardService, times(1)).save(any(Dashboard.class));
	}

}
