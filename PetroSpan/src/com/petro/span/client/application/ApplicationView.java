package com.petro.span.client.application;


import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;

class ApplicationView extends ViewImpl implements ApplicationPresenter.MyView {
	interface Binder extends UiBinder<Widget, ApplicationView> {
	}


	@UiField
	DockLayoutPanel appLayout;


	@UiField
	SimpleLayoutPanel headerLayout;

	@UiField
	SimpleLayoutPanel tabBarLayout;

	@UiField
	SimpleLayoutPanel contentLayout;
	
	@UiField
	SimpleLayoutPanel selectionLayout ;

//	@UiField public   CurrentSelectionWidget currentSelection;

	@Inject
	ApplicationView(Binder uiBinder) {
//		this.currentSelection = currentSelection;
		initWidget(uiBinder.createAndBindUi(this));
		appLayout.animate(1000);
	}

	
	@Override
	public void setInSlot(Object slot, IsWidget content) {
		
		if (slot == ApplicationPresenter.SLOT_SetMainContent) {
			contentLayout.setWidget(content);
			
		} 
		else if(slot == ApplicationPresenter.Header_Slot){

			headerLayout.setWidget(content);
		}
		else if(slot == ApplicationPresenter.TabBar_Slot){

			tabBarLayout.setWidget(content);
		}
		
		else if(slot == ApplicationPresenter.Current_Slot){
			selectionLayout.setWidget(content);
		}
	else {
			super.setInSlot(slot, content);
		}
	}

	
}