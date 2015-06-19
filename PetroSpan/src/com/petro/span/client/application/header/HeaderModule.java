package com.petro.span.client.application.header;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class HeaderModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
    	bindSingletonPresenterWidget(HeaderPresenter.class, HeaderPresenter.MyView.class, HeaderView.class);
//    	bindPresenterWidget(HeaderPresenter.class, HeaderPresenter.MyView.class, HeaderView.class);
    }
}