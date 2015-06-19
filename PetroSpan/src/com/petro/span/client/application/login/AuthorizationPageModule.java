package com.petro.span.client.application.login;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class AuthorizationPageModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        bindPresenter(AuthorizationPagePresenter.class, AuthorizationPagePresenter.MyView.class, AuthorizationPageView.class, AuthorizationPagePresenter.MyProxy.class);
    }
}