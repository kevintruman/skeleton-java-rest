package com.dev.myrest.config;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.dev.myrest.model.SidUserRequest;
import com.dev.myrest.model.VwUserMapping;
import com.dev.myrest.repo.SidUserRequestRepo;
import com.dev.myrest.repo.VwUserMappingRepo;
import com.dev.myrest.service.ConstantService;
import com.dev.myrest.service.ServiceTools;

@Component
public class InterceptorConfig implements HandlerInterceptor {

	Logger log = Logger.getLogger(getClass());

	@Autowired
	ServiceTools serviceTools;

	@Autowired
	SidUserRequestRepo sidUserRequestRepo;
	@Autowired
	VwUserMappingRepo vwUserMappingRepo;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		log.info("REQUEST [" + request.getRequestURI() + "] FROM " + request.getRemoteAddr());

		boolean result = false;

		String resultUri = ConstantService.IGNORE_URI.stream().filter(e -> e.equals( request.getRequestURI() ))
				.findFirst().orElse(null);

		if (!request.getRequestURI().equals("") && resultUri == null) {
			result = accessUri(request);
		} else {
			result = true;
		}

		if (!result)
			response.sendRedirect("/service-error");

		return result;
	}

	boolean accessUri(HttpServletRequest request) {

		String message = "";
		boolean result = false;

		// get token
		boolean tokenPresent = false;
		Map<String, Object> resultHeader = null;
		String token = request.getHeader(ConstantService.KEY);

		if (token != null) {
			tokenPresent = true;
			resultHeader = serviceTools.encodeHeader(token);
		} else {
			message = "MISSING KEY HEADER";
		}

		// get user
		boolean userPresent = false;

		if (resultHeader != null && resultHeader.get("username") != null && resultHeader.get("password") != null) {

			SidUserRequest user = sidUserRequestRepo.findOneByUsernameAndPassword(
					resultHeader.get("username").toString(), resultHeader.get("password").toString());

			if (user != null && user.getPassword().equals(resultHeader.get("password").toString())) {
				userPresent = true;
			} else {
				if (tokenPresent)
					message += ", USER NOT FOUND";
				else
					message = "USER NOT FOUND";
			}

		} else {
			if (tokenPresent)
				message += ", INVALID KEY HEADER";
			else
				message = "INVALID KEY HEADER";
		}

		// get uri
		if (userPresent) {

			List<VwUserMapping> resultMapping = vwUserMappingRepo
					.findByUsername(resultHeader.get("username").toString());
			List<VwUserMapping> mapping = resultMapping.stream()
					.filter(e -> e.getPath().startsWith( request.getRequestURI() )).collect(Collectors.toList());

			if (mapping.size() > 0) {
				result = true;
			} else {
				if (userPresent || tokenPresent)
					message += ", ROLE NOT FOUND";
				else
					message = "ROLE NOT FOUND";
			}
		}

		if (!result)
			log.error(message);

		return result;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

}
