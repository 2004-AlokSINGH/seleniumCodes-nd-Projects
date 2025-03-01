package configppt;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

public class ConfigureProperties {
	Properties properties;

	public ConfigureProperties() {
		properties = new Properties();
		File file = new File(
				"src/test/resources/config/config.properties");
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(file);
		} catch (FileNotFoundException e) {
			System.out.println(e.getMessage());
		}
		try {
			properties.load(fis);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public String getProperty(String property) {
		return properties.getProperty(property);
	}

	public void displayAllProperties() {
		for (Map.Entry<Object, Object> entry : properties.entrySet()) {
			System.out.println(entry.getKey() + " = " + entry.getValue());
		}
	}
}
