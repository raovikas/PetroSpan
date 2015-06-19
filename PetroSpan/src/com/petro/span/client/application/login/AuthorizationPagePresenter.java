package com.petro.span.client.application.login;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.shared.RestDispatch;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.NoGatekeeper;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.petro.span.client.AuthUtil;
import com.petro.span.client.Loader;
import com.petro.span.client.SessionFactory;
import com.petro.span.client.place.NameTokens;
import com.petro.span.client.rest.ILoginService;
import com.petro.span.shared.CurrentUser;
import com.petro.span.shared.LoginModel;
import com.petro.span.shared.UserDto;
import com.petro.span.shared.UserInfo;

public class AuthorizationPagePresenter extends Presenter<AuthorizationPagePresenter.MyView, AuthorizationPagePresenter.MyProxy> implements AuthorizationPageUiHandlers {
	interface MyView extends View , HasUiHandlers<AuthorizationPageUiHandlers> {

		void initialize();

		void displayErroMsg(String result);

		void clearDisplayMsg();
	}


	private final ILoginService loginService;

	@Inject
	private RestDispatch dispatcher;
	private CurrentUser currentUser;

	private final AuthUtil authUtil;

	@Inject
	SessionFactory sessionFactory;

	@Inject
	PlaceManager placeManager;

	@Inject Loader loader;

	String REGISTERED_USER_TABLEID = "10wxEU56AnodDoRGt2jVJPJdHPnHvzD4uH8sFeZ5f" ;
	
	@Inject
	UserInfo user;

	@ProxyStandard
	@NameToken(NameTokens.login)
	@NoGatekeeper
	interface MyProxy extends ProxyPlace<AuthorizationPagePresenter> {
	}

	@Inject
	AuthorizationPagePresenter(
			EventBus eventBus,
			MyView view, 
			MyProxy proxy,
			ILoginService loginService,
			CurrentUser currentUser, AuthUtil authUtil) {
		super(eventBus, view, proxy, RevealType.RootLayout);
		this.loginService = loginService;
		this.currentUser = currentUser;
		this.authUtil = authUtil;
		System.out.println("AuthorizationPagePresenter");
		getView().setUiHandlers(this);
	}

	protected void onBind() {
		super.onBind();
		getView().initialize();
	}

	
	
	
	@Override
	protected void onReveal() {
		super.onReveal();
		getView().clearDisplayMsg();
		
	}
	
//	@Override
//	public void login(final String username, String password) {
//		String url = "https://www.googleapis.com/fusiontables/v2/query?sql=SELECT LoginName , HashedPassword From "+REGISTERED_USER_TABLEID+" Where LoginName = "+username;
//		System.out.println("url "+url);
//		final RequestBuilder builder = new RequestBuilder(RequestBuilder.GET, url);
//
//		try {
//			loader.show();
//			final String token = user.getToken();
//
//			dispatcher.execute(loginService.isTokenExpire(token), new AsyncCallback<Boolean>() {
//
//				@Override
//				public void onFailure(Throwable caught) {
//					caught.printStackTrace();
//					loader.hide();
//				}
//
//				@Override
//				public void onSuccess(final Boolean isTokenExpire) {
//					if(isTokenExpire){
//						authUtil.doAuthentication();
//						new Timer(){
//
//							@Override
//							public void run() {
//								String newtoken= user.getToken();
//								System.out.println("newtoken "+newtoken);
//								builder.setHeader("Authorization", "Bearer " + newtoken);
//								System.out.println("isTokenExpire timer " +isTokenExpire);
//								sendRequest(builder);
//
//							}
//
//
//						}.schedule(3000);
//					}
//					else
//					{
//						builder.setHeader("Authorization", "Bearer " + token);
//						System.out.println("isTokenExpire without timer" +isTokenExpire);
//						sendRequest(builder);
//					}
//
//				}
//			});
//			builder.setHeader("Authorization", "Bearer " + token);
//
//
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}


//
//				dispatcher.execute(loginService.login(username, password), new AsyncCallback<Boolean>() {
//		
//					@Override
//					public void onFailure(Throwable caught) {
//						caught.printStackTrace();
//					}
//		
//					@Override
//					public void onSuccess(Boolean result) {
//						System.out.println("result "+result);
//						currentUser.setLoggedIn(result);
//						if(result){
//							currentUser.setUsername(username);
//							
//							
//							authUtil.doAuthorization();
//							
//							
//		
//		
//						}
//						else
//							Window.alert("Wrong login or password.");
//					}
//				});
//
//
//
//	}



//	private void sendRequest(RequestBuilder builder) {
//		try {
//			builder.sendRequest(null, new RequestCallback() {
//
//				@Override
//				public void onResponseReceived(Request request, Response response) {
//					if(200 == response.getStatusCode()){
//						String  responseText =  response.getText();
//						System.out.println("responseText  "+responseText);
//						loader.hide();
//
//					}else
//
//						System.out.println("error responseText  "+ response.getText());
//				}
//
//				@Override
//				public void onError(Request request, Throwable exception) {
//					exception.printStackTrace();
//					loader.hide();
//				}
//			});
//		} catch (RequestException e) {
//			e.printStackTrace();
//			loader.hide();
//		}		
//	}

	@Override
	public void login(final UserDto userDto) {
		
		loader.show();
		
		userDto.setAccessToken(user.getToken());
		
		dispatcher.execute(loginService.login(userDto), new AsyncCallback< LoginModel>() {

			@Override
			public void onFailure(Throwable caught) {
				caught.printStackTrace();
				loader.hide();
			}

			@Override
			public void onSuccess( LoginModel result) {
//				if(result.get("ReturnMsg").size()==1 &&  result.get("ReturnMsg").get(0).equals("200")){
				if(result.getReturnMsgList().size()==1 &&  result.getReturnMsgList().get(0).equals("200")){
					currentUser.setLoggedIn(true);
					currentUser.setRoles(result.getRole_list());
					currentUser.setUsername(userDto.getLoginName());
					currentUser.setTabModel(result.getModel());
					currentUser.setFiltersModel(result.getFiltersModel());
					authUtil.doAuthorization();
				}
				else{
					
					loader.hide();
				getView().displayErroMsg(result.getReturnMsgList().get(0));
				}
			}
		});
		
		
	}


}