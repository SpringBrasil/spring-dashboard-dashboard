package com.springbrasil.dashboard.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.springbrasil.dashboard.model.Dashboard;

public interface DashboardDao extends MongoRepository<Dashboard, String>{

}
