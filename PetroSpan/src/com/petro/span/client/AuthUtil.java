package com.petro.span.client;


import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Event.NativePreviewEvent;
import com.google.gwt.user.client.Event.NativePreviewHandler;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.rest.shared.RestDispatch;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest.Builder;
import com.petro.span.client.place.NameTokens;
import com.petro.span.client.place.ParameterTokens;
import com.petro.span.client.rest.AuthorizationRest;
import com.petro.span.client.rest.ILoginService;
import com.petro.span.shared.UserInfo;

/**
 * this is the Util class which have various methods implemented for the 
 * purpose of Google Authorization
 * and for accessing Google API's using Oauth2
 * 
 * @author vikas.yadav
 * 
 * 
 */
public class AuthUtil {



	@Inject
	PlaceManager placeManager;

	@Inject
	private RestDispatch dispatcher;
	
	private Timer timeoutTimer = null;




	private final ILoginService loginService;
	private final UserInfo user;
	private final Loader loader ; 

	private final AuthorizationRest authRest;
	
	@Inject
	private SessionFactory sessionFactory;
	
	@Inject
	AuthUtil(ILoginService loginService, UserInfo user ,Loader loader ,AuthorizationRest authRest){
		this.loginService = loginService;
		this.user = user;
		this.loader = loader;
		this.authRest = authRest;
		addPreviewHandler();
	
	}


	
	public void doAuthorization() {
		System.out.println("login user.getToken() "+user.getToken());
		try {
			if(user.getToken() == null)
			dispatcher.execute(authRest.getAccessToken(), new AsyncCallback<String>() {

				@Override
				public void onFailure(Throwable caught) {
					System.out.println("onFailure");
					caught.printStackTrace();
					loader.hide();
				}

				@Override
				public void onSuccess(String result) {
					System.out.println(" user token in doAuthorization result "+result);
					user.setToken(result);
					changePlace();
					loader.hide();
					
					
							
				}
			});
			else{
				changePlace();
				loader.hide();
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			loader.hide();
		}
	}
	
	

	



	protected void changePlace() {
		String token = placeManager
				.getCurrentPlaceRequest()
				.getParameter(ParameterTokens.REDIRECT, NameTokens.getOnLoginDefaultPage());

				
				PlaceRequest placeRequest = new Builder().nameToken(token).build();

				placeManager.revealPlace(placeRequest);			
	}



	boolean isTokenExpire;
	/**
	 * 
	 * @param token is access token to access private data
	 * @param builder 
	 * @param authUtil 
	 * @return 
	 * @return the status of token is expire or not
	 */

	/**
	 * 
	 * @param token is access token to access private data
	 * @return the status of token is expire or not
	 */
	public Boolean checkTokenStatus(String token) {
		try {
			dispatcher.execute(loginService.isTokenExpire(token), new AsyncCallback<Boolean>() {

				@Override
				public void onFailure(Throwable caught) {
					caught.printStackTrace();				
				}

				@Override
				public void onSuccess(Boolean result) {
					isTokenExpire = result;
					
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isTokenExpire;

	}



	
	private void addPreviewHandler() {
		Event.addNativePreviewHandler(new NativePreviewHandler() {

			@Override
			public void onPreviewNativeEvent(NativePreviewEvent event) {
				if(sessionFactory.getSessionFactory().isRunning()){
					cancelTimer();
					sessionFactory.getSessionFactory().schedule(sessionFactory.TIMEOUT * 1000);
				}

			
			}
		});
	}


	// Stop the timeout timer if it is running
	protected void cancelTimer() {
		timeoutTimer = sessionFactory.getSessionFactory();
		if (timeoutTimer != null) {
			timeoutTimer.cancel();
			timeoutTimer  = null;
		
		}

	}

}	


