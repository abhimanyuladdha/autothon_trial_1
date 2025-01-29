package com.autothon.utility;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.supercsv.io.CsvMapReader;
import org.supercsv.io.ICsvMapReader;
import org.supercsv.prefs.CsvPreference;

public class CsvUtils {
	Map<String, Map<String, String>> allValues = new HashMap<String, Map<String, String>>();
	
	public Map<String, String> getUITestData(String identifier, String testDataFilePath)
	{
		Map<String, Map<String, String>> csvTestCases=readTestDataValues(testDataFilePath);
		Map<String, String> data=csvTestCases.get(identifier);
		return data;
	}

	public Map<String, Map<String, String>> readTestDataValues(String testDataFilePath) {
		ICsvMapReader listReader;
		try {
			listReader = new CsvMapReader(new FileReader(testDataFilePath), CsvPreference.STANDARD_PREFERENCE);
			final String[] headers = listReader.getHeader(true);
			Map<String, String> fieldsInCurrentRow;

			while ((fieldsInCurrentRow = listReader.read(headers)) != null) {
				readingHeaders(fieldsInCurrentRow);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return allValues;
	}

	public void readingHeaders(Map<String, String> header) {
		String identifier = "";
		if (header.keySet() != null) {
			Set<String> keys = header.keySet();

			for (String key : keys) {
				if (key.contains("Identifier")) {
					identifier = header.get(key);
					allValues.put(identifier, header);
					break;
				}
			}
		}

	}
}
