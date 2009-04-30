package mymoney.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileContentParser {
	
	private static final String NEW_LINE = System.getProperty("line.separator");

	public static String getFileContent(String filePath) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
		StringBuilder content = new StringBuilder();
		String line = reader.readLine(); 
		while (line != null) {
			content.append(line+NEW_LINE);
			line = reader.readLine();
		}
		return content.toString();
	}

}
