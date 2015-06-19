package com.petro.span.client.application.production;

import javax.inject.Inject;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.petro.span.client.application.common.filter.CommonFilter;

class ProductionTabView extends ViewWithUiHandlers<ProductionTabUiHandlers> implements ProductionTabPresenter.MyView {
    interface Binder extends UiBinder<Widget, ProductionTabView> {
    }

  
    @UiField(provided = true)
   	public CommonFilter commonFilter ;
    
    @Inject
    ProductionTabView(Binder uiBinder, CommonFilter commonFilter) {
    	this.commonFilter = commonFilter;
        initWidget(uiBinder.createAndBindUi(this));
        System.out.println("production view");
    }
 
}