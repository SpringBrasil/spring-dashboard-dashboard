package com.springbrasil.dashboard.controller;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import javax.ws.rs.NotFoundException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.util.NestedServletException;

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
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	private Dashboard dashboard;
	
	@Before
	public void setUp() {
		dashboard = new Dashboard();
		dashboard.setId("6e71d0d568e134c029203593b00a0103e7cdf30b");
		dashboard.setName("My Dashboard");
		doReturn(dashboard).when(dashboardService).save(any(Dashboard.class));
		doReturn(new PageImpl<>(Arrays.asList(dashboard))).when(dashboardService).getAll(anyInt(), anyInt());
		doReturn(dashboard).when(dashboardService).get(eq("6e71d0d568e134c029203593b00a0103e7cdf30b"));
		doThrow(new NotFoundException("unexisting_dashboard")).when(dashboardService).get(eq("unexisting_dashboard"));
	}

	@Test
	public void shouldCreateDashboard() throws Exception {
		String request = JsonHelper.getRequestFileAsString("dashboard/create_dashboard_success.json");
		String response = JsonHelper.getResponseFileAsString("dashboard/create_dashboard_success.json");

		mvc.perform(post("/dashboards").contentType(MediaType.APPLICATION_JSON_UTF8_VALUE).content(request)
				.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isCreated())
				.andExpect(content().json(response));

		verify(dashboardService, times(1)).save(any(Dashboard.class));
	}

	@Test
	public void shouldGetAllDashboards() throws Exception {
		String response = JsonHelper.getResponseFileAsString("dashboard/get_all_dashboards_success.json");

		mvc.perform(get("/dashboards").accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk())
				.andExpect(content().json(response));

		verify(dashboardService, times(1)).getAll(0, 10);
	}
	
	@Test
	public void shouldGetOneDashboard() throws Exception {
		String response = JsonHelper.getResponseFileAsString("dashboard/get_one_dashboard_success.json");

		mvc.perform(get("/dashboards/6e71d0d568e134c029203593b00a0103e7cdf30b").accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isOk())
				.andExpect(content().json(response));

		verify(dashboardService, times(1)).get("6e71d0d568e134c029203593b00a0103e7cdf30b");
	}
	
	@Test
	public void shouldThrowErrorWhenGetUnexistingDashboard() throws Exception {
		exception.expect(NestedServletException.class);
		exception.expectCause(instanceOf(NotFoundException.class));
		
		mvc.perform(get("/dashboards/unexisting_dashboard")
				.accept(MediaType.APPLICATION_JSON_UTF8_VALUE))
				.andExpect(status().isNotFound());
	}

}
