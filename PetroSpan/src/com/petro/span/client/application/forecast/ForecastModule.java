package com.petro.span.client.application.forecast;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class ForecastModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        bindPresenter(ForecastPresenter.class, ForecastPresenter.MyView.class, ForecastView.class, ForecastPresenter.MyProxy.class);
    }
}