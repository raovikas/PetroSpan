package com.petro.span.client.application.common.filter;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
//import com.google.gwt.user.datepicker.client.DateBox;
import com.google.inject.Inject;
import com.petro.span.client.APIRequests;
import com.petro.span.client.AuthUtil;
import com.petro.span.client.BPSSUtils;
import com.petro.span.client.CustomDateBox;
import com.petro.span.client.Loader;
import com.petro.span.client.PlayAreaList;
import com.petro.span.shared.CurrentUser;
import com.petro.span.shared.UserInfo;

public class CommonFilter extends ResizeComposite  {

	//	private static CommonFilterUiBinder uiBinder = GWT
	//			.create(CommonFilterUiBinder.class);

	interface CommonFilterUiBinder extends UiBinder<Widget, CommonFilter> {
	}

	public  List<String> defaultSelectionList = new ArrayList<String>();
	public  List<String> operationalSelectionList = new ArrayList<String>();


	public @UiField ListBox countyListBox;
	@UiField
	public ListBox qualityZoneListBox;
	@UiField
	public ListBox townShipListBox;
	@UiField
	public ListBox wellTypeListBox;
	@UiField
	public ListBox operatorListBox;
	@UiField
	public ListBox wellNameListBox;
	@UiField
	public ListBox leaseNameListBox;
	@UiField
	public ListBox orientationListBox;
	@UiField
	public ListBox wellStatusListBox;
	@UiField
	public ListBox fluidTypeListBox;

	//stateMap do the mapping between 'State Description' and 'State Code'
	private Map<String,String> stateMap = new HashMap<String, String>(); 

	private List<String> stateList = new ArrayList<String>(); 


	@UiField
	public Grid grid ;

	String currentTableID;

	@UiField
	public ScrollPanel sideScrollPanel ;






	@UiField
	public
	LayoutPanel sideScrollLayout;

	@UiField
	public CustomDateBox permitDateFrm;
	public @UiField CustomDateBox spudDateFrm;
	@UiField
	public CustomDateBox completionDateFrm;
	@UiField
	public CustomDateBox productionDateFrm;
	@UiField
	public ListBox stateCombo;
	@UiField
	public ListBox searchByCombo;


	private AuthUtil authUtil;

	private  UserInfo user;

	@UiField
	public ListBox producingZoneCombo;
	@UiField public ListBox playCombo;
	@UiField
	public CustomDateBox permitDateTo;
	@UiField
	public CustomDateBox spudDateTo;
	@UiField
	public CustomDateBox completionDateTo;
	@UiField
	public CustomDateBox productionDateTo;
	@UiField
	public Button searchButton;
	@UiField TextBox searchTextBox;

	private PlayAreaList playAreaObject ;

	public  Loader loader;



	CurrentUser currentUser;



	@Inject
	public CommonFilter(CommonFilterUiBinder uiBinder,PlayAreaList playAreaObject,AuthUtil authUtil,
			UserInfo user,
			Loader loader ,	CurrentUser currentUser) {

		initWidget(uiBinder.createAndBindUi(this));


		this.playAreaObject = playAreaObject;
		playAreaObject.setRef(this);
		this.authUtil = authUtil;
		this.user = user;
		this.loader = loader;
		this.currentUser = currentUser;
		initialize();

		addHandlers();
		System.out.println("CommonFilter");

		List<String>  rolesList =   currentUser.getRoles();
		if((rolesList.contains("Role_PRIVILAGE")  || rolesList.contains("Role_USER")) && !(rolesList.contains("Role_ADMIN")))
			resetFilter();


	}

	private void resetFilter() {


		for (int row = grid.getRowCount()-1;  row >= 0; row--) {
			for (int col = 0; col < grid.getCellCount(row); col++) {	
				String text = 		grid.getText(row, col);
				System.out.println("text "+grid.getText(row, col));
				if( text.contains("Quality Zone") && currentUser.getFiltersModel().getQuailtyZone().equals("false")){
					//					grid.remove(countyListBox);
					grid.removeRow(row);

				}else if(text.contains("TownShip/Range") && currentUser.getFiltersModel().getTownshipRange().equals("false"))
					grid.removeRow(row);

				else if(text.contains("Operator") && currentUser.getFiltersModel().getOperator().equals("false"))
					grid.removeRow(row);

				else if(text.contains("Lease Name ") && currentUser.getFiltersModel().getLeaseName().equals("false"))
					grid.removeRow(row);

				else if(text.contains("Well Name ") && currentUser.getFiltersModel().getWellName().equals("false"))
					grid.removeRow(row);

				else if(text.contains("Well Type ") && currentUser.getFiltersModel().getWellType().equals("false"))
					grid.removeRow(row);

				else if(text.contains("Well Orientation ") && currentUser.getFiltersModel().getWellOrientation().equals("false"))
					grid.removeRow(row);

				else if(text.contains("Fluid Type") && currentUser.getFiltersModel().getFluidType().equals("false"))
					grid.removeRow(row);

				else if(text.contains("Well Status ") && currentUser.getFiltersModel().getWellStatus().equals("false"))
					grid.removeRow(row);
				else if(text.contains("Producing Zone") && currentUser.getFiltersModel().getProducingZone().equals("false"))
					grid.removeRow(row);
				else if(text.contains("Permit Date") && currentUser.getFiltersModel().getPermitDate().equals("false")){
					grid.removeRow(row);
					grid.removeRow(row);
					grid.removeRow(row);
				}
				else if(text.contains("Spud Date") && currentUser.getFiltersModel().getSpudDate().equals("false")){
					grid.removeRow(row);
					grid.removeRow(row);
					grid.removeRow(row);
				}
				else if(text.contains("Completion Date") && currentUser.getFiltersModel().getCompletionDate().equals("false")){
					grid.removeRow(row);
					grid.removeRow(row);
					grid.removeRow(row);
				}
				else if(text.contains("First Production Date ") && currentUser.getFiltersModel().getFirstProductionDt().equals("false")){
					grid.removeRow(row);
					grid.removeRow(row);
					grid.removeRow(row);
				}
				else if(text.contains("Search By") && currentUser.getFiltersModel().getSearchBy().equals("false"))
					grid.removeRow(row);
			}

		}
	}


	//	protected void playListInitialization() {
	//		try {
	//
	//			if(isFirstTimeObject){ 
	//				new Timer() {
	//
	//					@Override
	//					public void run() {
	//						isFirstTimeObject = false;
	//
	//
	//						addPlayComboItem();
	//					}
	//				}.schedule(5000);
	//				System.out.println("isFirstTimeObject "+isFirstTimeObject);
	//
	//			}
	//			else{
	//				addPlayComboItem();
	//				System.out.println("isFirstTimeObject "+isFirstTimeObject);
	//			}
	//
	//
	//		} catch (Exception e) {
	//			e.printStackTrace();
	//		}
	//
	//	}

	protected void addHandlers() {

		leaseNameListBox.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				wellNameListBox.setEnabled(true);
				generateWellNameListBox(leaseNameListBox.getValue(leaseNameListBox.getSelectedIndex()));
				wellNameListBox.setSelectedIndex(0);

			}
		});


		playCombo.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				playComboChangeHandler();
			}
		});




//		countyListBox.addChangeHandler(new ChangeHandler() {
//
//			@Override
//			public void onChange(ChangeEvent event) {
//				addWhereRequired();
//			}
//		});
//
//
//		playCombo.addChangeHandler(new ChangeHandler() {
//
//			@Override
//			public void onChange(ChangeEvent event) {
//				addWhereRequired();
//			}
//		});
//
//		stateCombo.addChangeHandler(new ChangeHandler() {
//
//			@Override
//			public void onChange(ChangeEvent event) {
//				addWhereRequired();
//			}
//		});
//
//
//		qualityZoneListBox.addChangeHandler(new ChangeHandler() {
//			@Override
//			public void onChange(ChangeEvent event) {
//				addWhereRequired();
//			}
//		});
//
//
//		townShipListBox.addChangeHandler(new ChangeHandler() {
//			@Override
//			public void onChange(ChangeEvent event) {
//				addWhereRequired();
//			}
//		});
//
//		operatorListBox.addChangeHandler(new ChangeHandler() {
//
//			@Override
//			public void onChange(ChangeEvent event) {
//				addWhereRequired();
//			}
//		});
//
//
//		leaseNameListBox.addChangeHandler(new ChangeHandler() {
//
//			@Override
//			public void onChange(ChangeEvent event) {
//				addWhereRequired();
//			}
//		});
//
//		wellTypeListBox.addChangeHandler(new ChangeHandler() {
//
//			@Override
//			public void onChange(ChangeEvent event) {
//				addWhereRequired();
//
//			}
//		});
//
//
//		wellNameListBox.addChangeHandler(new ChangeHandler() {
//
//			@Override
//			public void onChange(ChangeEvent event) {
//				addWhereRequired();
//
//			}
//		});
//
//
//		orientationListBox.addChangeHandler(new ChangeHandler() {
//
//			@Override
//			public void onChange(ChangeEvent event) {
//				addWhereRequired();
//			}
//		});
//
//		fluidTypeListBox.addChangeHandler(new ChangeHandler() {
//
//			@Override
//			public void onChange(ChangeEvent event) {
//				addWhereRequired();
//			}
//		});
//
//		wellStatusListBox.addChangeHandler(new ChangeHandler() {
//
//			@Override
//			public void onChange(ChangeEvent event) {
//				addWhereRequired();
//			}
//		});
//
//		producingZoneCombo.addChangeHandler(new ChangeHandler() {
//
//			@Override
//			public void onChange(ChangeEvent event) {
//				addWhereRequired();
//
//			}
//		});
//
//		permitDateFrm.addValueChangeHandler(new ValueChangeHandler<Date>() {
//
//			@Override
//			public void onValueChange(ValueChangeEvent<Date> event) {
//				addWhereRequired();
//			}
//		});
//
//		permitDateTo.addValueChangeHandler(new ValueChangeHandler<Date>() {
//
//			@Override
//			public void onValueChange(ValueChangeEvent<Date> event) {
//				addWhereRequired();
//			}
//		});
//
//
//		spudDateFrm.addValueChangeHandler(new ValueChangeHandler<Date>() {
//
//			@Override
//			public void onValueChange(ValueChangeEvent<Date> event) {
//				addWhereRequired();
//			}
//		});
//
//		spudDateTo.addValueChangeHandler(new ValueChangeHandler<Date>() {
//
//			@Override
//			public void onValueChange(ValueChangeEvent<Date> event) {
//				addWhereRequired();
//			}
//		});
//
//
//		completionDateFrm.addValueChangeHandler(new ValueChangeHandler<Date>() {
//
//			@Override
//			public void onValueChange(ValueChangeEvent<Date> event) {
//				addWhereRequired();
//			}
//		});
//
//		completionDateTo.addValueChangeHandler(new ValueChangeHandler<Date>() {
//
//			@Override
//			public void onValueChange(ValueChangeEvent<Date> event) {
//				addWhereRequired();
//			}
//		});
//
//
//
//		productionDateFrm.addValueChangeHandler(new ValueChangeHandler<Date>() {
//
//			@Override
//			public void onValueChange(ValueChangeEvent<Date> event) {
//				addWhereRequired();
//			}
//		});
//
//		productionDateTo.addValueChangeHandler(new ValueChangeHandler<Date>() {
//
//			@Override
//			public void onValueChange(ValueChangeEvent<Date> event) {
//				addWhereRequired();
//			}
//		});
//
//
//		searchByCombo.addChangeHandler(new ChangeHandler() {
//
//			@Override
//			public void onChange(ChangeEvent event) {
//				addWhereRequired();
//			}
//		});
//
//		searchButton.addClickHandler(new ClickHandler() {
//
//			@Override
//			public void onClick(ClickEvent event) {
//				addWhereRequired();
//			}
//		});		

	}



	protected void addWhereRequired(List<String> selectionList) {

		selectionList.clear();
		String permitDtFrom = null;
		String permitDtTo  = null;

		String spudDtFrm = null;
		String spudDtTo = null;


		String completionDtFrm = null;
		String completionDtTo = null;

		String productionDtFrm = null;
		String productionDtTo = null;

//		StringBuffer query = new StringBuffer();

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
			
			
			selectionList.add(" Play :"+playName);
		}
		
		
		
		
		if(!state.equalsIgnoreCase("-1")){
			
			
			
			selectionList.add(" , State :"+state);
		}
		
		
		
		
		if (!county.equalsIgnoreCase("-1")) {
			
			selectionList.add(" , County :"+county);
			
		}
		
		if (!qZone.equalsIgnoreCase("-1")) {
			
			selectionList.add(" , Quality Zone :"+qZone);
		}
		
		if (!township.equalsIgnoreCase("-1")) {
			
			selectionList.add(" , Township/Range :"+township);
		}

	

		if(!operator.equalsIgnoreCase("-1")){
			
			selectionList.add(" , Operator : "+operator);
		}

		if(!leaseName.equalsIgnoreCase("-1")){
			
			selectionList.add(" , Lease Name :"+leaseName);
		}

		if(!wellType.equalsIgnoreCase("-1")){
			
			selectionList.add(" , Well Type :"+wellType);
		}

		if(!wellName.equalsIgnoreCase("-1")){
			
			selectionList.add(" , Well Name :"+wellName);
		}


		if(!wellOrientation.equalsIgnoreCase("-1")){
			
			selectionList.add(" , Orientation :"+wellOrientation);
		}

		if(!fluidType.equalsIgnoreCase("-1")){
			
			selectionList.add(" , Fluid Type :"+fluidType);
		}


		if(!wellStatus.equalsIgnoreCase("-1")){
			
			selectionList.add(" , Well Status :"+wellStatus);
		}


		if(!producingZone.equalsIgnoreCase("-1")){
			
			selectionList.add(" , Producing Zone :"+producingZone);
		}


		if(!(permitDtFrom == null)){
			
			selectionList.add(", PermitDate From :"+permitDtFrom);
		
		}

		if(!(permitDtTo==null)){
			
			selectionList.add(", PermitDate To :"+permitDtTo);
		}




		if(!(spudDtFrm == null)){
			
			selectionList.add(", Spud Date From :"+spudDtFrm);
		}


		if(!(spudDtTo==null)){

			
			selectionList.add(", Spud Date To :"+spudDtTo);
		}



		if(!(completionDtFrm == null)){
			
			selectionList.add(", CompletionDate From :"+completionDtFrm);
		}


		if(!(completionDtTo==null)){

			
			selectionList.add(", CompletionDate To :"+completionDtTo);
		}


		if(!(productionDtFrm == null)){
			
			selectionList.add(", ProductionDate From :"+productionDtFrm);
		}


		if(!(productionDtTo==null)){

			
			selectionList.add(", ProductionDate To :"+productionDtTo);
		}


		if(!(searchBy.equalsIgnoreCase("-1")) && searchBox.trim().length()!=0){
			
			selectionList.add(" , "+searchBox);
		}


		

		
		

	
	}



	//	protected void getAllFilterParameters() {
	//		String currentPlayCode = playAreaObject.getPlayMap().get(playCombo.getItemText(playCombo.getSelectedIndex()).trim());
	//		String stateURL = "https://www.googleapis.com/fusiontables/v1/query?sql=SELECT 'State Description' , 'State Code' from  "+FUSION_TABLE_ID_STATE
	//				+ "  Where 'Play Code' = '"+currentPlayCode+"'";
	//
	//
	//		String httpGetUrl = "https://www.googleapis.com/fusiontables/v1/query?sql=SELECT 'County' FROM "
	//				+ currentTableID
	//				+ " GROUP BY 'County' Order By 'County'";
	//
	//		System.out.println("stateURL "+stateURL);
	//
	//		try {
	//			loader.show();
	//			//			RequestBuilder builder = bpssUtil.gwtGETBuilder(stateURL);
	//			//			System.out.println("builder getHeader "+builder.getHeader("Authorization"));
	//
	//			//			ParallelRequestCallback stateCallback = new ParallelRequestCallback();
	//			//			ParallelRequestCallback countyCallback = new ParallelRequestCallback();
	//
	//			ParallelRequestCallback stateCallback = bpssUtil.gwtPOSTTBuilder(stateURL);
	//			ParallelRequestCallback countyCallback = bpssUtil.gwtPOSTTBuilder(httpGetUrl);
	//
	//			@SuppressWarnings("unused")
	//			ParentRequestCallback  parentCallback = new ParentRequestCallback(stateCallback,countyCallback) {
	//
	//				@Override
	//				protected void handleSuccess() {
	//					loader.hide();
	//					System.out.println("handleSuccess");
	//					Response stateResponse = getCallbackData(0);
	//					Response countyResponse = getCallbackData(1);
	//					parseStateData(stateResponse.getText());
	//					parseJsonData(countyResponse.getText(),countyListBox);
	//				}
	//			};
	//
	//
	//		} catch (RequestException e) {
	//			e.printStackTrace();
	//			loader.hide();
	//		}
	//	}




	protected void playComboChangeHandler() {
		if(playCombo.getSelectedIndex()!=0){
			String currentPlayCode = playAreaObject.getPlayMap().get(playCombo.getItemText(playCombo.getSelectedIndex()).trim());
			currentTableID = playAreaObject.getTableIDMap().get(currentPlayCode);



			genrateCountyListBox();
			genrateQualityListBox();
			genrateTownshipListBox();

			generateOperatorListBox();

			generateWellTypeListBox();

			generateLeaseNameListBox();

			generateOrientationListBox();
			generateFluidTypeListBox();
			generateStatusListBox();

			//			generateWellNameListBox();

			generateProducingZoneBox();

			generateSearchByCombo();

			httpRequestForState(currentPlayCode);


		}else{
//			countyListBox.clear();
//			qualityZoneListBox.clear();
//			townShipListBox.clear();
//			operatorListBox.clear();
//			wellTypeListBox.clear();
//			leaseNameListBox.clear();
//			orientationListBox.clear();
//			fluidTypeListBox.clear();
//			wellStatusListBox.clear();
//			wellNameListBox.clear();
//			producingZoneCombo.clear();
//			searchByCombo.clear();
//			searchTextBox.setText("");
//			stateCombo.clear();
//			defaultSelectionList.clear();
			
			
			
			countyListBox.setItemSelected(0, true);
			qualityZoneListBox.setItemSelected(0, true);
			townShipListBox.setItemSelected(0, true);
			operatorListBox.setItemSelected(0, true);
			wellTypeListBox.setItemSelected(0, true);
			leaseNameListBox.setItemSelected(0, true);
			orientationListBox.setItemSelected(0, true);
			fluidTypeListBox.setItemSelected(0, true);
			wellStatusListBox.setItemSelected(0, true);
			wellNameListBox.setItemSelected(0, true);
			producingZoneCombo.setItemSelected(0, true);
			searchByCombo.setItemSelected(0, true);
			searchTextBox.setText("");
			stateCombo.setItemSelected(0, true);
			defaultSelectionList.clear();
			operationalSelectionList.clear();


		}

		initializeFilterDateValues();


	}

	protected void initializeFilterDateValues() {

		permitDateFrm.setValue(null);
		permitDateTo.setValue(null);

		spudDateFrm.setValue(null);
		spudDateTo.setValue(null);

		completionDateFrm.setValue(null);
		completionDateTo.setValue(null);

		productionDateFrm.setValue(null);
		productionDateTo.setValue(null);		
	}

	private void initialize() {
		//		playListInitialization();

		countyListBox.addItem("Select", "-1");
		qualityZoneListBox.addItem("Select", "-1");
		townShipListBox.addItem("Select", "-1");
		operatorListBox.addItem("Select", "-1");
		wellNameListBox.addItem("Select", "-1");
		leaseNameListBox.addItem("Select", "-1");
		wellTypeListBox.addItem("Select", "-1");
		orientationListBox.addItem("Select", "-1");
		fluidTypeListBox.addItem("Select", "-1");
		wellStatusListBox.addItem("Select", "-1");
		stateCombo.addItem("Select", "-1");
		producingZoneCombo.addItem("Select", "-1");
		//		apiListBox.addItem("Select" , "-1");

		wellNameListBox.setEnabled(false);
		/*
		 * Set the data format for permitDate , spudDate , completionDate , productionDate
		 */
		permitDateFrm.setFormat( new CustomDateBox.DefaultFormat(BPSSUtils.mm_dd_yyyy));
		permitDateFrm.getDatePicker().setYearAndMonthDropdownVisible(true);
		permitDateFrm.getDatePicker().setYearArrowsVisible(true);

		permitDateTo.setFormat( new CustomDateBox.DefaultFormat(BPSSUtils.mm_dd_yyyy));
		permitDateTo.getDatePicker().setYearAndMonthDropdownVisible(true);
		permitDateTo.getDatePicker().setYearArrowsVisible(true);

		spudDateFrm.setFormat( new CustomDateBox.DefaultFormat(BPSSUtils.mm_dd_yyyy));
		spudDateFrm.getDatePicker().setYearAndMonthDropdownVisible(true);
		spudDateFrm.getDatePicker().setYearArrowsVisible(true);

		spudDateTo.setFormat( new CustomDateBox.DefaultFormat(BPSSUtils.mm_dd_yyyy));
		spudDateTo.getDatePicker().setYearAndMonthDropdownVisible(true);
		spudDateTo.getDatePicker().setYearArrowsVisible(true);


		completionDateFrm.setFormat( new CustomDateBox.DefaultFormat(BPSSUtils.mm_dd_yyyy));
		completionDateFrm.getDatePicker().setYearAndMonthDropdownVisible(true);
		completionDateFrm.getDatePicker().setYearArrowsVisible(true);

		completionDateTo.setFormat( new CustomDateBox.DefaultFormat(BPSSUtils.mm_dd_yyyy));
		completionDateTo.getDatePicker().setYearAndMonthDropdownVisible(true);
		completionDateTo.getDatePicker().setYearArrowsVisible(true);



		productionDateFrm.setFormat( new CustomDateBox.DefaultFormat(BPSSUtils.mm_dd_yyyy));
		productionDateFrm.getDatePicker().setYearAndMonthDropdownVisible(true);
		productionDateFrm.getDatePicker().setYearArrowsVisible(true);


		productionDateTo.setFormat( new CustomDateBox.DefaultFormat(BPSSUtils.mm_dd_yyyy));
		productionDateTo.getDatePicker().setYearAndMonthDropdownVisible(true);
		productionDateTo.getDatePicker().setYearArrowsVisible(true);





		//
		//		locationCombo.addItem("Select");
		//		locationCombo.addItem("Quality Zone");
		//		locationCombo.addItem("County");
		//		locationCombo.addItem("Township/Range");
		//		locationCombo.addItem("MySelection");


	}


	protected void addPlayComboItem() {
		playCombo.addItem("Select");
		for (Iterator<String> iterator = playAreaObject.getPlayMap().keySet().iterator(); iterator.hasNext();) {
			String playDescription = (String) iterator.next();
			playCombo.addItem(playDescription);
		}		
	}













	private void genrateCountyListBox() {

		String httpGetUrl = "https://www.googleapis.com/fusiontables/v1/query?sql=SELECT 'County' FROM "
				+ currentTableID
				+ " GROUP BY 'County' Order By 'County'&key="+ FUSION_TABLE_ACCESS_KEY;
		gwtPOSTHttp(httpGetUrl, countyListBox);
	}



	private void genrateQualityListBox() {


		String httpGetUrl = "https://www.googleapis.com/fusiontables/v1/query?sql=SELECT 'Quality Zone' FROM "
				+ currentTableID
				+ " GROUP BY 'Quality Zone' Order By 'Quality Zone'&key="+ FUSION_TABLE_ACCESS_KEY;

		gwtPOSTHttp(httpGetUrl,  qualityZoneListBox);
	}



	private void genrateTownshipListBox() {


		String httpGetUrl = "https://www.googleapis.com/fusiontables/v1/query?sql=SELECT 'Township/Range' FROM "
				+ currentTableID
				+ " GROUP BY 'Township/Range' Order By 'Township/Range'&key="+ FUSION_TABLE_ACCESS_KEY;


		gwtPOSTHttp(httpGetUrl, townShipListBox);
	}



	private void generateOperatorListBox() {


		String httpGetUrl = "https://www.googleapis.com/fusiontables/v1/query?sql=SELECT 'Operator' FROM "
				+ currentTableID
				+ " GROUP BY 'Operator' Order By 'Operator'&key="+ FUSION_TABLE_ACCESS_KEY;

		gwtPOSTHttp(httpGetUrl, operatorListBox);
	}

	private void generateWellTypeListBox() {

		String httpGetUrl = "https://www.googleapis.com/fusiontables/v1/query?sql=SELECT 'Well Type' FROM "
				+ currentTableID
				+ " GROUP BY 'Well Type' Order By 'Well Type'&key="+ FUSION_TABLE_ACCESS_KEY;

		gwtPOSTHttp(httpGetUrl, wellTypeListBox);


	}



	protected void generateLeaseNameListBox() {
		String httpGetUrl = "https://www.googleapis.com/fusiontables/v1/query?sql=SELECT 'Lease Name' FROM "
				+ currentTableID
				+ " GROUP BY 'Lease Name' Order By 'Lease Name'&key="+ FUSION_TABLE_ACCESS_KEY;

		gwtPOSTHttp(httpGetUrl, leaseNameListBox);
	}



	private void generateOrientationListBox() {
		String httpGetUrl = "https://www.googleapis.com/fusiontables/v1/query?sql=SELECT 'Orientation' FROM "
				+ currentTableID
				+ " GROUP BY 'Orientation' Order By 'Orientation'&key="+ FUSION_TABLE_ACCESS_KEY;

		gwtPOSTHttp(httpGetUrl, orientationListBox);

	}



	private void generateFluidTypeListBox() {
		String httpGetUrl = "https://www.googleapis.com/fusiontables/v1/query?sql=SELECT 'Fluid Type' FROM "
				+ currentTableID
				+ " GROUP BY 'Fluid Type' Order By 'Fluid Type'&key="+ FUSION_TABLE_ACCESS_KEY;

		gwtPOSTHttp(httpGetUrl, fluidTypeListBox);

	}

	private void generateStatusListBox() {

		String httpGetUrl = "https://www.googleapis.com/fusiontables/v1/query?sql=SELECT 'Well Status' FROM "
				+ currentTableID
				+ " GROUP BY 'Well Status' Order By 'Well Status'&key="+ FUSION_TABLE_ACCESS_KEY;

		gwtPOSTHttp(httpGetUrl, wellStatusListBox);
	}



	protected void generateWellNameListBox(String leaseName) {
		try {
			if(!leaseName.equalsIgnoreCase("-1")){
				String httpGetUrl = "https://www.googleapis.com/fusiontables/v1/query?sql=SELECT 'Well Name' FROM "
						+ currentTableID +" Where 'Lease Name' = '"+ leaseName
						+ "' GROUP BY 'Well Name' Order By 'Well Name'&key="+ FUSION_TABLE_ACCESS_KEY;


				gwtPOSTHttp(httpGetUrl, wellNameListBox);

				//				getUiHandlers().fireSelectionEvent(byDefaultFilter);
			} else
			{
				wellNameListBox.clear();
				wellNameListBox.addItem("Select", "-1");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}



	protected void generateProducingZoneBox() {

		String httpPOSTUrl = "https://www.googleapis.com/fusiontables/v1/query?sql=SELECT 'Producing Zone' FROM "
				+ currentTableID
				+ " GROUP BY 'Producing Zone' Order By 'Producing Zone'&key="+ FUSION_TABLE_ACCESS_KEY;

		gwtPOSTHttp(httpPOSTUrl, producingZoneCombo);
	}


	protected void generateSearchByCombo() {
		searchByCombo.clear();
		searchByCombo.addItem("Select","-1");
		searchByCombo.addItem("API");
		searchByCombo.addItem("Well Name");
		searchByCombo.addItem("Lease Name");
	}

	APIRequests apiRequest = null;

	public void gwtPOSTHttp(String postUrl, ListBox listBox) {
		apiRequest = new APIRequests(authUtil,user);
		apiRequest.gwtPOSTHttp(postUrl,listBox);
	}




	String FUSION_TABLE_ID_STATE="1uwvtdtpQ-_JZFDndIJAoTn8-ia15KS4QxNtMRyGr";
	String FUSION_TABLE_ACCESS_KEY =	"AIzaSyD7YQT-LFaE8YR3HFeFvzAQtFhZpwXnNvI";

	public void httpRequestForState(String currentPlayCode) {

		String stateURL = "https://www.googleapis.com/fusiontables/v1/query?sql=SELECT 'State Description' , 'State Code' from  "+FUSION_TABLE_ID_STATE
				+ "  Where 'Play Code' = '"+currentPlayCode+"'&key="+ FUSION_TABLE_ACCESS_KEY;

		System.out.println("stateURL "+stateURL);

		try {
			loader.show();
			RequestBuilder builder = new RequestBuilder(RequestBuilder.POST, stateURL);
			builder.sendRequest("", new RequestCallback() {

				@Override
				public void onResponseReceived(Request request, Response response) {
					if(200 == response.getStatusCode()){
						String  responseText =  response.getText();
						System.out.println("responseText  "+responseText);
						parseStateData(responseText);

					}else

						System.out.println("error responseText  "+ response.getText());

				}

				@Override
				public void onError(Request request, Throwable exception) {
					System.out.println(exception.getLocalizedMessage());
					loader.hide();
				}
			});
		} catch (RequestException e) {
			e.printStackTrace();
			loader.hide();
		}
	}



	protected void parseStateData(String responseText) {

		JSONValue value = JSONParser.parseLenient(responseText);
		JSONObject productsObj = value.isObject();
		JSONArray rowsArray = productsObj.get("rows").isArray();
		stateMap.clear();
		stateList.clear();
		if (rowsArray != null) {
			for (int i = 0; i <= rowsArray.size() - 1; i++) {
				JSONArray colArray = rowsArray.get(i).isArray();
				String colArray0 = colArray.get(0).isString().stringValue();
				String colArray1 = colArray.get(1).isString().stringValue();

				stateMap.put(colArray0,colArray1);
				stateList.add(colArray0);
				System.out.println("stateMap size  "+stateMap.size());


			}


			stateCombo.clear();
			stateCombo.addItem("Select","-1");
			for (String state : stateList) {

				stateCombo.addItem(state);
			}
			loader.hide();
		}


	}


	@SuppressWarnings("unused")
	private void parseJsonData(String json,ListBox listBox) {
		listBox.clear();
		listBox.addItem("Select","-1");
		JSONValue value = JSONParser.parseLenient(json);
		JSONObject productsObj = value.isObject();
		JSONArray rowsArray = productsObj.get("rows").isArray();

		if (rowsArray != null) {
			for (int i = 0; i <= rowsArray.size() - 1; i++) {
				JSONArray colArray = rowsArray.get(i).isArray();

				listBox.addItem(colArray.get(0).isString().stringValue());
			}

			// RootPanel.get().add(lb);

		}

	}









}







