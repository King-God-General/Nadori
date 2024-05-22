package com.ssafy.nadori.model.service;

import java.util.HashMap;

public interface OAuthService {
	public String getAccessToken(String code);
	public HashMap<String, Object> getMemberInfo(String accessToken);
}
