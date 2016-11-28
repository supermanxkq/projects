package com.paySystem.ic.utils;

import java.io.IOException;
import java.util.Properties;

public class ReadInit {
	private static Properties properties = new Properties();
	static{
		try {
			properties.load(ReadInit.class.getClassLoader().getResourceAsStream("init.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String read(String key){		
		return (String)properties.get(key);
	}
}
