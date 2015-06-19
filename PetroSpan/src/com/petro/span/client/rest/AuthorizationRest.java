package com.petro.span.client.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import com.gwtplatform.dispatch.rest.shared.RestAction;

@Path("/authorize")
public interface AuthorizationRest {

	@GET
	RestAction<String> getAccessToken();
}
