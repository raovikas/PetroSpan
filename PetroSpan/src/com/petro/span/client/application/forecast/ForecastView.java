package com.petro.span.client.application.forecast;


import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.petro.span.client.application.common.filter.CommonFilter;

class ForecastView extends ViewWithUiHandlers<ForecastUiHandlers> implements ForecastPresenter.MyView {
    interface Binder extends UiBinder<Widget, ForecastView> {
    }


    @UiField(provided = true)
   	public CommonFilter commonFilter ;
    

    @Inject
    ForecastView(Binder uiBinder, CommonFilter commonFilter) {
    	this.commonFilter = commonFilter;
        initWidget(uiBinder.createAndBindUi(this));
    }
    
   
}