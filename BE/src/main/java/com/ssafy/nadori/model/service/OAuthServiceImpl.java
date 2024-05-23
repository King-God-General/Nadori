package com.ssafy.nadori.model.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class OAuthServiceImpl implements OAuthService{

	@Override
	public String[] getTokens(String code) {
		
		String accessToken="";
		String refreshToken="";
		String requestURL = "https://kauth.kakao.com/oauth/token";
		String clientId="7fea046e2e3c36e3b420930e6347e166";
		String requestURi = "http://127.0.0.1:8080/api/member/login/kakao";
		
		try {
			URL url = new URL(requestURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			StringBuilder sb = new StringBuilder();
			sb.append("grant_type=authorization_code");
			sb.append("&client_id="+clientId);
			sb.append("&redirect_uri="+requestURi);
			sb.append("&code="+code);
			
			bw.write(sb.toString());
			bw.flush();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line="";
			String result="";
			while((line=br.readLine()) != null) {
				result+=line;
			}
			
			JSONParser parser = new JSONParser();
			JSONObject element = (JSONObject) parser.parse(result.toString());
			
			accessToken = (String) element.get("access_token");
            refreshToken = (String) element.get("refresh_token");
			
			br.close();
			bw.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return new String[] {accessToken, refreshToken};
	}

	@Override
	public HashMap<String, String> getMemberInfo(String accessToken) {
		
		HashMap<String, String> memberInfo = new HashMap<>();
		String requestURL = "https://kapi.kakao.com/v2/user/me";
		
		try {
			URL url = new URL(requestURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Authorization", "Bearer "+accessToken);
			conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
			
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line="";
			String result="";
			while((line=br.readLine()) != null) {
				result+=line;
			}
			System.out.println(result);
			
			JSONParser parser = new JSONParser();
			JSONObject element = (JSONObject) parser.parse(result.toString());
			String memberId = element.get("id").toString();
			memberInfo.put("memberId", memberId);
			
			JSONObject properties = (JSONObject) element.get("properties");
            String nickname = (String) properties.get("nickname");
            memberInfo.put("nickname", nickname);
			
			br.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return memberInfo;
	}
	
}
