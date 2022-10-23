package com.api.Helpers;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import javax.lang.model.*;
import com.api.constants.EndPoints;
import com.api.model.AddUserPOJO;
import com.api.model.DeleteUserPOJO;
import com.api.model.GetPOJO;
import com.api.model.GetUserPOJO;
import com.api.model.LoginPOJO;
import com.api.model.LoginResponsePOJO;
import com.api.model.UpdateUserPOJO;

import com.api.utils.CommonUtilities;
import com.fasterxml.jackson.core.type.TypeReference;

import static org.testng.Assert.assertEquals;

import java.lang.reflect.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;


public class UserServiceHelper extends ReusableMethods{
	private static Response response;
	private static String token = null;

	public static void getBaseURI() {  
		CommonUtilities CU = new CommonUtilities();
		Properties applicationPropertiesFile=CU.loadFile("applicationProperties");
		String baseURI=CU.getApplicationProperty("baseURI", applicationPropertiesFile);
		//RestAssured.baseURI = "https://us-central1-qa01-tekarch-accmanager.cloudfunctions.net/";
	}	
	public static List<GetPOJO> getUserdata() {
		
		response = RestAssured
				.given().contentType(ContentType.JSON).when()
				.get(EndPoints.GET_DATA)
				.andReturn();	
	
		GetPOJO userArray = response.as(GetPOJO.class);
		List<GetPOJO> list = Arrays.asList(userArray);
		System.out.println("Response is="+list);
		response.then().statusCode(200);
		response.prettyPrint();
		response.then().body("status", Matchers.equalTo("success"));
		System.out.println("number of records=" + response.jsonPath().get("data.size"));
		
		return list;
	}

	public static Response addUserdata() {	
		CommonUtilities cu = new CommonUtilities();
		Properties applicationPropertiesFile=cu.loadFile("applicationProperties");
		//cu.getApplicationProperty("id", applicationPropertiesFile).concat(token);
		Header header = new Header("token", token);
		System.out.println("Haa   "+header.getValue());
		AddUserPOJO adduser = new AddUserPOJO();
		adduser.setName(cu.getApplicationProperty("name", applicationPropertiesFile));
		adduser.setSalary(cu.getApplicationProperty("salary", applicationPropertiesFile));
		adduser.setAge(cu.getApplicationProperty("age", applicationPropertiesFile));
		
		response = RestAssured.given()
				.contentType(ContentType.JSON)
				.body(adduser)
				.when()
				.post(EndPoints.ADD_DATA);
		 	return response;
	}
	
	public static Response deleteUserid() {	
		response = addUserdata();
		int id = response.jsonPath().get("data.id");
		System.out.println("id in deleteUser id--"+id);
		CommonUtilities cu = new CommonUtilities();
		//Properties applicationPropertiesFile=cu.loadFile("applicationProperties");
		
	    DeleteUserPOJO deleteuser = new DeleteUserPOJO();
		deleteuser.setId(id);
		
		response = RestAssured.given().contentType(ContentType.JSON)
				.body(deleteuser).when().delete(EndPoints.DELETE_DATA + "/" + Integer.toString(id));
		
		return response;
	}

	public static Response delete0Userid() {	
		response = addUserdata();
		
	    DeleteUserPOJO deleteuser = new DeleteUserPOJO();
		deleteuser.setId(0);
		
		response = RestAssured.given().contentType(ContentType.JSON)
				.body(deleteuser).when().delete(EndPoints.DELETE_DATA + "/" + Integer.toString(0));
		
		return response;
	}
	
	public static List<GetPOJO> getSpecificUserdata() {
		
		response = RestAssured
				.given().contentType(ContentType.JSON).when()
				.get(EndPoints.GET_SPECIFIC_DATA + "/" + Integer.toString(2))
				.andReturn();	
	
		GetPOJO userArray = response.as(GetPOJO.class);
		List<GetPOJO> list = Arrays.asList(userArray);
		System.out.println("Response is="+list);
		response.then().statusCode(200);
		response.then().contentType("application/json");
		response.then().body("status", Matchers.equalTo("success"));
		response.then().body("data.employee_name", Matchers.equalTo("Garrett Winters"));
		response.then().body("data.employee_salary", Matchers.equalTo(170750));
		response.then().body("data.employee_age", Matchers.equalTo(63));
		
		return list;
	}
}