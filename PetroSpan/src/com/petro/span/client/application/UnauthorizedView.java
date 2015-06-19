package com.petro.span.client.application;

import javax.inject.Inject;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;

class UnauthorizedView extends ViewImpl implements UnauthorizedPresenter.MyView {
    interface Binder extends UiBinder<Widget, UnauthorizedView> {
    }

  
    @UiField Hyperlink linkToLogin;
    
    @Inject
    UnauthorizedView(Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }

	@Override
	public void setLinkToLogin(String placeToken) {
		linkToLogin.setTargetHistoryToken(placeToken);
	}
    
  
}