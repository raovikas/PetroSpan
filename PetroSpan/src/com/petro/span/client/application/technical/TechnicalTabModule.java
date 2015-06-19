package com.petro.span.client.application.technical;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class TechnicalTabModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        bindPresenter(TechnicalTabPresenter.class, TechnicalTabPresenter.MyView.class, TechnicalTabView.class, TechnicalTabPresenter.MyProxy.class);
    }
}