package com.petro.span.client.application.bydefault;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class DefaultTabModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        bindPresenter(DefaultTabPresenter.class, DefaultTabPresenter.MyView.class, DefaultTabView.class, DefaultTabPresenter.MyProxy.class);
    }
}