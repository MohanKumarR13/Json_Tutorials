package com.json;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class ReadDataFromJSONFile {

	public static void main(String[] args) throws IOException, ParseException {
		JSONParser jsonParser = new JSONParser();
		FileReader fileReader = new FileReader(".\\Json File\\Employee.json");
		Object object = jsonParser.parse(fileReader);
		JSONObject jsonObject = (JSONObject) object;
		String firstName = (String) jsonObject.get("firstName");
		String lastName = (String) jsonObject.get("lastName");
		System.out.println("First Name : " + firstName);
		System.out.println("Last Name : " + lastName);
		JSONArray jsonArray = (JSONArray) jsonObject.get("address");
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject address = (JSONObject) jsonArray.get(i);
			String street = (String) address.get("street");
			String city = (String) address.get("city");
			String state = (String) address.get("state");
			System.out.println("Address of " + i + " is.....");
			System.out.println("Street : " + street);
			System.out.println("City : " + city);
			System.out.println("State : " + state);

		}

	}

}
