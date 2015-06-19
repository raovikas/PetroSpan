package com.petro.span.client.application.ui;

import com.google.gwt.maps.client.MapOptions;
import com.google.gwt.maps.client.MapTypeId;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.base.LatLng;
import com.google.gwt.maps.client.layers.FusionTableHeatmap;
import com.google.gwt.maps.client.layers.FusionTablesLayer;
import com.google.gwt.maps.client.layers.FusionTablesLayerOptions;
import com.google.gwt.maps.client.layers.FusionTablesQuery;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;

public class FusionHeatMapWidget extends ResizeComposite {

	public SimpleLayoutPanel pWidget;
	public MapWidget mapWidget;
	String currentTableID;


	public FusionHeatMapWidget(String searchCriteria, String currentTableID) {
		pWidget = new SimpleLayoutPanel();
		this.currentTableID  = currentTableID;
		initWidget(pWidget);

		draw(searchCriteria);
	}



	private void draw(String searchCriteria) {
		pWidget.clear();
		setupMap();
		setupFusionTablesLayer(mapWidget,searchCriteria);
	}



	private void setupMap() {
		LatLng northDakota =  LatLng.newInstance(48.161876, -102.884336);	

		MapOptions options = MapOptions.newInstance();
		options.setCenter(northDakota);
		options.setZoom(6);
		options.setMapTypeId(MapTypeId.TERRAIN);

		mapWidget = new MapWidget(options);

		pWidget.add(mapWidget);

	}


	private void setupFusionTablesLayer(MapWidget mapWidget,String searchCriteria) {
		FusionTablesQuery query = FusionTablesQuery.newInstance();
		query.setSelect("Latitude");
		query.setFrom(currentTableID);
		System.out.println("currentTableID "+currentTableID);
		//		query.setFrom("1iEwxr7UTGNp7TYjb4Qzjn-Ke1mjwJ8_7gaHqsKpc");
		query.setWhere(searchCriteria);

		FusionTablesLayerOptions options = FusionTablesLayerOptions.newInstance();
		options.setQuery(query);








		//for heatmap
		FusionTableHeatmap fusionTableHeatMap = FusionTableHeatmap.newInstance();
		fusionTableHeatMap.setEnabled(true);
		options.setHeatmap(fusionTableHeatMap);

		FusionTablesLayer layer = FusionTablesLayer.newInstance(options);
		layer.setMap(mapWidget);




	}



	public MapWidget getMapWidget() {
		return mapWidget;
	}





	@Override
	public void onResize() {
		super.onResize();

		//this method is called on map resize so that map marker comes on the map center
		mapWidget.triggerResize();
	}


}
