package com.petro.span.client.application.technical;

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
public class TechnicalTabPresenter extends Presenter<TechnicalTabPresenter.MyView, TechnicalTabPresenter.MyProxy> implements TechnicalTabUiHandlers {
	interface MyView extends View , HasUiHandlers<TechnicalTabUiHandlers> {
	}

	@Inject
	private CurrentUser currentUser ;
	
	@Inject
	private PlaceManager placeManager;

	@NameToken(NameTokens.technical)
	@ProxyCodeSplit
	
	@UseGatekeeper(PrivilagedGatekeeper.class)
	interface MyProxy extends ProxyPlace<TechnicalTabPresenter> {
	}
	
	

	@Inject
	TechnicalTabPresenter(
			EventBus eventBus,
			MyView view, 
			MyProxy proxy) {
		super(eventBus, view, proxy, ApplicationPresenter.SLOT_SetMainContent);

		getView().setUiHandlers(this);
	}

	protected void onBind() {
		super.onBind();
		if(currentUser.getTabModel().getTechnical().equalsIgnoreCase("false") && !currentUser.getRoles().contains("Role_ADMIN")){
			placeManager.revealUnauthorizedPlace(NameTokens.unauthorized);
		}
	}

}