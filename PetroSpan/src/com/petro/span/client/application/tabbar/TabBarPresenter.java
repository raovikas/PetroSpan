package com.petro.span.client.application.tabbar;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.petro.span.client.application.event.GlobalEvent;

public class TabBarPresenter extends PresenterWidget<TabBarPresenter.MyView> implements TabBarUiHandlers {
	interface MyView extends View , HasUiHandlers<TabBarUiHandlers> {

		

		void addHandler();

		void tabBarSelectionHandler();

		void initialize();

		void addAdminTab();
	}

//	@Inject
//	AdminGatekeeper adminGatekeeper;

	@Inject
	TabBarPresenter(
			EventBus eventBus,
			MyView view) {
		super(eventBus, view);

		getView().setUiHandlers(this);
	}

	protected void onBind() {
		super.onBind();

		getView().initialize();
		
		getView().addHandler();
//		getView().tabBarSelectionHandler();

		System.out.println("TabBarPresenter");
	}
	
	@Override
	protected void onReveal() {
		super.onReveal();
//		System.out.println("onreveal "+adminGatekeeper.canReveal());
		getView().addAdminTab();
		
	
	}

	@Override
	protected void onReset() {
		System.out.println("onreset");
		super.onReset();
		
		
			
	}

	@Override
	public void clearSelectionPanel(String placeToken) {
		System.out.println("placetoken "+placeToken);
		GlobalEvent.fire(this,placeToken);
	}

}