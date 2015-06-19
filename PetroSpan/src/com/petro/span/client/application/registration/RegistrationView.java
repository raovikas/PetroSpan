package com.petro.span.client.application.registration;

import java.util.Set;

import javax.validation.ConstraintViolation;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.BlurEvent;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.FocusEvent;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.petro.span.client.ClientValidation;
import com.petro.span.client.application.ui.SignUpMessageWidget;
import com.petro.span.client.resources.Resources;
import com.petro.span.shared.RegistrationModel;
import com.petro.span.shared.UserInfo;

class RegistrationView extends ViewWithUiHandlers<RegistrationUiHandlers> implements RegistrationPresenter.MyView {
	interface Binder extends UiBinder<Widget, RegistrationView> {
	}

	@UiField LayoutPanel   registrationPageLayout ;

	@UiField TextBox userName;

	@UiField TextBox  emailAddress ;

	@UiField TextBox  loginName  ;

	@UiField PasswordTextBox   password;

	@UiField PasswordTextBox    confirmPassword ;

	@UiField LayoutPanel  registrationFormLayout ;

	@UiField Button  signUpButton ;

	@UiField RadioButton  maleRadio;

	@UiField RadioButton  femaleRadio;

	@UiField SimpleLayoutPanel   messageDisplayPanel;

	SignUpMessageWidget signUpMsgWidget;

	ClientValidation clientValidation;

	@UiField  HTMLPanel  passInstructionPanel ;

	@UiField  HTMLPanel mailMsgLayoutPanel;

	@UiField  HTMLPanel  loginNameMsgLayoutPanel;

	@Inject
	Resources resource;

	UserInfo user;

	private Boolean isEmailExist ;

	private Boolean isLoginNameExist ;

	@Inject
	RegistrationView(Binder uiBinder,UserInfo user,ClientValidation clientValidation) {
		initWidget(uiBinder.createAndBindUi(this));
		this.clientValidation = clientValidation;
		this.user = user;
		userName.getElement().setAttribute("placeholder", "Your Name");
		emailAddress.getElement().setAttribute("placeholder", "Your E-mail Address");
		loginName.getElement().setAttribute("placeholder", "Choose a Login Name");
		password.getElement().setAttribute("placeholder", "Choose a Password");
		confirmPassword.getElement().setAttribute("placeholder", "Confirm your Password");
		signUpMsgWidget = new SignUpMessageWidget();
		//		maleRadio.setValue(true);

		System.out.println("RegistrationView");
	}



//	@UiHandler("loginName")
//	void onLoginNameBlur(BlurEvent event) {
//		if(loginName.getValue().trim().length()>0)
//			getUiHandlers().isLoginNameExist(user.getToken(),loginName.getValue());
//	}
	
	
	@UiHandler("loginName")
	void OnLoginNameKeyUp(KeyUpEvent event){
		if(loginName.getValue().trim().length()>2)
			getUiHandlers().isLoginNameExist(user.getToken(),loginName.getValue());
	}
	
	
	@UiHandler("emailAddress")
	void onEmailAddressKeyUp(KeyUpEvent event){
		if(emailAddress.getValue().trim().length()>0 && emailAddress.getValue().contains("@"))
			getUiHandlers().isEmailAlreadyExist(user.getToken() ,emailAddress.getValue());
	}


//	@UiHandler("emailAddress")
//	void onEmailAddressBlur(BlurEvent event){
//		if(emailAddress.getValue().trim().length()>0 && emailAddress.getValue().contains("@gmail.com"))
//			getUiHandlers().isEmailAlreadyExist(user.getToken() ,emailAddress.getValue());
//	}

	@UiHandler("password")
	void onPasswordFocus(FocusEvent event){

		//		registrationPageLayout.setWidgetLeftWidth(passInstructionPanel, 71.0, Unit.PCT, 20.0, Unit.PCT);
		//		registrationPageLayout.setWidgetTopHeight(passInstructionPanel, 52.0, Unit.PCT, 26.0, Unit.PCT);
		//		registrationPageLayout.animate(900);


		registrationPageLayout.setWidgetLeftWidth(passInstructionPanel, 71.0, Unit.PCT, 20.0, Unit.PCT);
		registrationPageLayout.setWidgetTopHeight(passInstructionPanel, 52.0, Unit.PCT, 00.0, Unit.PCT);

		registrationPageLayout.forceLayout();

		registrationPageLayout.setWidgetLeftWidth(passInstructionPanel, 71.0, Unit.PCT, 20.0, Unit.PCT);
		registrationPageLayout.setWidgetTopHeight(passInstructionPanel, 52.0, Unit.PCT, 26.0, Unit.PCT);
		registrationPageLayout.animate(700);

	}


	@UiHandler("password")
	void onPasswordBlur(BlurEvent event){
		//		registrationPageLayout.setWidgetLeftWidth(passInstructionPanel, 0.0, Unit.PCT, 00.0, Unit.PCT);
		//		registrationPageLayout.setWidgetTopHeight(passInstructionPanel, 0.0, Unit.PCT, 0.0, Unit.PCT);
		//		registrationPageLayout.animate(900);


		registrationPageLayout.setWidgetLeftWidth(passInstructionPanel, 71.0, Unit.PCT, 20.0, Unit.PCT);
		registrationPageLayout.setWidgetTopHeight(passInstructionPanel, 52.0, Unit.PCT, 26.0, Unit.PCT);

		registrationPageLayout.forceLayout();

		//		registrationPageLayout.setWidgetLeftWidth(passInstructionPanel, 0.0, Unit.PCT, 00.0, Unit.PCT);
		registrationPageLayout.setWidgetTopHeight(passInstructionPanel, 52.0, Unit.PCT, 00.0, Unit.PCT);
		registrationPageLayout.animate(700);





	}


	@UiHandler("signUpButton")
	void onSignUpClicked(ClickEvent event){

//		if(isEmailExist || isLoginNameExist){
//			new Timer() {
//
//				@Override
//				public void run() {
//
//					signUpProcessing();
//				}
//			}.schedule(3000);
//		}else

			signUpProcessing();



	}



	private void signUpProcessing() {
		RegistrationModel model = new RegistrationModel();

		model.setEmailAddress(emailAddress.getValue());
		model.setUserName(userName.getValue());
		model.setLoginName(loginName.getValue());
		model.setHashedPassword(password.getValue());
		if(maleRadio.getValue()){

			model.setGender("Male");
		}else if(femaleRadio.getValue()){
			model.setGender("Female");
		}
		model.setAccessToken(user.getToken());
		model.setConfirmPassword(confirmPassword.getValue());

		Set<ConstraintViolation<RegistrationModel>> violations = clientValidation.getConstraintViolations(model);
		if (!violations.isEmpty()) {

			System.out.println("violations size "+ violations.size());

			InlineLabel	errorMsg = 	new InlineLabel(clientValidation.getPrimaryMessage());
			errorMsg.setStyleName(resource.style().failure());

			messageDisplayPanel.setWidget(errorMsg);
		}
		else  if(!(model.getConfirmPassword().equals(model.getHashedPassword()))){
			InlineLabel	errorMsg = 	new InlineLabel("Password does not match");
			errorMsg.setStyleName(resource.style().failure());
			messageDisplayPanel.setWidget(errorMsg);
		}

		else if(!isEmailExist && !isLoginNameExist){

			getUiHandlers().registerUserProcessing(model,user);
			userName.setValue("");
			emailAddress.setValue("");
			loginName.setValue("");
			password.setValue("");
			confirmPassword.setValue("");

			maleRadio.setValue(false);
			femaleRadio.setValue(false);
		}

	}



	@Override
	public void showMessage() {
		messageDisplayPanel.setWidget(signUpMsgWidget);
	}



	@Override
	public void clearDisplayMsg() {
		messageDisplayPanel.clear();
	}



	@Override
	public void setLayoutForEmail(Boolean isEmailExist) {
		this.isEmailExist = isEmailExist;
		if(isEmailExist){

			registrationPageLayout.setWidgetLeftWidth(mailMsgLayoutPanel, 71.0, Unit.PCT, 00.0,  Unit.PCT);		
			registrationPageLayout.setWidgetTopHeight(mailMsgLayoutPanel, 36.0,  Unit.PCT, 3.0,  Unit.PCT);

			registrationPageLayout.forceLayout();

			registrationPageLayout.setWidgetLeftWidth(mailMsgLayoutPanel, 71.0, Unit.PCT, 20.0,  Unit.PCT);		
			registrationPageLayout.setWidgetTopHeight(mailMsgLayoutPanel, 36.0,  Unit.PCT, 3.0,  Unit.PCT);

		}else{

			//			registrationPageLayout.setWidgetLeftWidth(mailMsgLayoutPanel, 71.0, Unit.PCT, 20.0,  Unit.PCT);		
			//			registrationPageLayout.setWidgetTopHeight(mailMsgLayoutPanel, 36.0,  Unit.PCT, 3.0,  Unit.PCT);
			//
			//			registrationPageLayout.forceLayout();

			registrationPageLayout.setWidgetLeftWidth(mailMsgLayoutPanel, 71.0, Unit.PCT, 00.0,  Unit.PCT);		
			registrationPageLayout.setWidgetTopHeight(mailMsgLayoutPanel, 36.0,  Unit.PCT, 3.0,  Unit.PCT);

		}

		registrationPageLayout.animate(900);
	}



	@Override
	public void setLayoutForLoginName(Boolean isLoginNameExist) {
		this.isLoginNameExist = isLoginNameExist;
		if(isLoginNameExist){

			registrationPageLayout.setWidgetLeftWidth(loginNameMsgLayoutPanel, 71.0, Unit.PCT, 00.0,  Unit.PCT);		
			registrationPageLayout.setWidgetTopHeight(loginNameMsgLayoutPanel, 46.0,  Unit.PCT, 3.0,  Unit.PCT);

			registrationPageLayout.forceLayout();

			registrationPageLayout.setWidgetLeftWidth(loginNameMsgLayoutPanel, 71.0, Unit.PCT, 20.0,  Unit.PCT);		
			registrationPageLayout.setWidgetTopHeight(loginNameMsgLayoutPanel, 46.0,  Unit.PCT, 3.0,  Unit.PCT);

		}else{


			//			registrationPageLayout.setWidgetLeftWidth(loginNameMsgLayoutPanel, 71.0, Unit.PCT, 20.0,  Unit.PCT);		
			//			registrationPageLayout.setWidgetTopHeight(loginNameMsgLayoutPanel, 46.0,  Unit.PCT, 3.0,  Unit.PCT);
			//
			//			registrationPageLayout.forceLayout();

			registrationPageLayout.setWidgetLeftWidth(loginNameMsgLayoutPanel, 71.0, Unit.PCT, 00.0,  Unit.PCT);		
			registrationPageLayout.setWidgetTopHeight(loginNameMsgLayoutPanel, 46.0,  Unit.PCT, 3.0,  Unit.PCT);
		}

		registrationPageLayout.animate(900);
	}




}