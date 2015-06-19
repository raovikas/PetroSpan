package com.petro.span.client.application;


import com.google.gwt.event.shared.GwtEvent.Type;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ContentSlot;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.client.proxy.RevealContentHandler;
import com.petro.span.client.application.current.selection.CurrentSelectionWidgetPresenter;
import com.petro.span.client.application.header.HeaderPresenter;
import com.petro.span.client.application.tabbar.TabBarPresenter;

public class ApplicationPresenter extends Presenter<ApplicationPresenter.MyView, ApplicationPresenter.MyProxy> {
	interface MyView extends View {
	}





	@ContentSlot
	public static final Type<RevealContentHandler<?>> SLOT_SetMainContent = new Type<>();

	@ProxyStandard
	interface MyProxy extends Proxy<ApplicationPresenter> {
	}

	private final HeaderPresenter header;
	private final TabBarPresenter tabBar;
	private final CurrentSelectionWidgetPresenter currentSelection;

	public static final  Object Header_Slot = new Object();
	public static final Object  TabBar_Slot = new Object();
	public static final Object  Current_Slot = new Object();

	@Inject
	ApplicationPresenter(EventBus eventBus,
			MyView view,
			MyProxy proxy,
			HeaderPresenter header,
			TabBarPresenter tabBar,
			CurrentSelectionWidgetPresenter currentSelection
			) {
		super(eventBus, view, proxy,RevealType.RootLayout);
		this.header = header;
		this.tabBar = tabBar;
		this.currentSelection =  currentSelection;
		System.out.println("Aplication Presenter");
		
	}

	@Override
	protected void onBind() {
		super.onBind();

		setInSlot(Header_Slot, header);
		setInSlot(TabBar_Slot, tabBar);
		setInSlot(Current_Slot, currentSelection);
	}
}