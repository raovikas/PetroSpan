package com.petro.span.client.application.registration;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.shared.RestDispatch;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.NoGatekeeper;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.petro.span.client.BPSSUtils;
import com.petro.span.client.Loader;
//import com.petro.span.client.BPSSUtils;
//import com.petro.span.client.Loader;
import com.petro.span.client.place.NameTokens;
import com.petro.span.client.rest.AuthorizationRest;
import com.petro.span.client.rest.RegistrationRest;
import com.petro.span.shared.RegistrationModel;
import com.petro.span.shared.UserInfo;
public class RegistrationPresenter extends Presenter<RegistrationPresenter.MyView, RegistrationPresenter.MyProxy> implements RegistrationUiHandlers {
	
	
    interface MyView extends View , HasUiHandlers<RegistrationUiHandlers> {

		void showMessage();

		void clearDisplayMsg();

		void setLayoutForEmail(Boolean isEmailExist);

		void setLayoutForLoginName(Boolean isLoginNameExist);
    }
   
    @NameToken(NameTokens.registration)
    @ProxyCodeSplit 
    @NoGatekeeper
    interface MyProxy extends ProxyPlace<RegistrationPresenter> {
    }

    @Inject
    BPSSUtils bpssUtil ;
//    
    @Inject
    Loader loader;
    
    @Inject
    RegistrationRest registrationRest;
    
    @Inject
    RestDispatch dispatch;
    
    @Inject
    private  AuthorizationRest authRest;
    
    
    @Inject
    UserInfo user;
    
    @Inject
    RegistrationPresenter(
            EventBus eventBus,
            MyView view, 
            MyProxy proxy) {
        super(eventBus, view, proxy, RevealType.RootLayout);
        
        getView().setUiHandlers(this);
        
        System.out.println("RegistrationPresenter");
    }
    
    
    protected void onBind() {
        super.onBind();
        dispatch.execute(authRest.getAccessToken(), new AsyncCallback<String>() {

			@Override
			public void onFailure(Throwable caught) {
				caught.printStackTrace();
			}

			@Override
			public void onSuccess(String result) {
				user.setToken(result);
			}
		});
    }

	
    @Override
    protected void onReveal() {
    	super.onReveal();
    	getView().clearDisplayMsg();
    	
    }
	

	@Override
	public void registerUserProcessing(RegistrationModel model,final UserInfo user) {
		try {
			loader.show();
			dispatch.execute(registrationRest.saveData(model), new AsyncCallback<String>() {

				@Override
				public void onFailure(Throwable caught) {
					caught.printStackTrace();
					loader.hide();
				}

				@Override
				public void onSuccess(String result) {
//					Window.alert(result);
					user.setToken(result.trim());
					getView().showMessage();
					loader.hide();
				}
			});
			
		} catch (Exception e) {
			e.printStackTrace();
			loader.hide();
		}
		
	}
	
	

	
	@Override
	public void isEmailAlreadyExist(String token,String email) {
		 try {
			 System.out.println("TOKEN "+token);
			 
			
				dispatch.execute(registrationRest.isEmailAlreadyExist(token, email), new AsyncCallback<Boolean>() {

					@Override
					public void onFailure(Throwable caught) {
						caught.printStackTrace();
					}

					@Override
					public void onSuccess(Boolean isEmailExist) {
						getView().setLayoutForEmail(isEmailExist);
						System.out.println("result "+isEmailExist);
					}
				});
			} catch (Exception e) {
				e.printStackTrace();
			}			
	}


	@Override
	public void isLoginNameExist(String token, String loginName) {
		try {
			 System.out.println("TOKEN "+token);
			 
			 dispatch.execute(registrationRest.isLoginNameExist(token, loginName), new AsyncCallback<Boolean>() {

				@Override
				public void onFailure(Throwable caught) {
					caught.printStackTrace();
				}

				@Override
				public void onSuccess(Boolean isLoginNameExist) {
					getView().setLayoutForLoginName(isLoginNameExist);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
    
}