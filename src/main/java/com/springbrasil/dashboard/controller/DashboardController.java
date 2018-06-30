package com.springbrasil.dashboard.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.springbrasil.dashboard.controller.request.DashboardCreateRequest;
import com.springbrasil.dashboard.controller.response.DashboardCreateResponse;
import com.springbrasil.dashboard.model.Dashboard;
import com.springbrasil.dashboard.service.DashboardService;

@RestController
public class DashboardController {

	@Autowired
	private DashboardService dashboardService;

	@RequestMapping(method = RequestMethod.POST, value = "/dashboards", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(code = HttpStatus.CREATED)
	public DashboardCreateResponse createDashboard(@RequestBody @Valid DashboardCreateRequest request) {
		Dashboard savedDashboard = dashboardService.save(request.toModel());
		return new DashboardCreateResponse(savedDashboard);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/dashboards", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	public Page<Dashboard> getDashboards(
			@RequestParam(required=false, defaultValue="0") Integer page,
			@RequestParam(required=false, defaultValue="10") Integer size) {
		return dashboardService.getAll(page, size);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/dashboards/{dashboard_id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseStatus(code = HttpStatus.OK)
	public Dashboard getDashboards(@PathVariable(name="dashboard_id") String dashboardId) {
		return dashboardService.get(dashboardId);
	}

}
