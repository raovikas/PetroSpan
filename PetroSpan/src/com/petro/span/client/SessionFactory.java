package com.petro.span.client;

import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;
import com.petro.span.client.place.NameTokens;
import com.petro.span.shared.CurrentUser;

public class SessionFactory {

	public  Timer timeoutTimer = null;
	public final int TIMEOUT = 30*60; // 30 second timeout

	@Inject
	PlaceManager placeManager;
	
	
   @Inject
   CurrentUser currentUsers;
   
//   public static  Boolean isSessionExpired = false;


	public Timer getSessionFactory(){
		if(timeoutTimer == null){
		
			timeoutTimer = new Timer() {

			@Override
			public void run() {
				schedule(TIMEOUT * 1000);
				if(!(History.getToken().contains(NameTokens.login) || History.getToken().trim().length() == 0  || currentUsers.IsLoggedIn() == false)){
					currentUsers.setLoggedIn(false);
					currentUsers.setUsername("");
//					isSessionExpired = true;
				changePlace();
				
				}
			}
		};
		}
		timeoutTimer.schedule(TIMEOUT * 1000);
		return timeoutTimer;

	}




	protected  void changePlace() {
		placeManager.revealPlace(new PlaceRequest.Builder().nameToken(NameTokens.login).build());
		Window.alert("Your Session is timeout");
	}

}
