package com.petro.span.client.application.common.filter;

import java.util.Date;

import javax.inject.Inject;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.petro.span.client.AuthUtil;
import com.petro.span.client.Loader;
import com.petro.span.client.PlayAreaList;
import com.petro.span.shared.CurrentUser;
import com.petro.span.shared.UserInfo;

public class OperationalCommonFilter extends CommonFilter{

	
	@Inject
	public OperationalCommonFilter(CommonFilterUiBinder uiBinder,
			PlayAreaList playAreaObject, AuthUtil authUtil, UserInfo user,
			Loader loader, CurrentUser currentUser) {
		super(uiBinder, playAreaObject, authUtil, user, loader, currentUser);
		
		addHandler();
	}

	
	
	private void addHandler() {

		countyListBox.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				System.out.println("ByDefaultCommomFilter addHandler countyListBox");
				addWhereRequired(operationalSelectionList);

			}
		});



		qualityZoneListBox.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				addWhereRequired(operationalSelectionList);
			}
		});


		townShipListBox.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				addWhereRequired(operationalSelectionList);
			}
		});


		stateCombo.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				addWhereRequired(operationalSelectionList);
			}
		});

		operatorListBox.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				addWhereRequired(operationalSelectionList);
			}
		});


		leaseNameListBox.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				addWhereRequired(operationalSelectionList);
			}
		});

		wellTypeListBox.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				addWhereRequired(operationalSelectionList);
			}
		});


		wellNameListBox.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				addWhereRequired(operationalSelectionList);
			}
		});


		orientationListBox.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				addWhereRequired(operationalSelectionList);
			}
		});

		fluidTypeListBox.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				addWhereRequired(operationalSelectionList);
			}
		});

		wellStatusListBox.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				addWhereRequired(operationalSelectionList);
			}
		});

		producingZoneCombo.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				addWhereRequired(operationalSelectionList);
			}
		});


		permitDateFrm.addValueChangeHandler(new ValueChangeHandler<Date>() {

			@Override
			public void onValueChange(ValueChangeEvent<Date> event) {
					addWhereRequired(operationalSelectionList);
			}
		});

		permitDateTo.addValueChangeHandler(new ValueChangeHandler<Date>() {

			@Override
			public void onValueChange(ValueChangeEvent<Date> event) {
					addWhereRequired(operationalSelectionList);
			}
		});


		spudDateFrm.addValueChangeHandler(new ValueChangeHandler<Date>() {

			@Override
			public void onValueChange(ValueChangeEvent<Date> event) {
					addWhereRequired(operationalSelectionList);
			}
		});

		spudDateTo.addValueChangeHandler(new ValueChangeHandler<Date>() {

			@Override
			public void onValueChange(ValueChangeEvent<Date> event) {
					addWhereRequired(operationalSelectionList);
			}
		});


		completionDateFrm.addValueChangeHandler(new ValueChangeHandler<Date>() {

			@Override
			public void onValueChange(ValueChangeEvent<Date> event) {
					addWhereRequired(operationalSelectionList);
			}
		});

		completionDateTo.addValueChangeHandler(new ValueChangeHandler<Date>() {

			@Override
			public void onValueChange(ValueChangeEvent<Date> event) {
					addWhereRequired(operationalSelectionList);
			}
		});



		productionDateFrm.addValueChangeHandler(new ValueChangeHandler<Date>() {

			@Override
			public void onValueChange(ValueChangeEvent<Date> event) {
					addWhereRequired(operationalSelectionList);
			}
		});

		productionDateTo.addValueChangeHandler(new ValueChangeHandler<Date>() {

			@Override
			public void onValueChange(ValueChangeEvent<Date> event) {
					addWhereRequired(operationalSelectionList);
			}
		});


		searchByCombo.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				searchTextBox.setText("");
				addWhereRequired(operationalSelectionList);
			}
		});

		searchButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				addWhereRequired(operationalSelectionList);
			}
		});


		playCombo.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
			
				addWhereRequired(operationalSelectionList);


			}
		});


		
		





	}

}
