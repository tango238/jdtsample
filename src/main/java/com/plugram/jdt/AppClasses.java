package com.plugram.jdt;

import java.util.HashMap;
import java.util.Map;

public class AppClasses {

	private static Map<String, byte[]> classes = new HashMap<String, byte[]>();
	
	public static byte[] getClassBytes(String className){
		return classes.get(className);
	}
	
	public static void storeCompiledClass(String className, byte[] bytes){
		classes.put(className, bytes);
	}
}
