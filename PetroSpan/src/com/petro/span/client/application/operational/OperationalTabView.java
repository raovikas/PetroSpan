package com.petro.span.client.application.operational;


import java.util.Date;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.petro.span.client.application.common.filter.OperationalCommonFilter;

class OperationalTabView extends ViewWithUiHandlers<OperationalTabUiHandlers> implements OperationalTabPresenter.MyView {
    interface Binder extends UiBinder<Widget, OperationalTabView> {
    }

    @UiField(provided = true)
	public OperationalCommonFilter operationalFilter ;
    
    @Inject
    OperationalTabView(Binder uiBinder, OperationalCommonFilter operationalFilter) {
    	this.operationalFilter = operationalFilter;
        initWidget(uiBinder.createAndBindUi(this));
    }

	@Override
	public void addHandlers() {
		operationalFilter.countyListBox.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				getUiHandlers().fireSelectionEvent(operationalFilter);
			}
		});


		operationalFilter.playCombo.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				getUiHandlers().fireSelectionEvent(operationalFilter);
			}
		});

		operationalFilter.stateCombo.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				getUiHandlers().fireSelectionEvent(operationalFilter);
			}
		});


		operationalFilter.qualityZoneListBox.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				getUiHandlers().fireSelectionEvent(operationalFilter);
			}
		});


		operationalFilter.townShipListBox.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				getUiHandlers().fireSelectionEvent(operationalFilter);
			}
		});

		operationalFilter.operatorListBox.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				getUiHandlers().fireSelectionEvent(operationalFilter);
			}
		});


		operationalFilter.leaseNameListBox.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				getUiHandlers().fireSelectionEvent(operationalFilter);
			}
		});

		operationalFilter.wellTypeListBox.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				getUiHandlers().fireSelectionEvent(operationalFilter);			}
		});


		operationalFilter.wellNameListBox.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				getUiHandlers().fireSelectionEvent(operationalFilter);			}
		});


		operationalFilter.orientationListBox.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				getUiHandlers().fireSelectionEvent(operationalFilter);			}
		});

		operationalFilter.fluidTypeListBox.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				getUiHandlers().fireSelectionEvent(operationalFilter);			}
		});

		operationalFilter.wellStatusListBox.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				getUiHandlers().fireSelectionEvent(operationalFilter);			}
		});

		operationalFilter.producingZoneCombo.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				getUiHandlers().fireSelectionEvent(operationalFilter);	

			}
		});

		operationalFilter.permitDateFrm.addValueChangeHandler(new ValueChangeHandler<Date>() {

			@Override
			public void onValueChange(ValueChangeEvent<Date> event) {
				//				if(event.getValue()!=null){
				getUiHandlers().fireSelectionEvent(operationalFilter);	
				//				}
			}
		});

		operationalFilter.permitDateTo.addValueChangeHandler(new ValueChangeHandler<Date>() {

			@Override
			public void onValueChange(ValueChangeEvent<Date> event) {
				//				if(event.getValue()!=null){
				getUiHandlers().fireSelectionEvent(operationalFilter);	;
				//				}
			}
		});


		operationalFilter.spudDateFrm.addValueChangeHandler(new ValueChangeHandler<Date>() {

			@Override
			public void onValueChange(ValueChangeEvent<Date> event) {
				//				if(event.getValue()!=null){
				getUiHandlers().fireSelectionEvent(operationalFilter);	
				//				}
			}
		});

		operationalFilter.spudDateTo.addValueChangeHandler(new ValueChangeHandler<Date>() {

			@Override
			public void onValueChange(ValueChangeEvent<Date> event) {
				//				if(event.getValue()!=null){
				getUiHandlers().fireSelectionEvent(operationalFilter);	
				//				}
			}
		});


		operationalFilter.completionDateFrm.addValueChangeHandler(new ValueChangeHandler<Date>() {

			@Override
			public void onValueChange(ValueChangeEvent<Date> event) {
				//				if(event.getValue()!=null){
				getUiHandlers().fireSelectionEvent(operationalFilter);	
				//				}
			}
		});

		operationalFilter.completionDateTo.addValueChangeHandler(new ValueChangeHandler<Date>() {

			@Override
			public void onValueChange(ValueChangeEvent<Date> event) {
				//				if(event.getValue()!=null){
				getUiHandlers().fireSelectionEvent(operationalFilter);	
				//				}
			}
		});



		operationalFilter.productionDateFrm.addValueChangeHandler(new ValueChangeHandler<Date>() {

			@Override
			public void onValueChange(ValueChangeEvent<Date> event) {
				//				if(event.getValue()!=null){
				getUiHandlers().fireSelectionEvent(operationalFilter);	
				//				}
			}
		});

		operationalFilter.productionDateTo.addValueChangeHandler(new ValueChangeHandler<Date>() {

			@Override
			public void onValueChange(ValueChangeEvent<Date> event) {
				//				if(event.getValue()!=null){
				getUiHandlers().fireSelectionEvent(operationalFilter);	
				//				}
			}
		});


		operationalFilter.searchByCombo.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				getUiHandlers().fireSelectionEvent(operationalFilter);	
			}
		});

		operationalFilter.searchButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				getUiHandlers().fireSelectionEvent(operationalFilter);	
			}
		});		
	}
    
  
}