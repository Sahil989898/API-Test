package com.api.Helpers;

import static org.testng.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.hamcrest.Matchers;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import com.api.model.AddUserPOJO;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBodyExtractionOptions;
import io.restassured.response.ValidatableResponse;

public class ReusableMethods {

	public static String getbaseUri(String baseURL) {
		String baseURI = RestAssured.baseURI = "https://qa01-tekarch-accmanager.web.app/";
		return baseURI;
	}
	
	public int getStatusCode(Response response) {
		return response.getStatusCode();
	}
	public String getContentType(Response res) {
		return res.getContentType();
	}
	public long getResponseTimeIn(Response response, TimeUnit unit) {
		return response.getTimeIn(unit);
	}
	
	public static void verifyStatusCode(Response res, int statuscode) {
		res.then().statusCode(statuscode);
	}
	public static String UserId(Response response) {
		String userid =  response.jsonPath().get("data[].id");
		return userid;
	}
	public static Response getBaseUri(RestAssured RestAssured) {
    	Response BaseUri = RestAssured.given().get("https://dummy.restapiexample.com/api/v1");
    	return BaseUri;
	}
	public static void timelessthan(Response response) {
		response.then().time(Matchers.lessThanOrEqualTo(2000L));
	}
	public static void OkstatusCodeEqualto(Response response) {
		response.then().body("code", Matchers.equalTo(response));
	}
	public static void CreatedstatusCodeEqualto(Response response) {
		response.then().body("code", Matchers.equalTo(response));
	}
	public static void validateId(Response response) {
		response.then().body("data[].id", Matchers.equalTo(2000));
	}
	public static void validateName(Response response) {
		String name = response.jsonPath().getString("data[].name");
		System.out.println("name="+name);
	}
	public static void getdataSize(Response response) {
		response.jsonPath().get("data.size()");
	}
	public static void GetJsonPath(Response response) {
	response.jsonPath();
	}
	public static void queryParam(Response response) {
		RestAssured.given().queryParam("name", "value");
	}
	public static void contentType() {  
	RestAssured.given().contentType(ContentType.JSON);
	}
	public static void Auth(String userName, String password) {
		RestAssured.given().auth().basic(userName, password);
	}
	public static void print(Response res) {
		res.jsonPath().prettyPrint();
	}
	public static void verifyStatussuccess(Response res) {
		res.then().body("status", Matchers.equalTo("success"));
	}
	public static JsonPath getJsonPathData(Response response, String string) {
		return response.jsonPath();
	}
	public static void getBody(Response res) {
		res.getBody();
	}
	public static void getid(Response res) {
		String id = res.jsonPath().get("data.id");
	}
		public static void verifySuccess(Response res) {
			res.then().assertThat().body("status", Matchers.equalTo("success"));
		}
		
	}