package com.petro.span.client.application;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import com.petro.span.client.application.adminarea.AdminAreaModule;

public class UnauthorizedModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
      
		install(new AdminAreaModule());
		bindPresenter(UnauthorizedPresenter.class, UnauthorizedPresenter.MyView.class, UnauthorizedView.class, UnauthorizedPresenter.MyProxy.class);
    }
}