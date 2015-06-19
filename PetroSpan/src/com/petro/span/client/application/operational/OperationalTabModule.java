package com.petro.span.client.application.operational;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class OperationalTabModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        bindPresenter(OperationalTabPresenter.class, OperationalTabPresenter.MyView.class, OperationalTabView.class, OperationalTabPresenter.MyProxy.class);
    }
}