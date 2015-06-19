package com.petro.span.client.application.production;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class ProductionTabModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        bindPresenter(ProductionTabPresenter.class, ProductionTabPresenter.MyView.class, ProductionTabView.class, ProductionTabPresenter.MyProxy.class);
    }
}