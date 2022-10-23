package com.test.tests;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Properties;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.api.Helpers.ReusableMethods;
import com.api.Helpers.UserServiceHelper;
import com.api.model.AddUserPOJO;

import com.api.model.GetPOJO;
import com.api.model.GetUserPOJO;
import com.api.model.LoginPOJO;
import com.api.utils.CommonUtilities;
import com.api.utils.GenerateReport;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;

public class APItestScripts extends UserServiceHelper {

	public static GenerateReport report = null;

	@BeforeClass
	public static void setUp() {
		RestAssured.baseURI = "https://dummy.restapiexample.com/api/v1";
	}

	@BeforeTest
	public static void setupBeforeTest() {
		report = GenerateReport.getInstance();
		report.startExtentReport();
	}

	@BeforeMethod
	public static void setUp(Method m) {
		report.startSingleTestReport(m.getName());
		report.logTestInfo("Before Method Start execution");
	}

	@AfterTest
	public static void teardownAfterTest() {
		report.endReport();
		report.logTestInfo("After Test execution");
	}

	@Test(enabled = true)
	public static void TC01GetUserData() {
		List<GetPOJO> listofUsers = getUserdata();

		report.logTestInfo("first user information in the userlist is :\n" + listofUsers.get(0));
		report.logTestInfo("data retreived successfully");
	}

	@Test(enabled = true)
	public static void TC02AddUSerData() throws Exception {
		Response response = addUserdata();
		System.out.println(response.getBody().asPrettyString());
		report.logTestInfo("data has added successfully");
		// response.then().body("name", Matchers.equalTo("test"));
		response.then().assertThat().body("data.name", Matchers.equalTo("test"));
		response.then().assertThat().body("data.salary", Matchers.equalTo("123"));
		response.then().assertThat().body("data.age", Matchers.equalTo("23"));
		ReusableMethods.getBody(response);
		int id = response.jsonPath().get("data.id");
		System.out.println("id is = " + id);
	}

	@Test(enabled = true)

	public static void TC03deleteUserData() {
		Response response = deleteUserid();
		System.out.println(response.getBody().asPrettyString());
		String idValue = response.jsonPath().get("data");
		System.out.println("" + idValue);
		ReusableMethods.verifyStatusCode(response, 200);
		response.then().assertThat().body("status", Matchers.equalTo("success"));
		System.out.println("*************************");
		response.then().body("data", Matchers.equalTo(idValue));
		System.out.println("message=" + response.jsonPath().get("message"));

		System.out.println("deleted successfully");
		report.logTestInfo("data has deleted successfully");
	}

	@Test(enabled = true)

	public static void TC04deleteID0Data() {
		Response response = delete0Userid();
		ReusableMethods.verifyStatusCode(response, 400);

		System.out.println("ID 0 Delete Attempt Response " + response.getStatusCode());
		report.logTestInfo("data has deleted successfully");
	}

	@Test(enabled = true)
	public static void TC05GetUserDataByID() {
		List<GetPOJO> listofUsers = getSpecificUserdata();

		report.logTestInfo("first user information in the userlist is :\n" + listofUsers.get(0));
		report.logTestInfo("data retreived successfully");
	}
	
}
