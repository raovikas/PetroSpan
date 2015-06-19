package com.petro.span.client.application.bydefault;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.event.logical.shared.AttachEvent.Handler;
import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.maps.client.base.LatLng;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.googlecode.gwt.charts.client.BarChartWidget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.petro.span.client.application.common.filter.DefaultCommonFilter;
import com.petro.span.client.application.ui.FusionHeatMapWidget;
import com.petro.span.client.application.ui.FusionTablesMapWidget;
import com.petro.span.client.application.ui.WidgetDescription;

public class DefaultTabView extends ViewWithUiHandlers<DefaultTabUiHandlers> implements DefaultTabPresenter.MyView{
	interface Binder extends UiBinder<Widget, DefaultTabView> {
	}







	@UiField SimpleLayoutPanel heatMapPanel;
	@UiField SimpleLayoutPanel fusionMapPanel;
	@UiField SimpleLayoutPanel chartPanel1;
	@UiField SimpleLayoutPanel chartPanel2;
	public @UiField LayoutPanel contentLayoutPanel;


	FusionHeatMapWidget heatMapWidget;
	FusionTablesMapWidget fusionTableMapWidget;
	BarChartWidget chartWidget2;
	BarChartWidget chartWidget1;


	@UiField(provided = true)
	public DefaultCommonFilter byDefaultFilter ;

	private boolean isFirstTimeLoad = true;


	@UiField
	public DockLayoutPanel dockLayoutPanel;




	@UiField
	SimpleLayoutPanel heatMapDiscriptionPanel;

	@UiField
	WidgetDescription heatMapDiscription ;


	@UiField
	SimpleLayoutPanel fusionMapDiscriptionPanel;

	@UiField
	WidgetDescription fusionMapDiscription ;

	@UiField
	SimpleLayoutPanel chart1DiscriptionPanel;

	@UiField
	WidgetDescription chart1Discription ;


	@UiField
	SimpleLayoutPanel chart2DiscriptionPanel;

	@UiField
	WidgetDescription chart2Discription ;


	Map<String,String> idMap = new HashMap<>();


	@Inject
	DefaultTabView(Binder uiBinder,DefaultCommonFilter byDefaultFilter ) {
		this.byDefaultFilter = byDefaultFilter;
		initWidget(uiBinder.createAndBindUi(this));
		byDefaultFilter.setRef(this);
		System.out.println("DefaultTabView before resetfilter");
		
		
	}




//
//	private void resetFilter() {
//		for (int row = 0;  row < byDefaultFilter.grid.getRowCount(); row++) {
//			for (int col = 0; col < byDefaultFilter.grid.getCellCount(row); col++) {	
//				Widget w =  byDefaultFilter.grid.getWidget(row, col);
////				byDefaultFilter.grid.getText(row, col);
//				if(w == byDefaultFilter.countyListBox){
//					byDefaultFilter.grid.remove(byDefaultFilter.countyListBox);
//					byDefaultFilter.grid.removeRow(row);
//					
//				}
//				
//			}
//			
//		}
//	}





	@Override
	public void initialize() {
		contentLayoutPanel.animate(1300);
		dockLayoutPanel.animate(1300);
		Window.enableScrolling(false);
		heatMapDiscription.resetButton.setVisible(true);
		fusionMapDiscription.resetButton.setVisible(true);
		hideMapAndChart();
	
	}



	@Override
	public void addHandlers() {


		fusionMapPanel.addAttachHandler(new Handler() {

			@Override
			public void onAttachOrDetach(AttachEvent event) {

				fusionMapPanel.getElement().setAttribute("id", "fusionMap");
				idMap.put("fusionID", "fusionMap");
			}
		});

		heatMapPanel.addAttachHandler(new Handler() {

			@Override
			public void onAttachOrDetach(AttachEvent event) {
				heatMapPanel.getElement().setAttribute("id", "heatMap");
				idMap.put("heatID", "heatMap");
			}
		});


		chartPanel1.addAttachHandler(new Handler() {

			@Override
			public void onAttachOrDetach(AttachEvent event) {
				chartPanel1.getElement().setAttribute("id","chart1");
				idMap.put("chart1ID", "chart1");
			}
		});

		chartPanel2.addAttachHandler(new Handler() {

			@Override
			public void onAttachOrDetach(AttachEvent event) {
				chartPanel2.getElement().setAttribute("id","chart2");
				idMap.put("chart2ID", "chart2");
			}
		});



		
		byDefaultFilter.countyListBox.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				getUiHandlers().fireSelectionEvent(byDefaultFilter);
			}
		});


		byDefaultFilter.playCombo.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				getUiHandlers().fireSelectionEvent(byDefaultFilter);
			}
		});

		byDefaultFilter.stateCombo.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				getUiHandlers().fireSelectionEvent(byDefaultFilter);
			}
		});


		byDefaultFilter.qualityZoneListBox.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				getUiHandlers().fireSelectionEvent(byDefaultFilter);
			}
		});


		byDefaultFilter.townShipListBox.addChangeHandler(new ChangeHandler() {
			@Override
			public void onChange(ChangeEvent event) {
				getUiHandlers().fireSelectionEvent(byDefaultFilter);
			}
		});

		byDefaultFilter.operatorListBox.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				getUiHandlers().fireSelectionEvent(byDefaultFilter);
			}
		});


		byDefaultFilter.leaseNameListBox.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				getUiHandlers().fireSelectionEvent(byDefaultFilter);
			}
		});

		byDefaultFilter.wellTypeListBox.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				getUiHandlers().fireSelectionEvent(byDefaultFilter);			}
		});


		byDefaultFilter.wellNameListBox.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				getUiHandlers().fireSelectionEvent(byDefaultFilter);			}
		});


		byDefaultFilter.orientationListBox.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				getUiHandlers().fireSelectionEvent(byDefaultFilter);			}
		});

		byDefaultFilter.fluidTypeListBox.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				getUiHandlers().fireSelectionEvent(byDefaultFilter);			}
		});

		byDefaultFilter.wellStatusListBox.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				getUiHandlers().fireSelectionEvent(byDefaultFilter);			}
		});

		byDefaultFilter.producingZoneCombo.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				getUiHandlers().fireSelectionEvent(byDefaultFilter);	

			}
		});

		byDefaultFilter.permitDateFrm.addValueChangeHandler(new ValueChangeHandler<Date>() {

			@Override
			public void onValueChange(ValueChangeEvent<Date> event) {
				//				if(event.getValue()!=null){
				getUiHandlers().fireSelectionEvent(byDefaultFilter);	
				//				}
			}
		});

		byDefaultFilter.permitDateTo.addValueChangeHandler(new ValueChangeHandler<Date>() {

			@Override
			public void onValueChange(ValueChangeEvent<Date> event) {
				//				if(event.getValue()!=null){
				getUiHandlers().fireSelectionEvent(byDefaultFilter);	;
				//				}
			}
		});


		byDefaultFilter.spudDateFrm.addValueChangeHandler(new ValueChangeHandler<Date>() {

			@Override
			public void onValueChange(ValueChangeEvent<Date> event) {
				//				if(event.getValue()!=null){
				getUiHandlers().fireSelectionEvent(byDefaultFilter);	
				//				}
			}
		});

		byDefaultFilter.spudDateTo.addValueChangeHandler(new ValueChangeHandler<Date>() {

			@Override
			public void onValueChange(ValueChangeEvent<Date> event) {
				//				if(event.getValue()!=null){
				getUiHandlers().fireSelectionEvent(byDefaultFilter);	
				//				}
			}
		});


		byDefaultFilter.completionDateFrm.addValueChangeHandler(new ValueChangeHandler<Date>() {

			@Override
			public void onValueChange(ValueChangeEvent<Date> event) {
				//				if(event.getValue()!=null){
				getUiHandlers().fireSelectionEvent(byDefaultFilter);	
				//				}
			}
		});

		byDefaultFilter.completionDateTo.addValueChangeHandler(new ValueChangeHandler<Date>() {

			@Override
			public void onValueChange(ValueChangeEvent<Date> event) {
				//				if(event.getValue()!=null){
				getUiHandlers().fireSelectionEvent(byDefaultFilter);	
				//				}
			}
		});



		byDefaultFilter.productionDateFrm.addValueChangeHandler(new ValueChangeHandler<Date>() {

			@Override
			public void onValueChange(ValueChangeEvent<Date> event) {
				//				if(event.getValue()!=null){
				getUiHandlers().fireSelectionEvent(byDefaultFilter);	
				//				}
			}
		});

		byDefaultFilter.productionDateTo.addValueChangeHandler(new ValueChangeHandler<Date>() {

			@Override
			public void onValueChange(ValueChangeEvent<Date> event) {
				//				if(event.getValue()!=null){
				getUiHandlers().fireSelectionEvent(byDefaultFilter);	
				//				}
			}
		});


		byDefaultFilter.searchByCombo.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				getUiHandlers().fireSelectionEvent(byDefaultFilter);	
			}
		});

		byDefaultFilter.searchButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				getUiHandlers().fireSelectionEvent(byDefaultFilter);	
			}
		});



		Window.addResizeHandler(new ResizeHandler() {

			@Override
			public void onResize(ResizeEvent event) {

				mapAndChartResize();

			}
		});

		//		contentLayoutPanel.addDomHandler(new MouseOverHandler() {
		//
		//			@Override
		//			public void onMouseOver(MouseOverEvent event) {
		//					dockLayoutPanel.setWidgetSize(byDefaultFilter, 10);
		//			dockLayoutPanel.animate(400);
		//			byDefaultFilter.sideScrollLayout.animate(400);
		//			}
		//		}, MouseOverEvent.getType());

		fusionMapDiscription.printIcon.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				popUpAndPrintMap(idMap.get("fusionID"));
			}
		});
		
		heatMapDiscription.printIcon.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				popUpAndPrintMap(idMap.get("heatID"));
			}
		});
		
		
		chart1Discription.printIcon.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				downloadChartCall(chartWidget1.getImageURI(),idMap.get("chart1ID"));
			}
		});
		
		chart2Discription.printIcon.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				downloadChartCall(chartWidget2.getImageURI(),idMap.get("chart2ID"));
			}
		});

		
		heatMapDiscription.resetButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				heatMapReset();
			}
		});

		fusionMapDiscription.resetButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				fusionMapReset();				
			}
		});


		heatMapDiscription.expandIcon.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {




				// Chart1  Description Widget Position 
				contentLayoutPanel.setWidgetLeftWidth(chart1DiscriptionPanel, 0.0, Unit.PCT, 0.0, Unit.PCT);
				contentLayoutPanel.setWidgetTopHeight(chart1DiscriptionPanel, 0.0, Unit.PCT, 0.0, Unit.PCT);

				//Chart1 Widget Position
				contentLayoutPanel.setWidgetLeftWidth(chartPanel1, 0.0, Unit.PCT, 0.0, Unit.PCT);
				contentLayoutPanel.setWidgetTopHeight(chartPanel1, 0.0, Unit.PCT, 0.0, Unit.PCT);


				//Fusion Map Description Widget Position 
				contentLayoutPanel.setWidgetLeftWidth(fusionMapDiscriptionPanel, 0.0, Unit.PCT, 0.0, Unit.PCT);
				contentLayoutPanel.setWidgetTopHeight(fusionMapDiscriptionPanel, 0.0, Unit.PCT, 0.0, Unit.PCT);

				// Fusion Map Widget Position
				contentLayoutPanel.setWidgetLeftWidth(fusionMapPanel, 0.0, Unit.PCT, 0.0, Unit.PCT);
				contentLayoutPanel.setWidgetTopHeight(fusionMapPanel, 0.0, Unit.PCT, 0.0, Unit.PCT);

				//Chart2  Description Widget Position 
				contentLayoutPanel.setWidgetLeftWidth(chart2DiscriptionPanel, 0.0, Unit.PCT, 0.0, Unit.PCT);
				contentLayoutPanel.setWidgetTopHeight(chart2DiscriptionPanel, 0.0, Unit.PCT, 0.0, Unit.PCT);

				//   Chart2 Widget Position
				contentLayoutPanel.setWidgetLeftWidth(chartPanel2, 0.0, Unit.PCT, 0.0, Unit.PCT);
				contentLayoutPanel.setWidgetTopHeight(chartPanel2, 0.0, Unit.PCT, 0.0, Unit.PCT);


				contentLayoutPanel.setWidgetLeftWidth(heatMapPanel, 1.0, Unit.PCT, 99.0, Unit.PCT);
				contentLayoutPanel.setWidgetTopHeight(heatMapPanel, 5.0, Unit.PCT, 00.0, Unit.PCT);

				contentLayoutPanel.forceLayout();
				contentLayoutPanel.setWidgetLeftWidth(heatMapPanel, 1.0, Unit.PCT, 99.0, Unit.PCT);
				contentLayoutPanel.setWidgetTopHeight(heatMapPanel, 5.0, Unit.PCT, 95.0, Unit.PCT);

				contentLayoutPanel.animate(700);


				new Timer(){

					@Override
					public void run() {
						heatMapWidget.getMapWidget().triggerResize();
						fusionTableMapWidget.getMapWidget().triggerResize();
					}

				}.schedule(300);




			}
		});
		//		heatMapDiscription.radioExpand.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
		//
		//			@Override
		//			public void onValueChange(ValueChangeEvent<Boolean> event) {
		//				if(event.getValue()){
		//
		//
		//
		//					// Chart1  Description Widget Position 
		//					contentLayoutPanel.setWidgetLeftWidth(chart1DiscriptionPanel, 0.0, Unit.PCT, 0.0, Unit.PCT);
		//					contentLayoutPanel.setWidgetTopHeight(chart1DiscriptionPanel, 0.0, Unit.PCT, 0.0, Unit.PCT);
		//
		//					//Chart1 Widget Position
		//					contentLayoutPanel.setWidgetLeftWidth(chartPanel1, 0.0, Unit.PCT, 0.0, Unit.PCT);
		//					contentLayoutPanel.setWidgetTopHeight(chartPanel1, 0.0, Unit.PCT, 0.0, Unit.PCT);
		//
		//
		//					//Fusion Map Description Widget Position 
		//					contentLayoutPanel.setWidgetLeftWidth(fusionMapDiscriptionPanel, 0.0, Unit.PCT, 0.0, Unit.PCT);
		//					contentLayoutPanel.setWidgetTopHeight(fusionMapDiscriptionPanel, 0.0, Unit.PCT, 0.0, Unit.PCT);
		//
		//					// Fusion Map Widget Position
		//					contentLayoutPanel.setWidgetLeftWidth(fusionMapPanel, 0.0, Unit.PCT, 0.0, Unit.PCT);
		//					contentLayoutPanel.setWidgetTopHeight(fusionMapPanel, 0.0, Unit.PCT, 0.0, Unit.PCT);
		//
		//					//Chart2  Description Widget Position 
		//					contentLayoutPanel.setWidgetLeftWidth(chart2DiscriptionPanel, 0.0, Unit.PCT, 0.0, Unit.PCT);
		//					contentLayoutPanel.setWidgetTopHeight(chart2DiscriptionPanel, 0.0, Unit.PCT, 0.0, Unit.PCT);
		//
		//					//   Chart2 Widget Position
		//					contentLayoutPanel.setWidgetLeftWidth(chartPanel2, 0.0, Unit.PCT, 0.0, Unit.PCT);
		//					contentLayoutPanel.setWidgetTopHeight(chartPanel2, 0.0, Unit.PCT, 0.0, Unit.PCT);
		//
		//
		//					contentLayoutPanel.setWidgetLeftWidth(heatMapPanel, 1.0, Unit.PCT, 99.0, Unit.PCT);
		//					contentLayoutPanel.setWidgetTopHeight(heatMapPanel, 5.0, Unit.PCT, 00.0, Unit.PCT);
		//
		//					contentLayoutPanel.forceLayout();
		//					contentLayoutPanel.setWidgetLeftWidth(heatMapPanel, 1.0, Unit.PCT, 99.0, Unit.PCT);
		//					contentLayoutPanel.setWidgetTopHeight(heatMapPanel, 5.0, Unit.PCT, 95.0, Unit.PCT);
		//
		//					contentLayoutPanel.animate(700);
		//
		//
		//					new Timer(){
		//
		//						@Override
		//						public void run() {
		//							heatMapWidget.getMapWidget().triggerResize();
		//							fusionTableMapWidget.getMapWidget().triggerResize();
		//						}
		//
		//					}.schedule(300);
		//
		//
		//
		//				}
		//
		//			}
		//		});


		heatMapDiscription.collapseIcon.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				layoutPositionOnCollapse();
				contentLayoutPanel.animate(700);
			}
		});




		//		heatMapDiscription.radioCollapse.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
		//
		//			@Override
		//			public void onValueChange(ValueChangeEvent<Boolean> event) {
		//				if(event.getValue()){
		//
		//					layoutPositionOnCollapse();
		//					contentLayoutPanel.animate(700);
		//				}
		//
		//			}
		//		});




		fusionMapDiscription.expandIcon.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {



				contentLayoutPanel.setWidgetLeftWidth(heatMapDiscriptionPanel, 0.0, Unit.PCT, 0.0, Unit.PCT);
				contentLayoutPanel.setWidgetTopHeight(heatMapDiscriptionPanel, 0.0, Unit.PCT, 0.0, Unit.PCT);

				contentLayoutPanel.setWidgetLeftWidth(heatMapPanel, 0.0, Unit.PCT, 0.0, Unit.PCT);
				contentLayoutPanel.setWidgetTopHeight(heatMapPanel, 0.0, Unit.PCT, 0.0, Unit.PCT);

				contentLayoutPanel.setWidgetLeftWidth(chart1DiscriptionPanel, 0.0, Unit.PCT, 0.0, Unit.PCT);
				contentLayoutPanel.setWidgetTopHeight(chart1DiscriptionPanel, 0.0, Unit.PCT, 0.0, Unit.PCT);

				contentLayoutPanel.setWidgetLeftWidth(chartPanel1, 0.0, Unit.PCT, 0.0, Unit.PCT);
				contentLayoutPanel.setWidgetTopHeight(chartPanel1, 0.0, Unit.PCT, 0.0, Unit.PCT);

				contentLayoutPanel.setWidgetLeftWidth(chart2DiscriptionPanel, 0.0, Unit.PCT, 0.0, Unit.PCT);
				contentLayoutPanel.setWidgetTopHeight(chart2DiscriptionPanel, 0.0, Unit.PCT, 0.0, Unit.PCT);

				contentLayoutPanel.setWidgetLeftWidth(chartPanel2, 0.0, Unit.PCT, 0.0, Unit.PCT);
				contentLayoutPanel.setWidgetTopHeight(chartPanel2, 0.0, Unit.PCT, 0.0, Unit.PCT);


				contentLayoutPanel.setWidgetLeftWidth(fusionMapDiscriptionPanel, 1.0, Unit.PCT, 48.0, Unit.PCT);
				contentLayoutPanel.setWidgetTopHeight(fusionMapDiscriptionPanel, 0.0, Unit.PCT, 5.0, Unit.PCT);


				contentLayoutPanel.setWidgetLeftWidth(fusionMapPanel, 1.0, Unit.PCT, 99.0, Unit.PCT);
				contentLayoutPanel.setWidgetTopHeight(fusionMapPanel, 5.0, Unit.PCT, 00.0, Unit.PCT);
				contentLayoutPanel.forceLayout();
				contentLayoutPanel.setWidgetLeftWidth(fusionMapPanel, 1.0, Unit.PCT, 99.0, Unit.PCT);
				contentLayoutPanel.setWidgetTopHeight(fusionMapPanel, 5.0, Unit.PCT, 95.0, Unit.PCT);

				contentLayoutPanel.animate(700);



				new Timer(){

					@Override
					public void run() {
						fusionTableMapWidget.getMapWidget().triggerResize();
						heatMapWidget.getMapWidget().triggerResize();
					}

				}.schedule(300);


			}
		});


		//		fusionMapDiscription.radioExpand.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
		//
		//			@Override
		//			public void onValueChange(ValueChangeEvent<Boolean> event) {
		//				if(event.getValue()){
		//
		//
		//					contentLayoutPanel.setWidgetLeftWidth(heatMapDiscriptionPanel, 0.0, Unit.PCT, 0.0, Unit.PCT);
		//					contentLayoutPanel.setWidgetTopHeight(heatMapDiscriptionPanel, 0.0, Unit.PCT, 0.0, Unit.PCT);
		//
		//					contentLayoutPanel.setWidgetLeftWidth(heatMapPanel, 0.0, Unit.PCT, 0.0, Unit.PCT);
		//					contentLayoutPanel.setWidgetTopHeight(heatMapPanel, 0.0, Unit.PCT, 0.0, Unit.PCT);
		//
		//					contentLayoutPanel.setWidgetLeftWidth(chart1DiscriptionPanel, 0.0, Unit.PCT, 0.0, Unit.PCT);
		//					contentLayoutPanel.setWidgetTopHeight(chart1DiscriptionPanel, 0.0, Unit.PCT, 0.0, Unit.PCT);
		//
		//					contentLayoutPanel.setWidgetLeftWidth(chartPanel1, 0.0, Unit.PCT, 0.0, Unit.PCT);
		//					contentLayoutPanel.setWidgetTopHeight(chartPanel1, 0.0, Unit.PCT, 0.0, Unit.PCT);
		//
		//					contentLayoutPanel.setWidgetLeftWidth(chart2DiscriptionPanel, 0.0, Unit.PCT, 0.0, Unit.PCT);
		//					contentLayoutPanel.setWidgetTopHeight(chart2DiscriptionPanel, 0.0, Unit.PCT, 0.0, Unit.PCT);
		//
		//					contentLayoutPanel.setWidgetLeftWidth(chartPanel2, 0.0, Unit.PCT, 0.0, Unit.PCT);
		//					contentLayoutPanel.setWidgetTopHeight(chartPanel2, 0.0, Unit.PCT, 0.0, Unit.PCT);
		//
		//
		//					contentLayoutPanel.setWidgetLeftWidth(fusionMapDiscriptionPanel, 1.0, Unit.PCT, 48.0, Unit.PCT);
		//					contentLayoutPanel.setWidgetTopHeight(fusionMapDiscriptionPanel, 0.0, Unit.PCT, 5.0, Unit.PCT);
		//
		//
		//					contentLayoutPanel.setWidgetLeftWidth(fusionMapPanel, 1.0, Unit.PCT, 99.0, Unit.PCT);
		//					contentLayoutPanel.setWidgetTopHeight(fusionMapPanel, 5.0, Unit.PCT, 00.0, Unit.PCT);
		//					contentLayoutPanel.forceLayout();
		//					contentLayoutPanel.setWidgetLeftWidth(fusionMapPanel, 1.0, Unit.PCT, 99.0, Unit.PCT);
		//					contentLayoutPanel.setWidgetTopHeight(fusionMapPanel, 5.0, Unit.PCT, 95.0, Unit.PCT);
		//
		//					contentLayoutPanel.animate(700);
		//
		//
		//
		//					new Timer(){
		//
		//						@Override
		//						public void run() {
		//							fusionTableMapWidget.getMapWidget().triggerResize();
		//							heatMapWidget.getMapWidget().triggerResize();
		//						}
		//
		//					}.schedule(300);
		//
		//				}
		//
		//			}
		//		});




		fusionMapDiscription.collapseIcon.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				layoutPositionOnCollapse();
				contentLayoutPanel.animate(700);
			}
		});

		//		fusionMapDiscription.radioCollapse.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
		//
		//			@Override
		//			public void onValueChange(ValueChangeEvent<Boolean> event) {
		//				if(event.getValue()){
		//
		//					layoutPositionOnCollapse();
		//					contentLayoutPanel.animate(700);
		//				}
		//			}
		//		});



		chart1Discription.expandIcon.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				contentLayoutPanel.setWidgetLeftWidth(heatMapDiscriptionPanel, 0.0, Unit.PCT, 0.0, Unit.PCT);
				contentLayoutPanel.setWidgetTopHeight(heatMapDiscriptionPanel, 0.0, Unit.PCT, 0.0, Unit.PCT);

				contentLayoutPanel.setWidgetLeftWidth(heatMapPanel, 0.0, Unit.PCT, 0.0, Unit.PCT);
				contentLayoutPanel.setWidgetTopHeight(heatMapPanel, 0.0, Unit.PCT, 0.0, Unit.PCT);


				//Fusion Map Description Widget Position 
				contentLayoutPanel.setWidgetLeftWidth(fusionMapDiscriptionPanel, 0.0, Unit.PCT, 0.0, Unit.PCT);
				contentLayoutPanel.setWidgetTopHeight(fusionMapDiscriptionPanel, 0.0, Unit.PCT, 0.0, Unit.PCT);

				// Fusion Map Widget Position
				contentLayoutPanel.setWidgetLeftWidth(fusionMapPanel, 0.0, Unit.PCT, 0.0, Unit.PCT);
				contentLayoutPanel.setWidgetTopHeight(fusionMapPanel, 0.0, Unit.PCT, 0.0, Unit.PCT);

				//Chart2  Description Widget Position 
				contentLayoutPanel.setWidgetLeftWidth(chart2DiscriptionPanel, 0.0, Unit.PCT, 0.0, Unit.PCT);
				contentLayoutPanel.setWidgetTopHeight(chart2DiscriptionPanel, 0.0, Unit.PCT, 0.0, Unit.PCT);

				//   Chart2 Widget Position
				contentLayoutPanel.setWidgetLeftWidth(chartPanel2, 0.0, Unit.PCT, 0.0, Unit.PCT);
				contentLayoutPanel.setWidgetTopHeight(chartPanel2, 0.0, Unit.PCT, 0.0, Unit.PCT);

				// Chart1  Description Widget Position 
				contentLayoutPanel.setWidgetLeftWidth(chart1DiscriptionPanel, 1.0, Unit.PCT, 48.0, Unit.PCT);
				contentLayoutPanel.setWidgetTopHeight(chart1DiscriptionPanel, 0.0, Unit.PCT, 5.0, Unit.PCT);

				//Chart1 Widget Position
				contentLayoutPanel.setWidgetLeftWidth(chartPanel1, 1.0, Unit.PCT, 99.0, Unit.PCT);
				contentLayoutPanel.setWidgetTopHeight(chartPanel1, 5.0, Unit.PCT, 00.0, Unit.PCT);

				contentLayoutPanel.forceLayout();



				//Chart1 Widget Position
				contentLayoutPanel.setWidgetLeftWidth(chartPanel1, 1.0, Unit.PCT, 99.0, Unit.PCT);
				contentLayoutPanel.setWidgetTopHeight(chartPanel1, 5.0, Unit.PCT, 95.0, Unit.PCT);

				contentLayoutPanel.animate(700);

				new Timer(){

					@Override
					public void run() {
						heatMapWidget.getMapWidget().triggerResize();
						fusionTableMapWidget.getMapWidget().triggerResize();
						chartWidget1.barChart.redraw();
						chartWidget2.barChart.redraw();
					}

				}.schedule(300);


			}
		});

		//		chart1Discription.radioExpand.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
		//
		//			@Override
		//			public void onValueChange(ValueChangeEvent<Boolean> event) {
		//				if(event.getValue()){
		//
		//					contentLayoutPanel.setWidgetLeftWidth(heatMapPanel, 0.0, Unit.PCT, 0.0, Unit.PCT);
		//					contentLayoutPanel.setWidgetTopHeight(heatMapPanel, 0.0, Unit.PCT, 0.0, Unit.PCT);
		//
		//
		//					//Fusion Map Description Widget Position 
		//					contentLayoutPanel.setWidgetLeftWidth(fusionMapDiscriptionPanel, 0.0, Unit.PCT, 0.0, Unit.PCT);
		//					contentLayoutPanel.setWidgetTopHeight(fusionMapDiscriptionPanel, 0.0, Unit.PCT, 0.0, Unit.PCT);
		//
		//					// Fusion Map Widget Position
		//					contentLayoutPanel.setWidgetLeftWidth(fusionMapPanel, 0.0, Unit.PCT, 0.0, Unit.PCT);
		//					contentLayoutPanel.setWidgetTopHeight(fusionMapPanel, 0.0, Unit.PCT, 0.0, Unit.PCT);
		//
		//					//Chart2  Description Widget Position 
		//					contentLayoutPanel.setWidgetLeftWidth(chart2DiscriptionPanel, 0.0, Unit.PCT, 0.0, Unit.PCT);
		//					contentLayoutPanel.setWidgetTopHeight(chart2DiscriptionPanel, 0.0, Unit.PCT, 0.0, Unit.PCT);
		//
		//					//   Chart2 Widget Position
		//					contentLayoutPanel.setWidgetLeftWidth(chartPanel2, 0.0, Unit.PCT, 0.0, Unit.PCT);
		//					contentLayoutPanel.setWidgetTopHeight(chartPanel2, 0.0, Unit.PCT, 0.0, Unit.PCT);
		//
		//					// Chart1  Description Widget Position 
		//					contentLayoutPanel.setWidgetLeftWidth(chart1DiscriptionPanel, 1.0, Unit.PCT, 48.0, Unit.PCT);
		//					contentLayoutPanel.setWidgetTopHeight(chart1DiscriptionPanel, 0.0, Unit.PCT, 5.0, Unit.PCT);
		//
		//					//Chart1 Widget Position
		//					contentLayoutPanel.setWidgetLeftWidth(chartPanel1, 1.0, Unit.PCT, 99.0, Unit.PCT);
		//					contentLayoutPanel.setWidgetTopHeight(chartPanel1, 5.0, Unit.PCT, 00.0, Unit.PCT);
		//
		//					contentLayoutPanel.forceLayout();
		//
		//
		//
		//					//Chart1 Widget Position
		//					contentLayoutPanel.setWidgetLeftWidth(chartPanel1, 1.0, Unit.PCT, 99.0, Unit.PCT);
		//					contentLayoutPanel.setWidgetTopHeight(chartPanel1, 5.0, Unit.PCT, 95.0, Unit.PCT);
		//
		//					contentLayoutPanel.animate(700);
		//
		//					new Timer(){
		//
		//						@Override
		//						public void run() {
		//							heatMapWidget.getMapWidget().triggerResize();
		//							fusionTableMapWidget.getMapWidget().triggerResize();
		//							chartWidget1.barChart.redraw();
		//							chartWidget2.barChart.redraw();
		//						}
		//
		//					}.schedule(300);
		//
		//				}
		//
		//
		//			}
		//		});


		chart1Discription.collapseIcon.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				layoutPositionOnCollapse();
				contentLayoutPanel.animate(700);
			}
		});


		//		chart1Discription.radioCollapse.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
		//
		//			@Override
		//			public void onValueChange(ValueChangeEvent<Boolean> event) {
		//				layoutPositionOnCollapse();
		//				contentLayoutPanel.animate(700);
		//			}
		//		});


		chart2Discription.expandIcon.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {

				System.out.println("radioExpand");

				contentLayoutPanel.setWidgetLeftWidth(heatMapDiscriptionPanel, 0.0, Unit.PCT, 0.0, Unit.PCT);
				contentLayoutPanel.setWidgetTopHeight(heatMapDiscriptionPanel, 0.0, Unit.PCT, 0.0, Unit.PCT);


				contentLayoutPanel.setWidgetLeftWidth(heatMapPanel, 0.0, Unit.PCT, 0.0, Unit.PCT);
				contentLayoutPanel.setWidgetTopHeight(heatMapPanel, 0.0, Unit.PCT, 0.0, Unit.PCT);

				// Chart1  Description Widget Position 
				contentLayoutPanel.setWidgetLeftWidth(chart1DiscriptionPanel, 0.0, Unit.PCT, 0.0, Unit.PCT);
				contentLayoutPanel.setWidgetTopHeight(chart1DiscriptionPanel, 0.0, Unit.PCT, 0.0, Unit.PCT);

				//Chart1 Widget Position
				contentLayoutPanel.setWidgetLeftWidth(chartPanel1, 0.0, Unit.PCT, 0.0, Unit.PCT);
				contentLayoutPanel.setWidgetTopHeight(chartPanel1, 0.0, Unit.PCT, 0.0, Unit.PCT);


				//Fusion Map Description Widget Position 
				contentLayoutPanel.setWidgetLeftWidth(fusionMapDiscriptionPanel, 0.0, Unit.PCT, 0.0, Unit.PCT);
				contentLayoutPanel.setWidgetTopHeight(fusionMapDiscriptionPanel, 0.0, Unit.PCT, 0.0, Unit.PCT);

				// Fusion Map Widget Position
				contentLayoutPanel.setWidgetLeftWidth(fusionMapPanel, 0.0, Unit.PCT, 0.0, Unit.PCT);
				contentLayoutPanel.setWidgetTopHeight(fusionMapPanel, 0.0, Unit.PCT, 0.0, Unit.PCT);




				//Chart2  Description Widget Position 
				contentLayoutPanel.setWidgetLeftWidth(chart2DiscriptionPanel, 1.0, Unit.PCT, 48.0, Unit.PCT);
				contentLayoutPanel.setWidgetTopHeight(chart2DiscriptionPanel, 0.0, Unit.PCT, 5.0, Unit.PCT);

				//Chart2 Widget Position
				contentLayoutPanel.setWidgetLeftWidth(chartPanel2, 1.0, Unit.PCT, 99.0, Unit.PCT);
				contentLayoutPanel.setWidgetTopHeight(chartPanel2, 5.0, Unit.PCT, 00.0, Unit.PCT);

				contentLayoutPanel.forceLayout();


				//Chart2 Widget Position
				contentLayoutPanel.setWidgetLeftWidth(chartPanel2, 1.0, Unit.PCT, 99.0, Unit.PCT);
				contentLayoutPanel.setWidgetTopHeight(chartPanel2, 5.0, Unit.PCT, 95.0, Unit.PCT);

				contentLayoutPanel.animate(700);

				new Timer(){

					@Override
					public void run() {
						heatMapWidget.getMapWidget().triggerResize();
						fusionTableMapWidget.getMapWidget().triggerResize();
						chartWidget1.barChart.redraw();
						chartWidget2.barChart.redraw();
					}

				}.schedule(300);


			}
		});


		//		chart2Discription.radioExpand.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
		//
		//			@Override
		//			public void onValueChange(ValueChangeEvent<Boolean> event) {
		//				if(event.getValue()){
		//					System.out.println("radioExpand");
		//
		//					contentLayoutPanel.setWidgetLeftWidth(heatMapPanel, 0.0, Unit.PCT, 0.0, Unit.PCT);
		//					contentLayoutPanel.setWidgetTopHeight(heatMapPanel, 0.0, Unit.PCT, 0.0, Unit.PCT);
		//
		//					// Chart1  Description Widget Position 
		//					contentLayoutPanel.setWidgetLeftWidth(chart1DiscriptionPanel, 0.0, Unit.PCT, 0.0, Unit.PCT);
		//					contentLayoutPanel.setWidgetTopHeight(chart1DiscriptionPanel, 0.0, Unit.PCT, 0.0, Unit.PCT);
		//
		//					//Chart1 Widget Position
		//					contentLayoutPanel.setWidgetLeftWidth(chartPanel1, 0.0, Unit.PCT, 0.0, Unit.PCT);
		//					contentLayoutPanel.setWidgetTopHeight(chartPanel1, 0.0, Unit.PCT, 0.0, Unit.PCT);
		//
		//
		//					//Fusion Map Description Widget Position 
		//					contentLayoutPanel.setWidgetLeftWidth(fusionMapDiscriptionPanel, 0.0, Unit.PCT, 0.0, Unit.PCT);
		//					contentLayoutPanel.setWidgetTopHeight(fusionMapDiscriptionPanel, 0.0, Unit.PCT, 0.0, Unit.PCT);
		//
		//					// Fusion Map Widget Position
		//					contentLayoutPanel.setWidgetLeftWidth(fusionMapPanel, 0.0, Unit.PCT, 0.0, Unit.PCT);
		//					contentLayoutPanel.setWidgetTopHeight(fusionMapPanel, 0.0, Unit.PCT, 0.0, Unit.PCT);
		//
		//
		//
		//
		//					//Chart2  Description Widget Position 
		//					contentLayoutPanel.setWidgetLeftWidth(chart2DiscriptionPanel, 1.0, Unit.PCT, 48.0, Unit.PCT);
		//					contentLayoutPanel.setWidgetTopHeight(chart2DiscriptionPanel, 0.0, Unit.PCT, 5.0, Unit.PCT);
		//
		//					//Chart2 Widget Position
		//					contentLayoutPanel.setWidgetLeftWidth(chartPanel2, 1.0, Unit.PCT, 99.0, Unit.PCT);
		//					contentLayoutPanel.setWidgetTopHeight(chartPanel2, 5.0, Unit.PCT, 00.0, Unit.PCT);
		//
		//					contentLayoutPanel.forceLayout();
		//
		//
		//					//Chart2 Widget Position
		//					contentLayoutPanel.setWidgetLeftWidth(chartPanel2, 1.0, Unit.PCT, 99.0, Unit.PCT);
		//					contentLayoutPanel.setWidgetTopHeight(chartPanel2, 5.0, Unit.PCT, 95.0, Unit.PCT);
		//
		//					contentLayoutPanel.animate(700);
		//
		//					new Timer(){
		//
		//						@Override
		//						public void run() {
		//							heatMapWidget.getMapWidget().triggerResize();
		//							fusionTableMapWidget.getMapWidget().triggerResize();
		//							chartWidget1.barChart.redraw();
		//							chartWidget2.barChart.redraw();
		//						}
		//
		//					}.schedule(300);
		//
		//				}
		//			}
		//		});





		chart2Discription.collapseIcon.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				layoutPositionOnCollapse();
				contentLayoutPanel.animate(700);
			}
		});


		//		chart2Discription.radioCollapse.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
		//
		//			@Override
		//			public void onValueChange(ValueChangeEvent<Boolean> event) {
		//				layoutPositionOnCollapse();
		//				contentLayoutPanel.animate(700);
		//
		//			}
		//		});


		//		tabLayoutPanel.addSelectionHandler(new SelectionHandler<Integer>() {
		//
		//			@Override
		//			public void onSelection(SelectionEvent<Integer> event) {
		//				if(event.getSelectedItem() == 0){
		//
		//					sideScrollLayout.setWidgetTopHeight(sideScrollPanel, 0.0, Unit.PCT, 0.0, Unit.PCT);
		//					sideScrollLayout.forceLayout();
		//					sideScrollLayout.setWidgetTopHeight(sideScrollPanel, 0.0, Unit.PCT, 100.0, Unit.PCT);
		//					sideScrollLayout.animate(1300);
		//
		//				}else if(event.getSelectedItem() == 1){
		//
		//					operationalTAB.commonFilter.sideScrollLayout.setWidgetTopHeight(operationalTAB.commonFilter.sideScrollPanel, 0.0, Unit.PCT, 0.0, Unit.PCT);
		//					operationalTAB.commonFilter.sideScrollLayout.forceLayout();
		//					operationalTAB.commonFilter.sideScrollLayout.setWidgetTopHeight(operationalTAB.commonFilter.sideScrollPanel, 0.0, Unit.PCT, 100.0, Unit.PCT);
		//					operationalTAB.commonFilter.sideScrollLayout.animate(1300);
		//				}
		//				else if(event.getSelectedItem() == 2 ){
		//
		//					technicalTAB.commonFilter.sideScrollLayout.setWidgetTopHeight(technicalTAB.commonFilter.sideScrollPanel, 0.0, Unit.PCT, 0.0, Unit.PCT);
		//					technicalTAB.commonFilter.sideScrollLayout.forceLayout();
		//					technicalTAB.commonFilter.sideScrollLayout.setWidgetTopHeight(technicalTAB.commonFilter.sideScrollPanel, 0.0, Unit.PCT, 100.0, Unit.PCT);
		//					technicalTAB.commonFilter.sideScrollLayout.animate(1300);
		//				}
		//
		//				else if(event.getSelectedItem() == 3){
		//
		//					commercialTAB.commonFilter.sideScrollLayout.setWidgetTopHeight(commercialTAB.commonFilter.sideScrollPanel, 0.0, Unit.PCT, 0.0, Unit.PCT);
		//					commercialTAB.commonFilter.sideScrollLayout.forceLayout();
		//					commercialTAB.commonFilter.sideScrollLayout.setWidgetTopHeight(commercialTAB.commonFilter.sideScrollPanel, 0.0, Unit.PCT, 100.0, Unit.PCT);
		//					commercialTAB.commonFilter.sideScrollLayout.animate(1300);
		//				}
		//
		//
		//				else if(event.getSelectedItem() == 4 ){
		//
		//					forecastTAB.commonFilter.sideScrollLayout.setWidgetTopHeight(forecastTAB.commonFilter.sideScrollPanel, 0.0, Unit.PCT, 0.0, Unit.PCT);
		//					forecastTAB.commonFilter.sideScrollLayout.forceLayout();
		//					forecastTAB.commonFilter.sideScrollLayout.setWidgetTopHeight(forecastTAB.commonFilter.sideScrollPanel, 0.0, Unit.PCT, 100.0, Unit.PCT);
		//					forecastTAB.commonFilter.sideScrollLayout.animate(1300);
		//				}
		//
		//				else if(event.getSelectedItem() == 5 ){
		//
		//					productionTAB.commonFilter.sideScrollLayout.setWidgetTopHeight(productionTAB.commonFilter.sideScrollPanel, 0.0, Unit.PCT, 0.0, Unit.PCT);
		//					productionTAB.commonFilter.sideScrollLayout.forceLayout();
		//					productionTAB.commonFilter.sideScrollLayout.setWidgetTopHeight(productionTAB.commonFilter.sideScrollPanel, 0.0, Unit.PCT, 100.0, Unit.PCT);
		//					productionTAB.commonFilter.sideScrollLayout.animate(1300);
		//				}
		//			}
		//		});

	}









	public static native void printChart2() /*-{
		var content = $doc.getElementById("chart2"); // get you map details
		var newWindow = $wnd.open(); // open a new window
		newWindow.document.write(content.innerHTML); // write the map into the new window

		newWindow.document.close();
		newWindow.focus();
		newWindow.print();
		newWindow.close();
	}-*/;





	public static native void printChart1() /*-{
		var content = $doc.getElementById("chart1"); // get you map details
		var newWindow = $wnd.open(); // open a new window
		newWindow.document.write(content.innerHTML); // write the map into the new window

		newWindow.document.close();
		newWindow.focus();
		newWindow.print();
		newWindow.close();
	}-*/;


	/**
	 * This method  set  the default layout positions of maps and charts widget.
	 * This method is executed on collapse radio button selection on any maps or charts widget
	 */
	protected void layoutPositionOnCollapse() {

		contentLayoutPanel.setWidgetLeftWidth(fusionMapDiscriptionPanel, 1.0, Unit.PCT, 48.0, Unit.PCT);
		contentLayoutPanel.setWidgetTopHeight(fusionMapDiscriptionPanel, 0.0, Unit.PCT, 5.0, Unit.PCT);

		contentLayoutPanel.setWidgetLeftWidth(fusionMapPanel, 1.0, Unit.PCT, 48.0, Unit.PCT);
		contentLayoutPanel.setWidgetTopHeight(fusionMapPanel, 5.0, Unit.PCT, 43.0, Unit.PCT);


		contentLayoutPanel.setWidgetLeftWidth(chart1DiscriptionPanel, 1.0, Unit.PCT, 48.0, Unit.PCT);
		contentLayoutPanel.setWidgetTopHeight(chart1DiscriptionPanel, 50.0, Unit.PCT, 5.0, Unit.PCT);

		contentLayoutPanel.setWidgetLeftWidth(chartPanel1, 1.0, Unit.PCT, 48.0, Unit.PCT);
		contentLayoutPanel.setWidgetTopHeight(chartPanel1, 55.0, Unit.PCT, 43.0, Unit.PCT);



		contentLayoutPanel.setWidgetLeftWidth(heatMapDiscriptionPanel, 50.0, Unit.PCT, 48.0, Unit.PCT);
		contentLayoutPanel.setWidgetTopHeight(heatMapDiscriptionPanel, 0.0, Unit.PCT, 5.0, Unit.PCT);

		contentLayoutPanel.setWidgetLeftWidth(heatMapPanel, 50.0, Unit.PCT, 49.0, Unit.PCT);
		contentLayoutPanel.setWidgetTopHeight(heatMapPanel, 5.0, Unit.PCT, 43.0, Unit.PCT);


		contentLayoutPanel.setWidgetLeftWidth(chart2DiscriptionPanel, 50.0, Unit.PCT, 48.0, Unit.PCT);
		contentLayoutPanel.setWidgetTopHeight(chart2DiscriptionPanel, 50.0, Unit.PCT, 5.0, Unit.PCT);

		contentLayoutPanel.setWidgetLeftWidth(chartPanel2, 50.0, Unit.PCT, 48.0, Unit.PCT);
		contentLayoutPanel.setWidgetTopHeight(chartPanel2, 55.0, Unit.PCT, 43.0, Unit.PCT);


		new Timer(){

			@Override
			public void run() {
				fusionMapReset();
				heatMapReset();
				chartWidget1.barChart.redraw();
				chartWidget2.barChart.redraw();
			}

		}.schedule(700);


	}


	protected void heatMapReset() {
		LatLng northDakota =  LatLng.newInstance(48.161876, -102.884336);	
		heatMapWidget.getMapWidget().setCenter(northDakota);
		heatMapWidget.getMapWidget().triggerResize();
		heatMapWidget.getMapWidget().setZoom(6);		
	}





	protected void fusionMapReset() {
		LatLng northDakota =  LatLng.newInstance(48.161876, -102.884336);
		fusionTableMapWidget.getMapWidget().setCenter(northDakota);
		fusionTableMapWidget.getMapWidget().triggerResize();
		fusionTableMapWidget.getMapWidget().setZoom(6);	

	}





	public void refreshMapAndChart(String currentTableID) {
		String searchCreteria = byDefaultFilter.buildWhereClause();
		if(searchCreteria.trim().length()>0){
			//			heatMapDiscription.setVisible(true);
			//			chart1Discription.setVisible(true);
			//			fusionMapDiscription.setVisible(true);
			//			chart2Discription.setVisible(true);

			getUiHandlers().LoadMap(searchCreteria,currentTableID);
			getUiHandlers().LoadChart(searchCreteria,currentTableID);
		}
		//		else
		//		{
		//			hideMapAndChart();
		//
		//		}
	}

	public void showMapAndChart(String currentTableID) {
		String playCreteria = byDefaultFilter.playAreaClause();
		if(playCreteria.trim().length()>0){

			heatMapDiscription.setVisible(true);
			chart1Discription.setVisible(true);
			fusionMapDiscription.setVisible(true);
			chart2Discription.setVisible(true);

			getUiHandlers().LoadMap(playCreteria,currentTableID);
			getUiHandlers().LoadChart(playCreteria,currentTableID);

		}else
		{
			chartPanel1.clear();
			chartPanel2.clear();
			fusionMapPanel.clear();
			heatMapPanel.clear();
			hideMapAndChart();
		}
	}


	public  void hideMapAndChart() {

		heatMapDiscription.setVisible(false);
		chart1Discription.setVisible(false);
		fusionMapDiscription.setVisible(false);
		chart2Discription.setVisible(false);

	}





	@Override
	public void addMapView(Widget mapWidget) {
		if(mapWidget instanceof FusionHeatMapWidget){
			heatMapPanel.clear();
			FusionHeatMapWidget widget = (FusionHeatMapWidget)mapWidget;

			//			heatMapPanel.add(widget);
			heatMapPanel.setWidget(widget);
			widget.getMapWidget().triggerResize();
			this.heatMapWidget = widget;
		}else if(mapWidget instanceof FusionTablesMapWidget){
			fusionMapPanel.clear();
			FusionTablesMapWidget widget= (FusionTablesMapWidget)mapWidget;

			//			fusionMapPanel.add(widget);
			fusionMapPanel.setWidget(widget);
			widget.getMapWidget().triggerResize();
			this.fusionTableMapWidget = widget;
		}



	}



	/**
	 * This is the method called on Client  Window Resize to resize the map and chart .
	 */
	public void mapAndChartResize(){
		fusionTableMapWidget.getMapWidget().triggerResize();
		heatMapWidget.getMapWidget().triggerResize();
	}


	@Override
	public void addChartView(final BarChartWidget chartWidget, String chart) {

		if(chart.equalsIgnoreCase("API")){
			this.chartWidget2 = chartWidget;
			//			new Timer() {
			//
			//				@Override
			//				public void run() {
			chartPanel2.clear();
			//			chartPanel2.add(chartWidget2);
			chartPanel2.setWidget(chartWidget2);

			//				}
			//			}.schedule(5000);


		}else{
			this.chartWidget1 = chartWidget;
			//			new Timer() {
			//
			//				@Override
			//				public void run() {
			chartPanel1.clear();
			//			chartPanel1.add(chartWidget1);
			chartPanel1.setWidget(chartWidget1);
			//				}
			//			}.schedule(5000);

		}

	}





//	@Override
//	public void loadTimer() {
//		if(isFirstTimeLoad){
//			isFirstTimeLoad = false;
//			byDefaultFilter.loader.show();
//			new Timer() {
//
//				@Override
//				public void run() {
//					byDefaultFilter.loader.hide();
//				}
//			}.schedule(5000);
//		}
//	}



	void downloadChartCall(String imageURL, String id){
//		String imageURL = chartWidget1.getImageURI();
		popUpAndPrintChart(imageURL,id);
		System.out.println("imageURL "+imageURL);
	}



	public static native void popUpAndPrintChart(String imageURL, String id)/*-{
         var content = $doc.getElementById(id); // get you map details
         var newWindow = $wnd.open(); // open a new window
         newWindow.document.write('<img src="' + imageURL + '">'); // write the map into the new window

         newWindow.document.close();
         newWindow.focus();
         newWindow.print();
         newWindow.close();
	}-*/;




	public static native void popUpAndPrintMap (String id)/*-{
		var content = $doc.getElementById(id); // get you map details
        var newWindow = $wnd.open(); // open a new window
        newWindow.document.write(content.innerHTML); // write the map into the new window

        newWindow.document.close();
        newWindow.focus();
        newWindow.print();
        newWindow.close();




	}-*/;


}











