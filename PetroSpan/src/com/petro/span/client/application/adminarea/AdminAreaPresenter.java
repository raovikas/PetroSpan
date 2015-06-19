package com.petro.span.client.application.adminarea;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.shared.RestDispatch;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.UseGatekeeper;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.petro.span.client.AdminGatekeeper;
import com.petro.span.client.Loader;
import com.petro.span.client.application.ApplicationPresenter;
import com.petro.span.client.application.event.AdminGridSelectionEvent;
import com.petro.span.client.place.NameTokens;
import com.petro.span.client.rest.RegistrationRest;
import com.petro.span.shared.AdminMasterModel;
import com.petro.span.shared.RegisteredUser;
import com.petro.span.shared.UserInfo;

public class AdminAreaPresenter extends Presenter<AdminAreaPresenter.MyView, AdminAreaPresenter.MyProxy> implements AdminAreaUiHandlers {
	interface MyView extends View , HasUiHandlers<AdminAreaUiHandlers> {

		void createGrid();

		void displayRegisteredUserDetails(List<RegisteredUser> result);

		void addHandlers();

		void displayMessage(String result);

		void clear();
	}


	//	private final HeaderPresenter header;
	//	private final TabBarPresenter tabBar;
	//	
	//
	//	public static final  Object Header_Slot = new Object();
	//	public static final Object  TabBar_Slot = new Object();

	@Inject
	RegistrationRest registrationRest;

	@Inject
	RestDispatch dispatchRest;


	@Inject
	UserInfo user;
	
	@Inject
	Loader loader;


	@ProxyCodeSplit
	@NameToken(NameTokens.adminArea)
	@UseGatekeeper(AdminGatekeeper.class)
	interface MyProxy extends ProxyPlace<AdminAreaPresenter> {
	}

	@Inject
	AdminAreaPresenter(
			EventBus eventBus,
			MyView view, 
			MyProxy proxy) {
		super(eventBus, view, proxy,ApplicationPresenter.SLOT_SetMainContent);
		//		this.header = header;
		//		this.tabBar = tabBar;
		getView().setUiHandlers(this);
	}

	protected void onBind() {
		super.onBind();
		getView().addHandlers();
		//		getView().createGrid();
		//		setInSlot(Header_Slot, header);
		//		setInSlot(TabBar_Slot, tabBar);
	}


	@Override
	protected void onReveal() {
		super.onReveal();
		getRegisteredUserDetail(user.getToken());
	}



	private void getRegisteredUserDetail(String token) {
		loader.show();
		getView().clear();
		dispatchRest.execute(registrationRest.getRegisteredUserDetails(token), new AsyncCallback<List<RegisteredUser>>() {

			@Override
			public void onFailure(Throwable caught) {
				caught.printStackTrace();
				loader.hide();
			}

			@Override
			public void onSuccess(List<RegisteredUser> result) {
				if(result.size()>0){
					getView().displayRegisteredUserDetails(result);
					loader.hide();
				}

			}
		});
	}

	@Override
	public void freezeUsersAccessRights(AdminMasterModel masterModel,
			String token) {
		try {
			loader.show();
			dispatchRest.execute(registrationRest.freezeUsersAccessRights(masterModel, token), new AsyncCallback<String>() {

				@Override
				public void onFailure(Throwable caught) {
					caught.printStackTrace();
					loader.hide();
				}

				@Override
				public void onSuccess(String result) {
					getView().displayMessage(result);;
					getRegisteredUserDetail(user.getToken());
					loader.hide();
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			loader.hide();
		}
	}
	
	

	@Override
	public void fireGridRecordSelectionEvent(Object object) {
		AdminGridSelectionEvent.fire(this, object);
	}

}