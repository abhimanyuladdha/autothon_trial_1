package com.autothon.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.json.simple.parser.ParseException;
import org.testng.Assert;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

public class JsonOperations {

	/*
	 * Extract data from the json file
	 */
	public String extractDataFromJson(String jsonFilePath, String jsonPath)
			throws FileNotFoundException, IOException, ParseException {
		String json = readJsonFile(jsonFilePath);
		DocumentContext ctx = JsonPath.parse(json);
		Object data = null;
		try {
			data = ctx.read(jsonPath);
		} catch (Exception e) {
			e.getMessage();
			return null;
		}
		return data.toString();
	}

	/*
	 * Validate the data for a given key in json file
	 */
	public void validateResponse(String jsonFilePath, String jsonPath, String expectedValue)
			throws FileNotFoundException, IOException, ParseException {
		String actualResult = extractDataFromJson(jsonFilePath, jsonPath);
		Assert.assertEquals(actualResult, expectedValue, "The data doesn't match");
	}

	/*
	 * Validate given key is present in sample json
	 */
	public Boolean validateKeyExistance(String jsonFilePath, String keyName)
			throws FileNotFoundException, IOException, ParseException {
		if (extractDataFromJson(jsonFilePath, keyName) == null) {
			return false;
		}
		return true;
	}

	/*
	 * Create the json payload
	 */
	public String getJsonPayload(String identifier, String testDataFilePath, String JsonTempelatePath) {
		Map<String, Map<String, String>> csvTestCases = new CsvUtils().readTestDataValues(testDataFilePath);
		Map<String, String> data = csvTestCases.get(identifier);

		String json = readJsonFile(JsonTempelatePath);
		DocumentContext ctx = JsonPath.parse(json);

		data.forEach((k, v) -> {
			jsonRequest(ctx, k, v);
		});

		return ctx.jsonString();

	}

	public DocumentContext jsonRequest(DocumentContext ctx, String k, String v) {
		String key = "$.".concat(k);
		if (v == null) {
			removeKey(ctx, key);
		} else if (("false".equalsIgnoreCase(v) || "true".equalsIgnoreCase(v))) {
			replaceJsonBooleanValue(ctx, key, v);
		} else if (key.contains("Identifier")) {
			return ctx;
		} else {
			replaceJsonKeyValue(ctx, key, v);
		}

		return ctx;
	}

	public DocumentContext replaceJsonKeyValue(DocumentContext ctx, String path, String newValue) {
		return ctx.set(path, newValue);
	}

	public DocumentContext replaceJsonBooleanValue(DocumentContext ctx, String path, String newValue) {
		if ("false".equalsIgnoreCase(newValue)) {
			return ctx.set(path, Boolean.FALSE);
		} else
			return ctx.set(path, Boolean.TRUE);
	}

	public DocumentContext removeKey(DocumentContext ctx, String key) {
		return ctx.delete(key);
	}

	/*
	 * update the json payload for given path
	 */
	public String updatePayload(String json, String path, Object newValue) {
		DocumentContext ctx = JsonPath.parse(json);
		DocumentContext finalJson = ctx.set(path, newValue);
		return finalJson.jsonString();
	}

	/*
	 * read json file
	 */
	public String readJsonFile(String josnTempelatePath) {
		String json = null;
		try {
			json = FileUtils.readFileToString(new File(josnTempelatePath), StandardCharsets.UTF_8);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return json;
	}
}
