package com.ssafy.nadori.controller;

import java.io.IOException;

import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResponseErrorHandler;

@Component
public class CustomResponseErrorHandler implements ResponseErrorHandler {
	@Override
	public boolean hasError(ClientHttpResponse response) throws IOException {
		return response.getStatusCode().is5xxServerError() || 
				response.getStatusCode().is4xxClientError();
	}

	@Override
	public void handleError(ClientHttpResponse response) throws IOException {
		if (response.getStatusCode().is5xxServerError()) {
            //Handle SERVER_ERROR
            throw new HttpClientErrorException(response.getStatusCode());
        } else if (response.getStatusCode().is4xxClientError()) {
            //Handle CLIENT_ERROR
//            if (response.getStatusCode() == HttpStatus.NOT_FOUND) {
//                throw new NotFoundException("");
//            }
        }
	}
}