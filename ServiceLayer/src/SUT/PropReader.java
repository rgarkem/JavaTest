package SUT;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Properties;

public class PropReader {

	private HashMap<String, String> props;
	
	public String getProperty(String key) {
		
		if (props == null) {
			props = new HashMap<String, String>();
			String propFileName = "config.properties";
			 
			try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(propFileName)){
				Properties prop = new Properties();	
				
				if (inputStream != null) {
					prop.load(inputStream);
				} else {
					throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
				}
				
				Enumeration<Object> keys = prop.keys();
				
				while (keys.hasMoreElements()) {
					String nextKey = keys.nextElement().toString();
					props.put(nextKey, prop.getProperty(nextKey));
				}
			} catch (Exception e) {
				System.out.println("Exception: " + e);
			} 
		}
		
		return props.get(key);
	
	}
}
