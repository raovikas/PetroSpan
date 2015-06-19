package com.petro.span.client.application.production;

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
public class ProductionTabPresenter extends Presenter<ProductionTabPresenter.MyView, ProductionTabPresenter.MyProxy> implements ProductionTabUiHandlers {
	interface MyView extends View , HasUiHandlers<ProductionTabUiHandlers> {
	}

	

	@Inject
	CurrentUser currentUser;
	
	@Inject
	PlaceManager placeManager;

	
	@NameToken(NameTokens.production)
	@ProxyCodeSplit
	//    @UseGatekeeper(LoggedInGatekeeper.class)
	@UseGatekeeper(PrivilagedGatekeeper.class)
	interface MyProxy extends ProxyPlace<ProductionTabPresenter> {
	}

	@Inject
	ProductionTabPresenter(
			EventBus eventBus,
			MyView view, 
			MyProxy proxy) {
		super(eventBus, view, proxy, ApplicationPresenter.SLOT_SetMainContent);

		getView().setUiHandlers(this);
		System.out.println("production presenter");
	}

	protected void onBind() {
		super.onBind();
		 if(currentUser.getTabModel().getProduction().equalsIgnoreCase("false") && !currentUser.getRoles().contains("Role_ADMIN")){
				placeManager.revealUnauthorizedPlace(NameTokens.unauthorized);
			}
	}

}