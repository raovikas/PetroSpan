package com.petro.span.client.application.tabbar;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class TabBarModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
            bindSingletonPresenterWidget(TabBarPresenter.class, TabBarPresenter.MyView.class, TabBarView.class);
//    	  bindPresenterWidget(TabBarPresenter.class, TabBarPresenter.MyView.class, TabBarView.class);
    }
}