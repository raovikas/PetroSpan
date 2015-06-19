package com.petro.span.client.application.login;

import javax.inject.Inject;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.InlineHyperlink;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.petro.span.client.place.NameTokens;
import com.petro.span.client.resources.Resources;
import com.petro.span.shared.UserDto;

class AuthorizationPageView extends ViewWithUiHandlers<AuthorizationPageUiHandlers> implements AuthorizationPagePresenter.MyView {
	interface Binder extends UiBinder<Widget, AuthorizationPageView> {
	}



	@UiField TextBox userName;

	@UiField PasswordTextBox password ;
	@UiField InlineHyperlink inlineHyperlink;

	@UiField LayoutPanel   loginpageLayout ;
	@UiField LayoutPanel   loginFormLayout ;

	@UiField  Button loginButton ;

	@UiField SimpleLayoutPanel   messageDisplayPanel;

	@Inject
	Resources res;


	//	@UiField InlineHTML   sessionExpireLabel ;


	@Inject
	PlaceManager manager ;

	@Inject
	AuthorizationPageView(Binder uiBinder) {
		initWidget(uiBinder.createAndBindUi(this));

		userName.getElement().setAttribute("placeholder", "Username");
		password.getElement().setAttribute("placeholder", "Password");

		inlineHyperlink.setTargetHistoryToken(NameTokens.registration);



	}


	@UiHandler("loginButton")
	void onloginClicked(ClickEvent event){
		processLogin();
	}

	@UiHandler("password")
	void onPasswordKeyUp(KeyUpEvent event) {
		if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
			processLogin();
		}
	}


	private void processLogin() {
		UserDto userDto =  new UserDto();
		userDto.setLoginName(userName.getValue());
		userDto.setPassword(password.getValue());
		getUiHandlers().login(userDto);

		userName.setText("");
		password.setText("");
		//		loginPanel.reset();

	}


	@Override
	public void initialize() {
		loginpageLayout.animate(1000);
		loginFormLayout.animate(2000);

	}






	@Override
	public void displayErroMsg(String errorMsg) {
		try {
			//				inlineError.setText(errorMsg);
			InlineLabel errorLabel = new InlineLabel(errorMsg);
			errorLabel.setStyleName(res.style().failure());

			messageDisplayPanel.setWidget(errorLabel);
			//			InlineLabel errorLabel = new InlineLabel(errorMsg);
			//			errorLabel.setStyleName(res.style().failure());
			//			messageDisplayPanel.setWidget(errorLabel);
			//			Window.alert("errorMsg");

		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	@Override
	public void clearDisplayMsg() {
		messageDisplayPanel.clear();		
	}


	//	private void addPreviewHandler() {
	//		Event.addNativePreviewHandler(new NativePreviewHandler() {
	//
	//			@Override
	//			public void onPreviewNativeEvent(NativePreviewEvent event) {
	//				
	//			Timer timer = 	sessionFactory.getSessionFactory();
	//				
	//				if(timer.isRunning()){
	//					System.out.println("is running true");
	//					timer.cancel();
	////					cancelTimer();
	//					timer.schedule(sessionFactory.TIMEOUT * 1000);
	//					loginpageLayout.setWidgetTopHeight(sessionExpireLabel, 0.0, Unit.PCT, 0.0,  Unit.PCT);
	//					loginpageLayout.setWidgetLeftWidth(sessionExpireLabel, 0.0,  Unit.PCT, 0.0,  Unit.PCT);
	//					loginpageLayout.animate(500);
	//				}else
	//				{
	//					Window.alert("timer is expired");
	//					loginpageLayout.setWidgetTopHeight(sessionExpireLabel, 10.0, Unit.PCT, 3.0,  Unit.PCT);
	//					loginpageLayout.setWidgetLeftWidth(sessionExpireLabel, 45.0,  Unit.PCT, 26.0,  Unit.PCT);
	//					loginpageLayout.animate(500);
	//				}
	//				
	//				
	//				
	//
	//				//					else{//else condition never run we need to know
	//				//					if(!(Window.Location.getHash().contains("login"))){
	//
	//				//						System.out.println("is running false");
	//				//						placeManager.revealPlace(new PlaceRequest.Builder().nameToken(unauthorizedPlace).build());
	//				//						SessionFactory.getSessionFactory(placeManager).schedule(SessionFactory.TIMEOUT * 1000);
	//				//					}
	//				//					System.out.println("unauthorizedPlace "+unauthorizedPlace);
	//				//				System.out.println("hash  "+Window.Location.getHash());
	//				//			}
	//			}
	//		});
	//	}
	//
	//
	//	// Stop the timeout timer if it is running
	//	protected void cancelTimer() {
	//		timeoutTimer = sessionFactory.getSessionFactory();
	//		if (timeoutTimer != null) {
	//			timeoutTimer.cancel();
	//			timeoutTimer  = null;
	//		
	//		}
	//
	//	}


}