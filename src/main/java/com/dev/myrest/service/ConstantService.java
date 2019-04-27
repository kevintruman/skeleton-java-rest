package com.dev.myrest.service;

import java.util.Arrays;
import java.util.List;

public class ConstantService {

	public static final String KEY = "api-key";
	public static final List<String> IGNORE_URI = Arrays.asList("/", "/api/login", "/service-error", "/error",
			"/api/logout");

}
