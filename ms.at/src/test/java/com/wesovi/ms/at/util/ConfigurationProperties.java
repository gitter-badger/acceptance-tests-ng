package com.wesovi.ms.at.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ConfigurationProperties {

	private static Map<String,String> environmentMap = new HashMap<>();
	
	private static ConfigurationProperties instance = null;
	
	private final Properties properties = new Properties();
	
	static{
		environmentMap.put("local","/environment/local.properties");
		environmentMap.put("docker","environment/docker.properties");
	}
	
	private ConfigurationProperties() throws IOException{
		System.out.println("Environment: "+System.getProperty("environment"));
		String propertiesFilePath = environmentMap.get(System.getProperty("environment"));
		System.out.println(propertiesFilePath);
		properties.load(this.getClass().getResourceAsStream(propertiesFilePath));
	}
	
	public static ConfigurationProperties getInstance(){
		if(instance==null){
			try {
				instance=new ConfigurationProperties();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return instance;
	}
	
	@SuppressWarnings("unchecked")
	public <T>T getProperty(String key,Class<T> clazz){
		Object value = properties.get(key);
		if(value!=null){
			return clazz.cast(value);
		}
		return null;
	}
	
	
}