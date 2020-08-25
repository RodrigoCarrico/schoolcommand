package br.com.onboard.schoolcommand.utils;

public class PropertiesClass {
	
	public static <T> String getName(T object) {
		return object.getClass().getSimpleName();
	}
	

}
