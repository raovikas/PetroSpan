package com.petro.span.client.application.operational;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.UseGatekeeper;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.petro.span.client.LoggedInGatekeeper;
import com.petro.span.client.application.ApplicationPresenter;
import com.petro.span.client.application.common.filter.OperationalCommonFilter;
import com.petro.span.client.application.event.GlobalDataEvent;
import com.petro.span.client.place.NameTokens;
public class OperationalTabPresenter extends Presenter<OperationalTabPresenter.MyView, OperationalTabPresenter.MyProxy> implements OperationalTabUiHandlers {
	
    interface MyView extends View , HasUiHandlers<OperationalTabUiHandlers> {

		void addHandlers();
    }
   
    
    @ProxyCodeSplit
    @NameToken(NameTokens.operational)
    @UseGatekeeper(LoggedInGatekeeper.class)
    interface MyProxy extends ProxyPlace<OperationalTabPresenter> {
    }

    @Inject
    OperationalTabPresenter(
            EventBus eventBus,
            MyView view, 
            MyProxy proxy) {
        super(eventBus, view, proxy,ApplicationPresenter.SLOT_SetMainContent);
        
        getView().setUiHandlers(this);
    }
    
    protected void onBind() {
        super.onBind();
        getView().addHandlers();
    }

	@Override
	public void fireSelectionEvent(OperationalCommonFilter operationalFilter) {
		GlobalDataEvent.fire(this, operationalFilter);
	}
    
}