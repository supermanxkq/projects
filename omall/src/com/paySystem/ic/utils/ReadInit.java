package com.paySystem.ic.utils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

public class ReadInit {
	private static Properties properties = new Properties();
	static{
		try {
			properties.load(new InputStreamReader(ReadInit.class.getClassLoader().getResourceAsStream("init.properties"),"UTF-8"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String read(String key){		
		return (String)properties.get(key);
	}
}
