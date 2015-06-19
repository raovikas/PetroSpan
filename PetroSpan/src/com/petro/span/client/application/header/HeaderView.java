package com.petro.span.client.application.header;


import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.petro.span.shared.CurrentUser;
import com.petro.span.shared.UserInfo;

public class HeaderView extends ViewImpl implements HeaderPresenter.MyView {
	interface Binder extends UiBinder<Widget, HeaderView> {
	}

	//	  private static Binder uiBinder = GWT.create(Binder.class);

	@UiField 
	Label userNameLabel;

	@UiField
	Label logoutLabel;

	@Inject
	PlaceManager placeManager;


	private final CurrentUser currentUser;



	@Inject
	HeaderView(Binder uiBinder,UserInfo user,CurrentUser currentUser) {
		initWidget(uiBinder.createAndBindUi(this));
		this.currentUser = currentUser;


	}

	@UiHandler("logoutLabel")
	void onLogoutLabelClick(ClickEvent event) {
		currentUser.setLoggedIn(false);
		currentUser.setUsername("");

		Window.Location.assign(Window.Location.getHref().replace(Window.Location.getHash(), "#login"));
		Window.Location.reload();



	}

	@Override
	public void initialize() {
		userNameLabel.setText(currentUser.getUsername());
	}

}