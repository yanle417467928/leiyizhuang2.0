package com.ynyes.fitment.foundation.service;

import org.springframework.data.domain.Page;

import com.ynyes.fitment.core.entity.client.result.ClientResult;
import com.ynyes.fitment.foundation.entity.FitEmployee;

public interface FitEmployeeService {
	
	FitEmployee managerSaveEmployee(FitEmployee employee) throws Exception;
	
	void delete(Long id) throws Exception;
	
	FitEmployee findOne(Long id) throws Exception;
	
	Page<FitEmployee> findAll(Integer page, Integer size) throws Exception;
	
	Boolean validateRepeatEmployeeByMobile(String mobile, Long id) throws Exception;

	ClientResult login(String mobile, String password) throws Exception;
}
