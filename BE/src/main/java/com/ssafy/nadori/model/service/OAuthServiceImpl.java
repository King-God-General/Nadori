package com.ssafy.nadori.model.service;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class OAuthServiceImpl implements OAuthService{

	@Override
	public String getAccessToken(String code) {
		
		String accessToken="";
		String refreshToken="";
		String requestURL = "https://kauth.kakao.com/oauth/token";
		String clientId="7fea046e2e3c36e3b420930e6347e166";
		
		try {
			URL url = new URL(requestURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			StringBuilder sb = new StringBuilder();
			sb.append("grant_type=authorization_code");
			sb.append("&client_id="+clientId);
			sb.append("&redirect_uri="+redirectUri);
		} catch() {
			
		}
		
		return null;
	}
	
}
