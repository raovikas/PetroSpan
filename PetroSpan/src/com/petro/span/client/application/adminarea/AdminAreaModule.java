package com.petro.span.client.application.adminarea;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class AdminAreaModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        bindPresenter(AdminAreaPresenter.class, AdminAreaPresenter.MyView.class, AdminAreaView.class, AdminAreaPresenter.MyProxy.class);
    }
}