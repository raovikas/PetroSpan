package com.petro.span.client.application.bydefault;

import java.util.ArrayList;

import com.google.gwt.maps.client.LoadApi;
import com.google.gwt.maps.client.LoadApi.LoadLibrary;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.googlecode.gwt.charts.client.BarChartWidget;
import com.googlecode.gwt.charts.client.ChartLoader;
import com.googlecode.gwt.charts.client.ChartPackage;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.UseGatekeeper;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.petro.span.client.AuthUtil;
import com.petro.span.client.LoggedInGatekeeper;
import com.petro.span.client.application.ApplicationPresenter;
import com.petro.span.client.application.common.filter.DefaultCommonFilter;
import com.petro.span.client.application.event.GlobalDataEvent;
import com.petro.span.client.application.ui.FusionHeatMapWidget;
import com.petro.span.client.application.ui.FusionTablesMapWidget;
import com.petro.span.client.place.NameTokens;
import com.petro.span.shared.UserInfo;
public class DefaultTabPresenter extends Presenter<DefaultTabPresenter.MyView, DefaultTabPresenter.MyProxy> implements DefaultTabUiHandlers {
	
    interface MyView extends View , HasUiHandlers<DefaultTabUiHandlers> {
    	void initialize();


		void addHandlers();



		void addMapView(Widget mapWidget);

		void addChartView(BarChartWidget drillerChart, String chart);




    }
    
  
   

    @ProxyCodeSplit
    @NameToken(NameTokens.byDefault)
    @UseGatekeeper(LoggedInGatekeeper.class)
    interface MyProxy extends ProxyPlace<DefaultTabPresenter> {
    }
    
    @Inject
    DefaultTabPresenter(EventBus eventBus,
    		MyView view,
    		MyProxy proxy,
    		AuthUtil authUtil,
    		UserInfo user
    		) {
    	super(eventBus, view, proxy, ApplicationPresenter.SLOT_SetMainContent);
    	
    	
    	getView().setUiHandlers(this);
    	
    	System.out.println("DefaultPresenter");
    }
    
    protected void onBind() {
    	super.onBind();
    	getView().initialize();
    	getView().addHandlers();
    }
    
    
    @Override
    protected void onReveal() {
    	super.onReveal();

    }

  
    private void loadMapApi(final String searchCriteria, final String currentTableID) {
    	System.out.println("loadMapApi");
    	boolean sensor = true;
    	
    	// load all the libs for use in the maps
    	ArrayList<LoadLibrary> loadLibraries = new ArrayList<LoadApi.LoadLibrary>();
    	
    	loadLibraries.add(LoadLibrary.VISUALIZATION);
    	
    	Runnable onLoad = new Runnable() {
    		@Override
    		public void run() {
    			drawMap(searchCriteria,currentTableID);
    		}
    	};
    	
    	LoadApi.go(onLoad, loadLibraries, sensor);
    }
    
    
    private void loadVisualizationApi(final String searchCreteria,final String currentTableID) {
    	
    	
    	
    	// Create the API Loader
    	ChartLoader chartLoader = new ChartLoader(ChartPackage.CORECHART);
    	chartLoader.loadApi(new Runnable() {
    		
    		@Override
    		public void run() {
    			drawChart(searchCreteria,currentTableID);
    		}
    	});
    }
    
    
    
    protected void drawChart(String searchCriteria, String currentTableID) {
    	drawDrillerBarChart(searchCriteria,currentTableID);
    	drawAPIBarChart(searchCriteria,currentTableID);
    }
    
    
    
    
    protected void drawMap(String searchCriteria, String currentTableID) {
    	drawFusionHeatMap(searchCriteria,currentTableID);
    	drawFusionTableMap(searchCriteria,currentTableID);
    	
    }
    
    
    
    private void drawDrillerBarChart(String searchCriteria, String currentTableID) {
    	
    	String queryText = "SELECT Driller, 'Vertical Depth' FROM "+ currentTableID +" Where "+searchCriteria+" ";
    	System.out.println("currentTableID "+currentTableID);
    	String title = "Sample Petrozoom Chart 1";
    	String ytitle = "Driller";
    	String vtitle = "Vertical Depth";
//    	BarChartWidget drillerChart = new BarChartWidget(queryText,title);
    	addChartWidget(new BarChartWidget(queryText,title,ytitle,vtitle),"Driller");
    }
    
    
    
    
    
    private void drawAPIBarChart(String searchCriteria, String currentTableID) {
    	String queryText = "SELECT State, 'Drilling time'  FROM "+ currentTableID +" Where "+searchCriteria+" ";
    	System.out.println("currentTableID "+currentTableID);
    	String title = "Sample Petrozoom Chart 2";
    	String ytitle = "State";
    	String vtitle = "Drilling time";
//    	BarChartWidget drillerChart = new BarChartWidget(queryText,title);
    	addChartWidget(new BarChartWidget(queryText,title,ytitle,vtitle),"API");
    }
    
    
    
    private void drawFusionTableMap(String searchCriteria, String currentTableID) {
    	FusionTablesMapWidget fusionTableMap = new FusionTablesMapWidget(searchCriteria,currentTableID);
    	addMapWidget(fusionTableMap);
    }
    
    
    private void drawFusionHeatMap(String searchCriteria, String currentTableID) {
    	FusionHeatMapWidget heatMap = new FusionHeatMapWidget(searchCriteria,currentTableID);
    	addMapWidget(heatMap);
    }
    
    
    /**
     * Add the widget to the demos
     * 
     * @param widget map
     */
    private void addMapWidget(Widget widget) {
    	getView().addMapView(widget);
    }
    
    private void addChartWidget(BarChartWidget drillerChart, String chart) {
    	getView().addChartView(drillerChart,chart);
    }
    
    
    @Override
    public void LoadMap(String buildWhereClause, String currentTableID) {
    	loadMapApi(buildWhereClause,currentTableID);
    }
    
    
    @Override
    public void LoadChart(String searchCreteria, String currentTableID) {
    	loadVisualizationApi(searchCreteria,currentTableID);
    }

	
	@Override
	public void fireSelectionEvent(DefaultCommonFilter byDefaultFilter) {
		GlobalDataEvent.fire(this, byDefaultFilter);
	}

	
}

	




    
    
    
    










    