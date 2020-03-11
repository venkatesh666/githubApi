package com.api.service;

import java.util.List;

import com.api.dto.UserDetails;

public interface ApiService {
	List<UserDetails> getFollowerDetails(Integer gitId);
}
