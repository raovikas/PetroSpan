package com.petro.span.client.application.commercial;


import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.petro.span.client.application.common.filter.CommonFilter;

class CommercialTabView extends ViewWithUiHandlers<CommercialTabUiHandlers> implements CommercialTabPresenter.MyView {
    interface Binder extends UiBinder<Widget, CommercialTabView> {
    }

  
    @UiField(provided = true)
   	public CommonFilter commonFilter ;
    
    @Inject
    CommercialTabView(Binder uiBinder, CommonFilter commonFilter) {
    	this.commonFilter = commonFilter;
        initWidget(uiBinder.createAndBindUi(this));
        System.out.println("commercialView");
    }
    
  
}