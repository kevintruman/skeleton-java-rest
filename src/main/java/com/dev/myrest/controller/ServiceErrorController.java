package com.dev.myrest.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceErrorController {

	@GetMapping("service-error")
	Map<String, Object> errorAccess() {
		Map<String, Object> result = new HashMap<>();

		result.put("message", "access error, please contact administrator");

		return result;
	}

}
