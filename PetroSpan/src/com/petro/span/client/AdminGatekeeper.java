package com.petro.span.client;

import com.google.inject.Inject;
import com.gwtplatform.mvp.client.proxy.Gatekeeper;
import com.petro.span.shared.CurrentUser;

public class AdminGatekeeper implements Gatekeeper{

	
	private final  CurrentUser currentUser;
	
	@Inject
	AdminGatekeeper(CurrentUser currentUser){
		this.currentUser = currentUser;	
	}
	
	
	@Override
	public boolean canReveal() {
//		System.out.println("canReveal is logged in "+currentUser.IsLoggedIn() +" currentUser.getRoles()    "+currentUser.getRoles().equals("Role_ADMIN"));
		return currentUser.IsLoggedIn() && currentUser.getRoles().contains("Role_ADMIN");
	}

}
