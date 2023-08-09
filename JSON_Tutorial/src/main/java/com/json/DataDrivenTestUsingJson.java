package com.json;

import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DataDrivenTestUsingJson {
	static WebDriver driver;

	@BeforeClass
	public void setup() {
		WebDriverManager.edgedriver().setup();
		driver = new EdgeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@AfterClass
	public void tearDown() {
		driver.close();
	}

	@Test
	public void login(String data) {
		
		driver.get("https://demo.nopcommerce.com/login?returnUrl=%2F");
		String users[] = data.split(" , ");
		driver.findElement(By.id("Email")).sendKeys(users[0]);
		driver.findElement(By.id("Password")).sendKeys(users[1]);
		driver.findElement(By.xpath("//*[@class='button-1 login-button']")).click();
		String act_title = driver.getTitle();
		String exp_title = "nopCommerce demo store";
		Assert.assertEquals(act_title, exp_title);
	}

	@DataProvider(name = "dataprovider")
	public String[] readJson() throws IOException, ParseException {
		JSONParser jsonParser = new JSONParser();
		FileReader fileReader = new FileReader(".\\Json File\\UserLogin.json");
		Object object = jsonParser.parse(fileReader);
		JSONObject userLoginJSON = (JSONObject) object;
		JSONArray userLoginJSONArray = (JSONArray) userLoginJSON.get("userLogins");
		String arr[] = new String[userLoginJSONArray.size()];
		for (int i = 0; i < userLoginJSONArray.size(); i++) {
			JSONObject users = (JSONObject) userLoginJSONArray.get(i);
			String userName = (String) users.get("userName");
			String password = (String) users.get("password");
			arr[i] = userName + "," + password;

		}
		return arr;
	}
}
