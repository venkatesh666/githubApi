package com.api.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.api.dto.UserDetails;
import com.api.service.ApiService;

@Service
public class ApiServiceImpl implements ApiService {
	private final String GIT_URL = "https://api.github.com/users/octocat/followers";
	
	private UserDetails[] getFollowers(String url) {
		return new RestTemplate().getForObject(url, UserDetails[].class);
	}
	
	@Override
	public List<UserDetails> getFollowerDetails(Integer gitId){
		UserDetails[] results = getFollowers(GIT_URL);
	    List<UserDetails> gitUserDetails = new ArrayList<>();
	    int count = 0;
	    for(UserDetails user: results){
	    	if(count==5) break;
	    	if(gitId.equals(user.getId())){
	    		count++;
	    		UserDetails[] main = getFollowers(user.getFollowersUrl());
	    		 for(int i=0;i<main.length;i++){
	    			 UserDetails sub = main[i];
	    			 sub.setUsers(getFollowers(sub.getFollowersUrl()));
	    			 main[i] = sub;
	    		 }
	    		user.setUsers(main);
	    		gitUserDetails.add(user);
	    	}
	    }
	    return gitUserDetails;
	}

	
}
