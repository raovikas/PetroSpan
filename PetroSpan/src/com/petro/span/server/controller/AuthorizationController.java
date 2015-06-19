package com.petro.span.server.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;



import com.petro.span.server.spring.service.AuthorizationService;

import static org.springframework.web.bind.annotation.RequestMethod.GET;


@Controller
@RequestMapping("/rest/authorize")
public class AuthorizationController {

	@Autowired
	AuthorizationService authService;

	@RequestMapping(method = GET)
	@ResponseBody
	String getAccessToken() throws IOException, GeneralSecurityException {
		return authService.getAccessToken();
	}
}
