package com.petro.span.server.spring.service;

import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;

import org.springframework.stereotype.Service;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.bigquery.BigqueryScopes;
import com.google.api.services.fusiontables.Fusiontables;
import com.google.api.services.fusiontables.FusiontablesScopes;

@Service
public class AuthorizationServiceImpl implements AuthorizationService {

	String emailAddress = "87853623787-4gf56qm45lael9r45enic2gfs7fsi9fv@developer.gserviceaccount.com";
	JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
	HttpTransport httpTransport = new NetHttpTransport();

	@Override
	public String getAccessToken() throws IOException, GeneralSecurityException{

		
		
		
		GoogleCredential credential = createCredential();
		

		if(credential.getAccessToken()==null)
		credential.refreshToken();

		String token = credential.getAccessToken();
		System.out.println("token "+token);
		return token;
	}
	
	
	
	private GoogleCredential createCredential() throws IOException, GeneralSecurityException{
		ArrayList<String> scopes = new ArrayList<String>();
		scopes.add(FusiontablesScopes.FUSIONTABLES);
		scopes.add(BigqueryScopes.BIGQUERY);
		
		GoogleCredential credential  = new GoogleCredential.Builder()
		.setTransport(httpTransport)
		.setJsonFactory(JSON_FACTORY)
		.setServiceAccountId(emailAddress)
		.setServiceAccountPrivateKeyFromP12File(new File("PZTest.p12"))
//		.setServiceAccountScopes(Collections.singleton(FusiontablesScopes.FUSIONTABLES))
	    .setServiceAccountScopes(scopes)
		.build();
		
		return credential;
	}



	/**
	 * Creates an authorized FusionTable client service using the OAuth 2.0 protocol
	 * @param token
	 * @return  an authorized FusionTable client
	 * @throws Exception IOException
	 */
	
	@Override
	public Fusiontables createAuthorizedClient(final String token) throws IOException {
		System.out.println("inside createAuthorizedClient "+token);
//		GoogleCredential credential = new GoogleCredential().setAccessToken(token.replace("\"", ""));
//		GoogleCredential credential;
		Fusiontables fusionTable = null;
		try {
			 fusionTable = 	new Fusiontables.Builder(httpTransport,JSON_FACTORY, createCredential()).setApplicationName("PZTest").build();
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		}
		return fusionTable;
		

	}

}
