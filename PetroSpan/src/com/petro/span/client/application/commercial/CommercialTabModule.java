package com.petro.span.client.application.commercial;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class CommercialTabModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        bindPresenter(CommercialTabPresenter.class, CommercialTabPresenter.MyView.class, CommercialTabView.class, CommercialTabPresenter.MyProxy.class);
    }
}