package com.ssafy.nadori.model.service;

import java.util.ArrayList;
import java.util.HashMap;

public interface OAuthService {
	public String[] getTokens(String code);
	public HashMap<String, String> getMemberInfo(String accessToken);
}
