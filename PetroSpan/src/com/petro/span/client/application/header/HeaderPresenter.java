package com.petro.span.client.application.header;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
public class HeaderPresenter extends PresenterWidget<HeaderPresenter.MyView>  {
    interface MyView extends View  {

		void initialize();
    }
   
    
  

    @Inject
    HeaderPresenter(
            EventBus eventBus,
            MyView view) {
        super(eventBus, view);
        System.out.println("HeaderPresenter");
    }
    
    
    @Override
    protected void onReveal() {
    	super.onReveal();
    	getView().initialize();
    }
}