package com.petro.span.client.application.commercial;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.UseGatekeeper;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.petro.span.client.PrivilagedGatekeeper;
import com.petro.span.client.application.ApplicationPresenter;
import com.petro.span.client.place.NameTokens;
import com.petro.span.shared.CurrentUser;

public class CommercialTabPresenter extends Presenter<CommercialTabPresenter.MyView, CommercialTabPresenter.MyProxy> implements CommercialTabUiHandlers {
	interface MyView extends View , HasUiHandlers<CommercialTabUiHandlers> {
	}

	
	@Inject
	CurrentUser currentUser;
	
	@Inject
	PlaceManager placeManager;

	@NameToken(NameTokens.commercial)
	@ProxyCodeSplit
	//	    @UseGatekeeper(LoggedInGatekeeper.class)
	@UseGatekeeper(PrivilagedGatekeeper.class)
	interface MyProxy extends ProxyPlace<CommercialTabPresenter> {
	}

	@Inject
	CommercialTabPresenter(
			EventBus eventBus,
			MyView view, 
			MyProxy proxy) {
		super(eventBus, view, proxy, ApplicationPresenter.SLOT_SetMainContent);

		getView().setUiHandlers(this);
		System.out.println("commercial presenter");
	}

	protected void onBind() {
		super.onBind();
		if(currentUser.getTabModel().getCommercial().equalsIgnoreCase("false") && !currentUser.getRoles().contains("Role_ADMIN")){
			placeManager.revealUnauthorizedPlace(NameTokens.unauthorized);
		}
	}

}