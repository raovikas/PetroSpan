package com.petro.span.client.application.technical;


import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.petro.span.client.application.common.filter.CommonFilter;

class TechnicalTabView extends ViewWithUiHandlers<TechnicalTabUiHandlers> implements TechnicalTabPresenter.MyView {
    interface Binder extends UiBinder<Widget, TechnicalTabView> {
    }

    @UiField(provided = true)
   	public CommonFilter commonFilter ;

    @Inject
    TechnicalTabView(Binder uiBinder, CommonFilter commonFilter) {
    	this.commonFilter = commonFilter;
        initWidget(uiBinder.createAndBindUi(this));
    }
    
  
}