package com.petro.span.server.spring.service;

import java.io.IOException;
import java.security.GeneralSecurityException;

import com.google.api.services.fusiontables.Fusiontables;

public interface AuthorizationService {

	String getAccessToken() throws IOException, GeneralSecurityException;

	Fusiontables createAuthorizedClient(String accessToken) throws IOException;

}
