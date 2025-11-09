package com.qa.apifm.api.client;

import java.util.Map;

import com.qa.apifm.Exceptions.APIExceptions;
import com.qa.apifm.constant.AuthType;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class RestClient {

	private RequestSpecification setupRequest(String baseURL, AuthType authType, ContentType contentType) {

		RequestSpecification request = RestAssured.given().log().all().baseUri(baseURL).contentType(contentType)
				.accept(contentType);

		switch (authType) {
		case BEARER_TOKEN:
			request.header("Authorization", "Bearer");
			break;

		case BASIC_AUTH:
			request.header("Authorization", "Basic");
			break;

		case API_KEY:
			request.header("x-api-key", "api key");
			break;

		case NO_AUTH:
			System.out.println("Authorization Not  required");
			break;
		default:
			System.out.println("this auth  not support, please pass correct  auth");
			throw new APIExceptions("===invalid Auth Type===");

		}
		
		return request;

	}

	public void get(String baseURL, String endPoint, Map<String, String> queryParams, Map<String, String> pathParams,
			AuthType authType, ContentType contentType) {

		setupRequest(baseURL, authType, contentType);
	}

}
