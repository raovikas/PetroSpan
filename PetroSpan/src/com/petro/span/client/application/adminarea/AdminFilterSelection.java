package com.petro.span.client.application.adminarea;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.cell.client.Cell.Context;
import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.Header;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.petro.span.client.BPSSUtils;
import com.petro.span.client.DataService;
import com.petro.span.client.resources.DataGridResource;
import com.petro.span.shared.FilterSelectionModel;
import com.petro.span.shared.TabSelectionModel;

public class AdminFilterSelection extends Composite  {

	//	private static AdminFilterSelectionUiBinder uiBinder = GWT
	//			.create(AdminFilterSelectionUiBinder.class);

	interface AdminFilterSelectionUiBinder extends
	UiBinder<Widget, AdminFilterSelection> {
	}


	DataGridResource res = GWT.create(DataGridResource.class);

	@UiField (provided = true) DataGrid<FilterSelectionModel> filtersGrid = new DataGrid<FilterSelectionModel>(30000,res);
	@UiField (provided = true) DataGrid<TabSelectionModel> tabsGrid = new DataGrid<TabSelectionModel>(30000,res);


	Header<Boolean> checkFiltersHeader ;
	Column<FilterSelectionModel,Boolean> filterCheck;
	TextColumn<FilterSelectionModel> filterName;
	Boolean isFiltersHeaderChecked = false;
	DataService<FilterSelectionModel> filterDataService;


	AdminAreaView adminAreaView;
	
	Header<Boolean> checkTabHeader ;
	Column<TabSelectionModel,Boolean> tabCheck;
	TextColumn<TabSelectionModel> tabName;

	DataService<TabSelectionModel> tabsDataService;
	Boolean isTabHeaderChecked = false;

	List<String> filtersList = new ArrayList<String>();
	List<String> tabsList = new ArrayList<String>();
	
	List<FilterSelectionModel> filtersCheckList = new ArrayList<FilterSelectionModel>();
	List<TabSelectionModel> tabsCheckList = new ArrayList<TabSelectionModel>();
	

	@Inject
	private BPSSUtils bpssUtils;
	
	/**
     * An html string representation of a checked input box.
     */
    private final SafeHtml INPUT_CHECKED = SafeHtmlUtils.fromSafeConstant("<input type=\"checkbox\" tabindex=\"-1\" checked/>");
    
    
    /**
     * An html string representation of an unchecked input box.
     */
    private final SafeHtml INPUT_UNCHECKED = SafeHtmlUtils.fromSafeConstant("<input type=\"checkbox\" tabindex=\"-1\"/>");

	
	@Inject
	public AdminFilterSelection(AdminFilterSelectionUiBinder uiBinder,DataService<FilterSelectionModel> filterDataService,DataService<TabSelectionModel> tabsDataService) {
		initWidget(uiBinder.createAndBindUi(this));
		this.filterDataService = filterDataService;
		this.tabsDataService = tabsDataService;
		createFiltersGrid();
		createTabsGrid();
	}


	private void createTabsGrid() {

		checkTabHeader = new Header<Boolean>(new CheckboxCell(true, true)) {

			@Override
			public Boolean getValue() {
				return isTabHeaderChecked;
			}
			
			@Override
			public void render(Context context, SafeHtmlBuilder sb) {
				 if (Boolean.TRUE.equals(this.getValue())) {
			            sb.append(INPUT_CHECKED);
			        } else {
			            sb.append(INPUT_UNCHECKED);
			        }
			}
		};


		checkTabHeader.setUpdater(new ValueUpdater<Boolean>() {

			@Override
			public void update(Boolean value) {
				isTabHeaderChecked = value;
				System.out.println("isHeaderChecked "+isTabHeaderChecked);
				for(TabSelectionModel model : tabsGrid.getVisibleItems()){
					model.setTabCheck(isTabHeaderChecked);
					 bpssUtils.checklistUpdate(value,model,tabsCheckList);
					 adminAreaView.getDataForGridRecordSelectionEvent(model);
					tabsGrid.redraw();
				}
			}
		}
	);
		
		tabCheck = new Column<TabSelectionModel, Boolean>(new CheckboxCell(true, true)) {

			@Override
			public Boolean getValue(TabSelectionModel object) {
				return object.isTabCheck();
			}
			
			
		};
		
		
		
		tabCheck.setFieldUpdater(new FieldUpdater<TabSelectionModel, Boolean>() {
			
			@Override
			public void update(int index, TabSelectionModel object, Boolean value) {
				object.setTabCheck(value);
				bpssUtils.checklistUpdate(value, object, tabsCheckList);
				adminAreaView.getDataForGridRecordSelectionEvent(object);
			}
		});


		tabName = new TextColumn<TabSelectionModel>() {

			@Override
			public String getValue(TabSelectionModel object) {
				return object.getTabName();
			}
		};


		tabsGrid.addColumn(tabName, "TabName");
		tabsGrid.addColumn(tabCheck,checkTabHeader);

		if(!tabsDataService.getDataProvider().getDataDisplays().contains(tabsGrid)){
			tabsDataService.getDataProvider().addDataDisplay(tabsGrid);
		}


		tabsList.add("Technical");
		tabsList.add("Commercial");
		tabsList.add("Forecast");
		tabsList.add("Production");
		
		displayTabsGrid();

	}





	private void createFiltersGrid() {
		
		checkFiltersHeader = new Header<Boolean>(new CheckboxCell(true, true)) {

			@Override
			public Boolean getValue() {
				return isFiltersHeaderChecked;
			}
			
			@Override
			public void render(Context context, SafeHtmlBuilder sb) {
				 if (Boolean.TRUE.equals(this.getValue())) {
			            sb.append(INPUT_CHECKED);
			        } else {
			            sb.append(INPUT_UNCHECKED);
			        }
			}
		};


		checkFiltersHeader.setUpdater(new ValueUpdater<Boolean>() {

			@Override
			public void update(Boolean value) {
				isFiltersHeaderChecked = value;
				System.out.println("isHeaderChecked "+isFiltersHeaderChecked);
				for(FilterSelectionModel model : filtersGrid.getVisibleItems()){
					model.setFilterCheck(isFiltersHeaderChecked);
					 bpssUtils.checklistUpdate(value,model,filtersCheckList);
					 adminAreaView.getDataForGridRecordSelectionEvent(model);
					filtersGrid.redraw();
				}
			}
		}
	);
		
		filterCheck = new Column<FilterSelectionModel, Boolean>(new CheckboxCell(true, true)) {

			@Override
			public Boolean getValue(FilterSelectionModel object) {
				return object.isFilterCheck();
			}
		};
		
		
		filterCheck.setFieldUpdater(new FieldUpdater<FilterSelectionModel, Boolean>() {

			@Override
			public void update(int index, FilterSelectionModel object,
					Boolean value) {
				object.setFilterCheck(value);
				bpssUtils.checklistUpdate(value, object, filtersCheckList);
				adminAreaView.getDataForGridRecordSelectionEvent(object);
			}
		});


		filterName = new TextColumn<FilterSelectionModel>() {

			@Override
			public String getValue(FilterSelectionModel object) {
				return object.getFilterName();
			}
		};


		filtersGrid.addColumn(filterName, "FilterName");
		filtersGrid.addColumn(filterCheck,checkFiltersHeader);

		if(!filterDataService.getDataProvider().getDataDisplays().contains(filtersGrid)){
			filterDataService.getDataProvider().addDataDisplay(filtersGrid);
		}


		filtersList.add("Quality Zone");
		filtersList.add("TownShip/Range");
		filtersList.add("Operator");
		filtersList.add("Lease Name ");
		filtersList.add("Well Name ");
		filtersList.add("Well Type ");
		filtersList.add("Well Orientation ");
		filtersList.add("Fluid Type");
		filtersList.add("Well Status ");
		filtersList.add("Producing Zone");
		filtersList.add("Permit Date");
		filtersList.add("Spud Date");
		filtersList.add("Completion Date");
		filtersList.add("First Production Date ");
		filtersList.add("Search By");

		displayFiltersGrid();



	}


	private void displayFiltersGrid() {
		List<FilterSelectionModel> filtersDisplayList = new ArrayList<FilterSelectionModel>();
		for (int i = 0; i < filtersList.size(); i++) {

			FilterSelectionModel model = new FilterSelectionModel();
			model.setFilterName(filtersList.get(i));
			filtersDisplayList.add(model);
		}


		filterDataService.refreshDataList(filtersDisplayList);
	}
	
	
	
	private void displayTabsGrid() {
		List<TabSelectionModel> tabsDisplayList = new ArrayList<TabSelectionModel>();
		for (int i = 0; i < tabsList.size(); i++) {

			TabSelectionModel model = new TabSelectionModel();
			model.setTabName(tabsList.get(i));
			tabsDisplayList.add(model);
		}


		tabsDataService.refreshDataList(tabsDisplayList);
	}


	public void setAdminAreaViewRef(AdminAreaView adminAreaView) {
		this.adminAreaView = adminAreaView;
	}




}
