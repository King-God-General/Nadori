package com.ssafy.nadori.model.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientRequestException;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriComponentsBuilder;

import com.ssafy.nadori.model.dto.Attraction;

import reactor.core.publisher.Flux;


@Service
public class TourApiService {

	
	
	public List<Attraction> getAttraction(){
		
		String BASE_URL ="https://apis.data.go.kr/B551011/KorService1";
		
		String serviceKey = "TSThjH4bhNocM8qCDWfFXjoGa5M5XVTpItFdR+J8wa11BYgaBzeaNPtDjDOJ2dzDMPgG/KU3t8hrgv99qX4nhQ==";
		//String serviceKey = "TSThjH4bhNocM8qCDWfFXjoGa5M5XVTpItFdR%2BJ8wa11BYgaBzeaNPtDjDOJ2dzDMPgG%2FKU3t8hrgv99qX4nhQ%3D%3D";
		
		int numOfRows = 100;
        String MobileOS = "ETC";
        String MobileApp = "Nadori";
        
        String uriString = UriComponentsBuilder.fromHttpUrl(BASE_URL)
                .path("/areaBasedList1")
                .queryParam("serviceKey", serviceKey)
                .queryParam("numOfRows", numOfRows)
                .queryParam("MobileOS", MobileOS)
                .queryParam("MobileApp", MobileApp)
                .queryParam("_type", "json")
                .build()
                .toUriString();

        System.out.println(uriString);
        
        
        
		
//		DefaultUriBuilderFactory factory = new DefaultUriBuilderFactory();
//		factory.setEncodingMode(DefaultUriBuilderFactory.EncodingMode.NONE);
		
		WebClient webClient = WebClient.builder()
				//.uriBuilderFactory(factory)
				.baseUrl(BASE_URL)
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.build();
		
		
        
        
		Flux<Attraction> response = webClient
				.get()
				.uri(UriBuilder -> UriBuilder.path("/areaBasedList1")
						.queryParam("_type", "json")
						.queryParam("serviceKey", serviceKey)
						.queryParam("numOfRows", numOfRows)
						.queryParam("MobileOS", MobileOS)
						.queryParam("MobileApp", MobileApp)
						.build())
				.retrieve()
				.bodyToFlux(Attraction.class);
		
		response
	    .doOnNext(attraction -> {
	        // 각 Attraction 객체를 처리하고 출력
	        System.out.println(attraction);
	    })
	    .subscribe(); // Flux를 구독하여 시작
		
		List<Attraction> attractions = response.toStream().collect(Collectors.toList());
		
        System.out.println(attractions.size());
        
        return attractions;
	}
	
	
	
}
