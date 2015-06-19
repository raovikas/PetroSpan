package com.petro.span.client.application.common.filter;

import java.util.Date;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.inject.Inject;
import com.petro.span.client.AuthUtil;
import com.petro.span.client.BPSSUtils;
import com.petro.span.client.Loader;
import com.petro.span.client.PlayAreaList;
import com.petro.span.client.application.bydefault.DefaultTabView;
import com.petro.span.shared.CurrentUser;
import com.petro.span.shared.UserInfo;

public class DefaultCommonFilter extends CommonFilter {

	DefaultTabView defaultTabView;
	

	@Inject
	public DefaultCommonFilter(CommonFilterUiBinder uiBinder,
			PlayAreaList playAreaObject, AuthUtil authUtil, UserInfo user,
			 Loader loader,CurrentUser currentUser) {
		super(uiBinder, playAreaObject, authUtil, user,  loader, currentUser);

		addHandler();

	}







	private void addHandler() {

		countyListBox.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				defaultTabView.refreshMapAndChart(currentTableID);
				System.out.println("ByDefaultCommomFilter addHandler countyListBox");
				addWhereRequired(defaultSelectionList);

			}
		});



		qualityZoneListBox.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				defaultTabView.refreshMapAndChart(currentTableID);
				addWhereRequired(defaultSelectionList);
			}
		});


		townShipListBox.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				defaultTabView.refreshMapAndChart(currentTableID);
				addWhereRequired(defaultSelectionList);
			}
		});


		stateCombo.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				defaultTabView.refreshMapAndChart(currentTableID);
				addWhereRequired(defaultSelectionList);
			}
		});

		operatorListBox.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				defaultTabView.refreshMapAndChart(currentTableID);
				addWhereRequired(defaultSelectionList);
			}
		});


		leaseNameListBox.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				defaultTabView.refreshMapAndChart(currentTableID);
				addWhereRequired(defaultSelectionList);
			}
		});

		wellTypeListBox.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				defaultTabView.refreshMapAndChart(currentTableID);
				addWhereRequired(defaultSelectionList);
			}
		});


		wellNameListBox.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				defaultTabView.refreshMapAndChart(currentTableID);
				addWhereRequired(defaultSelectionList);
			}
		});


		orientationListBox.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				defaultTabView.refreshMapAndChart(currentTableID);
				addWhereRequired(defaultSelectionList);
			}
		});

		fluidTypeListBox.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				defaultTabView.refreshMapAndChart(currentTableID);
				addWhereRequired(defaultSelectionList);
			}
		});

		wellStatusListBox.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				defaultTabView.refreshMapAndChart(currentTableID);
				addWhereRequired(defaultSelectionList);
			}
		});

		producingZoneCombo.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				defaultTabView.refreshMapAndChart(currentTableID);
				addWhereRequired(defaultSelectionList);
			}
		});


		permitDateFrm.addValueChangeHandler(new ValueChangeHandler<Date>() {

			@Override
			public void onValueChange(ValueChangeEvent<Date> event) {
//				if(event.getValue()!=null){
					defaultTabView.refreshMapAndChart(currentTableID);
					addWhereRequired(defaultSelectionList);
//				}
			}
		});

		permitDateTo.addValueChangeHandler(new ValueChangeHandler<Date>() {

			@Override
			public void onValueChange(ValueChangeEvent<Date> event) {
//				if(event.getValue()!=null){
					defaultTabView.refreshMapAndChart(currentTableID);
					addWhereRequired(defaultSelectionList);
//				}
			}
		});


		spudDateFrm.addValueChangeHandler(new ValueChangeHandler<Date>() {

			@Override
			public void onValueChange(ValueChangeEvent<Date> event) {
//				if(event.getValue()!=null){
					defaultTabView.refreshMapAndChart(currentTableID);
					addWhereRequired(defaultSelectionList);
//				}
			}
		});

		spudDateTo.addValueChangeHandler(new ValueChangeHandler<Date>() {

			@Override
			public void onValueChange(ValueChangeEvent<Date> event) {
//				if(event.getValue()!=null){
					defaultTabView.refreshMapAndChart(currentTableID);
					addWhereRequired(defaultSelectionList);
//				}
			}
		});


		completionDateFrm.addValueChangeHandler(new ValueChangeHandler<Date>() {

			@Override
			public void onValueChange(ValueChangeEvent<Date> event) {
//				if(event.getValue()!=null){
					defaultTabView.refreshMapAndChart(currentTableID);
					addWhereRequired(defaultSelectionList);
//				}
			}
		});

		completionDateTo.addValueChangeHandler(new ValueChangeHandler<Date>() {

			@Override
			public void onValueChange(ValueChangeEvent<Date> event) {
//				if(event.getValue()!=null){
					defaultTabView.refreshMapAndChart(currentTableID);
					addWhereRequired(defaultSelectionList);
//				}
			}
		});



		productionDateFrm.addValueChangeHandler(new ValueChangeHandler<Date>() {

			@Override
			public void onValueChange(ValueChangeEvent<Date> event) {
//				if(event.getValue()!=null){
					defaultTabView.refreshMapAndChart(currentTableID);
					addWhereRequired(defaultSelectionList);
//				}
			}
		});

		productionDateTo.addValueChangeHandler(new ValueChangeHandler<Date>() {

			@Override
			public void onValueChange(ValueChangeEvent<Date> event) {
//				if(event.getValue()!=null){
					defaultTabView.refreshMapAndChart(currentTableID);
					addWhereRequired(defaultSelectionList);
//				}
			}
		});


		searchByCombo.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				searchTextBox.setText("");
				defaultTabView.refreshMapAndChart(currentTableID);
				addWhereRequired(defaultSelectionList);
			}
		});

		searchButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				defaultTabView.refreshMapAndChart(currentTableID);
				addWhereRequired(defaultSelectionList);
			}
		});


		playCombo.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
			
				defaultTabView.showMapAndChart(currentTableID);
				addWhereRequired(defaultSelectionList);


			}
		});


		
		//		sideScrollLayout.addDomHandler(new MouseOverHandler() {
		//
		//			@Override
		//			public void onMouseOver(MouseOverEvent event) {
		//				defaultTabView.dockLayoutPanel.setWidgetSize(defaultTabView.byDefaultFilter, 40);
		//				defaultTabView.dockLayoutPanel.animate(400);
		//				sideScrollLayout.animate(400);
		//			}
		//		}, MouseOverEvent.getType());







	}









	public String playAreaClause(){
		defaultSelectionList.clear();
		String playName = playCombo.getValue(playCombo.getSelectedIndex());
		StringBuffer query = new StringBuffer();
		if(!playName.equalsIgnoreCase("-1")){


			query.append(" 'Play' = '"+playName+"'");
			defaultSelectionList.add("Play :"+playName);
		}

		System.out.println("String Buffered Query String   "+ query.toString());
		return query.toString();
	}











	public void setRef(DefaultTabView defaultTabView) {
		this.defaultTabView = defaultTabView;
	}



	public  String buildWhereClause() {
//		defaultSelectionList.clear();
		String permitDtFrom = null;
		String permitDtTo  = null;

		String spudDtFrm = null;
		String spudDtTo = null;


		String completionDtFrm = null;
		String completionDtTo = null;

		String productionDtFrm = null;
		String productionDtTo = null;

		StringBuffer query = new StringBuffer();

		String county = countyListBox
				.getValue(countyListBox.getSelectedIndex());

		String qZone = qualityZoneListBox.getValue(qualityZoneListBox
				.getSelectedIndex());

		String township = townShipListBox.getValue(townShipListBox
				.getSelectedIndex());

		String state = stateCombo.getValue(stateCombo.getSelectedIndex());

		String operator = operatorListBox.getValue(operatorListBox.getSelectedIndex()) ;


		String leaseName = leaseNameListBox.getValue(leaseNameListBox.getSelectedIndex());

		String wellType = wellTypeListBox.getValue(wellTypeListBox.getSelectedIndex());

		String wellName = wellNameListBox.getValue(wellNameListBox.getSelectedIndex());

		String wellOrientation = orientationListBox.getValue(orientationListBox.getSelectedIndex());

		String fluidType = fluidTypeListBox.getValue(fluidTypeListBox.getSelectedIndex());

		String wellStatus = wellStatusListBox.getValue(wellStatusListBox.getSelectedIndex());

		String producingZone = producingZoneCombo.getValue(producingZoneCombo.getSelectedIndex());

		String searchBox = searchTextBox.getValue().trim();

		String searchBy = searchByCombo.getValue(searchByCombo.getSelectedIndex());


		String playName = playCombo.getValue(playCombo.getSelectedIndex());

//		permitDtFrom = permitDateFrm.getValue()!=null?BPSSUtils.mm_dd_yyyy.format(permitDateFrm.getValue()):"";
		if(permitDateFrm.getValue()!=null)
			permitDtFrom = BPSSUtils.mm_dd_yyyy.format(permitDateFrm.getValue());

//		permitDtTo = permitDateTo.getValue()!=null?BPSSUtils.mm_dd_yyyy.format(permitDateTo.getValue()):"";
		if(permitDateTo.getValue()!=null)
			permitDtTo = BPSSUtils.mm_dd_yyyy.format(permitDateTo.getValue());

//		spudDtFrm = spudDateFrm.getValue()!=null?BPSSUtils.mm_dd_yyyy.format(spudDateFrm.getValue()):"";
		if(spudDateFrm.getValue()!=null)
			spudDtFrm = BPSSUtils.mm_dd_yyyy.format(spudDateFrm.getValue());

//		spudDtTo = spudDateTo.getValue()!=null?BPSSUtils.mm_dd_yyyy.format(spudDateTo.getValue()):"";
		if(spudDateTo.getValue()!=null)
			spudDtTo = BPSSUtils.mm_dd_yyyy.format(spudDateTo.getValue());

//		completionDtFrm = completionDateFrm.getValue()!=null?BPSSUtils.mm_dd_yyyy.format(completionDateFrm.getValue()):"";
		if(completionDateFrm.getValue()!=null)
			completionDtFrm = BPSSUtils.mm_dd_yyyy.format(completionDateFrm.getValue());

//		completionDtTo = completionDateTo.getValue()!=null?BPSSUtils.mm_dd_yyyy.format(completionDateTo.getValue()):"";
		if(completionDateTo.getValue()!=null)
			completionDtTo = BPSSUtils.mm_dd_yyyy.format(completionDateTo.getValue());


//		productionDtFrm = productionDateFrm.getValue()!=null?BPSSUtils.mm_dd_yyyy.format(productionDateFrm.getValue()):"";
		 if(productionDateFrm.getValue()!=null)
			productionDtFrm = BPSSUtils.mm_dd_yyyy.format(productionDateFrm.getValue());


//		productionDtTo =productionDateTo.getValue()!=null?BPSSUtils.mm_dd_yyyy.format(productionDateTo.getValue()):"";
		if(productionDateTo.getValue()!=null)
			productionDtTo = BPSSUtils.mm_dd_yyyy.format(productionDateTo.getValue());


		
		if(!playName.equalsIgnoreCase("-1")){
			if(query.length()>0){
				embedAndWhereRequired(query);
			}

			query.append(" 'Play' = '"+playName+"'");
			
			
//			defaultSelectionList.add(" Play :"+playName);
		}
		
		
		
		
		if(!state.equalsIgnoreCase("-1")){
			if(query.length()>0){
				embedAndWhereRequired(query);
			}
			query.append(" State = '" + state +"'");
			
			
//			defaultSelectionList.add(" , State :"+state);
		}
		
		
		
		
		if (!county.equalsIgnoreCase("-1")) {
			if(query.length()>0){
				embedAndWhereRequired(query);
			}
			query.append(" 'County' ='" + county + "'");
			System.out.println("county !=-1");
//			defaultSelectionList.add(" , County :"+county);
			
		}
		
		if (!qZone.equalsIgnoreCase("-1")) {
			if(query.length()>0){
				embedAndWhereRequired(query);
			}
			query.append(" 'Quality Zone' ='" + qZone + "'");
//			defaultSelectionList.add(" , Quality Zone :"+qZone);
		}
		
		if (!township.equalsIgnoreCase("-1")) {
			if(query.length()>0){
				embedAndWhereRequired(query);
			}
			query.append(" 'Township/Range' ='" + township + "'");
//			defaultSelectionList.add(" , Township/Range :"+township);
		}

	

		if(!operator.equalsIgnoreCase("-1")){
			if(query.length()>0){
				embedAndWhereRequired(query);
			}
			query.append(" Operator = '"+operator+"'");
//			defaultSelectionList.add(" , Operator : "+operator);
		}

		if(!leaseName.equalsIgnoreCase("-1")){
			if(query.length()>0){
				embedAndWhereRequired(query);
			}

			query.append(" 'Lease Name' = '"+leaseName+"'");
//			defaultSelectionList.add(" , Lease Name :"+leaseName);
		}

		if(!wellType.equalsIgnoreCase("-1")){
			if(query.length()>0){
				embedAndWhereRequired(query);
			}

			query.append(" 'Well Type' = '"+wellType+"'");
//			defaultSelectionList.add(" , Well Type :"+wellType);
		}

		if(!wellName.equalsIgnoreCase("-1")){
			if(query.length()>0){
				embedAndWhereRequired(query);
			}

			query.append(" 'Well Name' = '"+wellName+"'");
//			defaultSelectionList.add(" , Well Name :"+wellName);
		}


		if(!wellOrientation.equalsIgnoreCase("-1")){
			if(query.length()>0){
				embedAndWhereRequired(query);
			}

			query.append(" 'Orientation' = '"+wellOrientation+"'");
//			defaultSelectionList.add(" , Orientation :"+wellOrientation);
		}

		if(!fluidType.equalsIgnoreCase("-1")){
			if(query.length()>0){
				embedAndWhereRequired(query);
			}

			query.append(" 'Fluid Type' = '"+fluidType+"'");
//			defaultSelectionList.add(" , Fluid Type :"+fluidType);
		}


		if(!wellStatus.equalsIgnoreCase("-1")){
			if(query.length()>0){
				embedAndWhereRequired(query);
			}

			query.append(" 'Well Status' = '"+wellStatus+"'");
//			defaultSelectionList.add(" , Well Status :"+wellStatus);
		}


		if(!producingZone.equalsIgnoreCase("-1")){
			if(query.length()>0){
				embedAndWhereRequired(query);
			}

			query.append(" 'Producing Zone' = '"+producingZone+"'");
//			defaultSelectionList.add(" , Producing Zone :"+producingZone);
		}


		if(!(permitDtFrom == null)){
			if(query.length()>0){
				embedAndWhereRequired(query);
			}

			query.append(" 'Permit Date' >= '"+permitDtFrom+"'");
//			defaultSelectionList.add(", PermitDate From :"+permitDtFrom);
		
		}

		if(!(permitDtTo==null)){
			if(query.length()>0){
				embedAndWhereRequired(query);
			}

			query.append(" 'Permit Date' <= '"+permitDtTo+"'");
//			defaultSelectionList.add(", PermitDate To :"+permitDtTo);
		}




		if(!(spudDtFrm == null)){
			if(query.length()>0){
				embedAndWhereRequired(query);
			}

			query.append(" 'Spud Date' >= '"+spudDtFrm+"'");
//			defaultSelectionList.add(", Spud Date From :"+spudDtFrm);
		}


		if(!(spudDtTo==null)){

			if(query.length()>0){
				embedAndWhereRequired(query);
			}

			query.append(" 'Spud Date' <= '"+spudDtTo+"'");
//			defaultSelectionList.add(", Spud Date To :"+spudDtTo);
		}



		if(!(completionDtFrm == null)){
			if(query.length()>0){
				embedAndWhereRequired(query);
			}

			query.append(" 'Completion date' >= '"+completionDtFrm+"'");
//			defaultSelectionList.add(", CompletionDate From :"+completionDtFrm);
		}


		if(!(completionDtTo==null)){

			if(query.length()>0){
				embedAndWhereRequired(query);
			}

			query.append(" 'Completion date' <= '"+completionDtTo+"'");
//			defaultSelectionList.add(", CompletionDate To :"+completionDtTo);
		}


		if(!(productionDtFrm == null)){
			if(query.length()>0){
				embedAndWhereRequired(query);
			}

			query.append(" 'First Production date' >= '"+productionDtFrm+"'");
//			defaultSelectionList.add(", ProductionDate From :"+productionDtFrm);
		}


		if(!(productionDtTo==null)){

			if(query.length()>0){
				embedAndWhereRequired(query);
			}

			query.append(" 'First Production date' <= '"+productionDtTo+"'");
//			defaultSelectionList.add(", ProductionDate To :"+productionDtTo);
		}


		if(!(searchBy.equalsIgnoreCase("-1")) && searchBox.trim().length()!=0){
			if(query.length()>0){
				embedAndWhereRequired(query);
			}

			query.append("'"+searchBy+"' = '"+searchBox+"'");
//			defaultSelectionList.add(" , "+searchBox);
		}


		

		System.out.println("String Buffered Query String   "+ query.toString());
		return query.toString();
		

	}




	private void embedAndWhereRequired(StringBuffer query) {

		query.append(" and ");

	}
}
