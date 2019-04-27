package com.dev.myrest.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

	@Value("${app.ver}")
	String version;

	@GetMapping({ "", "/" })
	@ResponseBody
	String main() {
		return "Skeleton Service v" + version;
	}

}
