package com.autothon.utility;

import java.util.Map;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class HttpUtils {
	Response response = null;

	public Response postRequest(String baseUri, Map<String, String> headers, Map<String, String> queryParams,
			String pathParams, String requestPayLoad) {
		RestAssured.baseURI = baseUri;
		response = (Response) RestAssured.given().headers(headers).queryParams(queryParams).body(requestPayLoad).when()
				.post(pathParams);
		return response;
	}

	public Response getRequest(String baseUri, Map<String, String> headers, Map<String, String> queryParams,
			String pathParams) {
		RestAssured.baseURI = baseUri;
		response = (Response) RestAssured.given().headers(headers).queryParams(queryParams).when().get(pathParams);
		return response;
	}

	public Response putRequest(String baseUri, Map<String, String> headers, Map<String, String> queryParams,
			String pathParams, String requestPayLoad) {
		RestAssured.baseURI = baseUri;
		response = (Response) RestAssured.given().headers(headers).queryParams(queryParams).body(requestPayLoad).log()
				.all().when().put(pathParams);
		return response;
	}

	public Response deleteRequest(String baseUri, Map<String, String> headers, Map<String, String> queryParams,
			String pathParams, String requestPayLoad) {
		RestAssured.baseURI = baseUri;
		response = (Response) RestAssured.given().headers(headers).queryParams(queryParams).body(requestPayLoad).log()
				.all().when().delete(pathParams);
		return response;
	}

}
