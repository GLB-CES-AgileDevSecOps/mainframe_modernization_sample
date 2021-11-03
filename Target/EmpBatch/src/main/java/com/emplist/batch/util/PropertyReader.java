package com.emplist.batch.util;

import java.util.Properties;
import java.util.ResourceBundle;



/**
 * @author AP5044237
 *
 */
public class PropertyReader {

	private static String appenv = "dev";
	
	private static ResourceBundle appResourceBundle = null;
	
	private static ResourceBundle globalErrorMessageBundle = null;
	
	private static final String APP_RESOURCES_PROP= "coboltojavabatch";//ApplicationResources.properties
	
	private static final String GLOBAL_ERROR_MESSAGES_PROP = "global_error_messages";//global_error_messages.properties
	

	static {
		loadAppResourceBundle(APP_RESOURCES_PROP);
		loadErrorMessageBundle(GLOBAL_ERROR_MESSAGES_PROP);
		
	}
	
	/**
	 * 
	 */
	public PropertyReader() {
		// TODO Auto-generated constructor stub
	}

	
	
	
	private static void loadAppResourceBundle(String propertyFileName) {
		try {
			Properties props = new Properties();
			props.load(PropertyReader.class.getClassLoader().getResourceAsStream("/META-INF/spring.batch.properties"));
			appenv = props.getProperty("application.env");
			appResourceBundle = ResourceBundle.getBundle(propertyFileName);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void loadErrorMessageBundle(String propertyFileName) {
		try {
			globalErrorMessageBundle = ResourceBundle.getBundle(propertyFileName);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public static String getAppPropertyValue(String propKey){
		return appResourceBundle.getString(propKey);
	}
	
	public static String getErrorMessagePropertyValue(String propKey){
		return globalErrorMessageBundle.getString(propKey);
	}
	
 
}
