package com.petro.span.client.application.current.selection;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.petro.span.client.application.event.AdminGridSelectionEvent;
import com.petro.span.client.application.event.GlobalDataEvent;
import com.petro.span.client.application.event.GlobalEvent;
import com.petro.span.client.application.event.AdminGridSelectionEvent.AdminGridSelectionHandler;
import com.petro.span.client.application.event.GlobalDataEvent.GlobalDataHandler;
import com.petro.span.client.application.event.GlobalEvent.GlobalHandler;
public class CurrentSelectionWidgetPresenter extends PresenterWidget<CurrentSelectionWidgetPresenter.MyView> implements CurrentSelectionWidgetUiHandlers,GlobalDataHandler,GlobalHandler,AdminGridSelectionHandler {
	interface MyView extends View , HasUiHandlers<CurrentSelectionWidgetUiHandlers> {



		void clearCurrentSelectionPanel(String token);

		void displayCurrentSelection(Object commonFilter);

		void displayAdminCurrentSelection(Object data);
	}

	@Inject
	CurrentSelectionWidgetPresenter(
			EventBus eventBus,
			MyView view) {
		super(eventBus, view);

		getView().setUiHandlers(this);
		System.out.println("CurrentSelectionWidgetPresenter");
	}

	protected void onBind() {
		super.onBind();
		addRegisteredHandler(GlobalDataEvent.getType(), this);
		addRegisteredHandler(GlobalEvent.getType(), this);
		addRegisteredHandler(AdminGridSelectionEvent.getType(), this);
	}

	@Override
	public void onGlobalDataEvent(GlobalDataEvent event) {
		getView().displayCurrentSelection(event.getData());
		System.out.println("onGlobalEvent");
	}

	@Override
	protected void onReveal() {
		super.onReveal();
		//	getView().setSize();
	}

	@Override
	protected void onReset() {
		super.onReset();
//		getView().setSize();
	}


	@Override
	public void onGlobalEvent(GlobalEvent event) {
		getView().clearCurrentSelectionPanel(event.getData());
	}

	
	@Override
	public void onAdminGridSelection(AdminGridSelectionEvent event) {
		getView().displayAdminCurrentSelection(event.getData());
	}


}


//	@Override
//	public void onCurrentSelection(CurrentSelectionEvent event) {
//		getView().displayCurrentSelection(event.getSelection());
//		System.out.println("onCurrentSelection");
//	}

