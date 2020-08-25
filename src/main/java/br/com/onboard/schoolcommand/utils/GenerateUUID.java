package br.com.onboard.schoolcommand.utils;

import java.util.UUID;

public class GenerateUUID {
	public static String generate() {
		return UUID.randomUUID().toString().replace("-", "");
	}

}
