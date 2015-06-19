package com.petro.span.client;

import com.google.inject.Inject;
import com.gwtplatform.mvp.client.proxy.Gatekeeper;
import com.petro.span.shared.CurrentUser;


public class LoggedInGatekeeper implements Gatekeeper{

	private final CurrentUser currentUser;

	@Inject
	LoggedInGatekeeper(CurrentUser currentUser) {
		this.currentUser = currentUser;
	}


	@Override
	public boolean canReveal() {
		return currentUser.IsLoggedIn()  && currentUser.getRoles().contains("Role_USER");
	}

}
