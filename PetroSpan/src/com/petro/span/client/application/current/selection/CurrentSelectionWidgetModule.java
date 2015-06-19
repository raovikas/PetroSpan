package com.petro.span.client.application.current.selection;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class CurrentSelectionWidgetModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
            bindSingletonPresenterWidget(CurrentSelectionWidgetPresenter.class, CurrentSelectionWidgetPresenter.MyView.class, CurrentSelectionWidgetView.class);
    }
}