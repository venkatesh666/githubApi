package com.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.dto.UserDetails;
import com.api.service.ApiService;

@RestController
public class ApiController {
	
	@Autowired
	private ApiService apiService;
	
	@GetMapping("/getFollowerDetails")
	public List<UserDetails> getFollowerDetails(Integer gitId){
	    return apiService.getFollowerDetails(gitId);
	}
}
