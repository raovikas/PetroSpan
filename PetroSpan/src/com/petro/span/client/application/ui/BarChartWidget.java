package com.petro.span.client.application.ui;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import com.googlecode.gwt.charts.client.DataTable;
//import com.google.gwt.visualization.client.DataTable;
//import com.google.gwt.visualization.client.Query;
//import com.google.gwt.visualization.client.Query.Callback;
//import com.google.gwt.visualization.client.QueryResponse;
//import com.google.gwt.visualization.client.visualizations.BarChart.Options;
//import com.google.gwt.visualization.client.visualizations.corechart.BarChart;
//import com.google.gwt.visualization.client.visualizations.corechart.Options;
import com.googlecode.gwt.charts.client.corechart.BarChart;
import com.googlecode.gwt.charts.client.corechart.BarChartOptions;
import com.googlecode.gwt.charts.client.options.HAxis;
import com.googlecode.gwt.charts.client.options.TextPosition;
import com.googlecode.gwt.charts.client.options.VAxis;
import com.googlecode.gwt.charts.client.query.Query;
import com.googlecode.gwt.charts.client.query.QueryCallback;
import com.googlecode.gwt.charts.client.query.QueryResponse;

public class BarChartWidget extends ResizeComposite {

	public SimpleLayoutPanel pWidget;
	public BarChart barChart ;


	public BarChartWidget(String queryText, String title, String ytitle, String vtitle){
		pWidget = new SimpleLayoutPanel();
		initWidget(pWidget);

		draw(queryText,title,ytitle,vtitle);
	}



	//	private void draw(String queryText, final String title) {
	//		
	//		String dataSourceUrl = "https://www.google.com/fusiontables/gvizdata?tq=";
	//		
	//		// Create a query to go to the above URL.
	//		Query query = Query.create(dataSourceUrl);
	//		
	//		query.setQuery(queryText);
	//		query.setRefreshInterval(5);
	//		query.
	//		query.send(new Callback() {
	//			
	//			@Override
	//			public void onResponse(QueryResponse response) {
	//				if(response.isError())
	//				  Window.alert(response.getMessage());
	//				else{
	//					// Get the data from the QueryResponse.
	//					DataTable data = response.getDataTable();
	//				
	//				 // Create the Options object.
	//		          Options options = createOptions(title);
	//		          
	//		       // Create a PieChart and add it to a panel.
	//		          barChart = new BarChart(data, options);
	//		          pWidget.clear();
	//		          pWidget.add(barChart);
	////		          pWidget.setSize("500px", "300px");
	//				}
	//					
	//			}
	//		});
	//	}
	//	
	//	
	//	
	//	
	//
	//	protected Options createOptions(String title) {
	//		Options options = Options.create();
	//		options.setTitle(title);
	//		return options;
	//	}



	private void draw(String queryText, final String title, final String ytitle, final String vtitle) {

		String dataSourceUrl = "https://www.google.com/fusiontables/gvizdata?tq=";

		// Create a query to go to the above URL.
		Query query = Query.create(dataSourceUrl);
		System.out.println("queryText "+queryText);
		query.setQuery(queryText);
		//		query.setRefreshInterval(5);
		query.send(new QueryCallback() {

			@Override
			public void onResponse(QueryResponse queryResponse) {
				if(queryResponse.isError()){
					System.out.println("error msg "+queryResponse.getDetailedMessage());
					//					Window.alert(queryResponse.getMessage());
					Window.alert(queryResponse.getMessage());
				}
				else{
					// Get the data from the QueryResponse.
					DataTable data = queryResponse.getDataTable();

					//					 // Create the Options object.
					//			          Options options = createOptions(title);
					BarChartOptions options = createOptions(title);
					
					VAxis vAxis  = VAxis.create(ytitle);
					vAxis.setTextPosition(TextPosition.NONE);
					options.setVAxis(vAxis);
//					options.setAxisTitlesPosition(AxisTitlesPosition.NONE);
//					options.setHAxis(HAxis.create().setTextPosition(TextPosition.NONE));
					options.setHAxes(HAxis.create(vtitle));
					// Create a PieChart and add it to a panel.
					barChart = new BarChart();
					barChart.draw(data, options);

					//			          barChart = new BarChart(data, options);
					pWidget.clear();
					pWidget.add(barChart);
					//			          pWidget.setSize("500px", "300px");
				}
			}
		});

	}





	protected BarChartOptions createOptions(String title) {
		BarChartOptions options = BarChartOptions.create();
		options.setTitle(title);
		return options;
	}
}
