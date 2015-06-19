package com.petro.span.client.application.ui;

import com.google.gwt.maps.client.MapOptions;
import com.google.gwt.maps.client.MapTypeId;
import com.google.gwt.maps.client.MapWidget;
import com.google.gwt.maps.client.base.LatLng;
import com.google.gwt.maps.client.layers.FusionTablesLayer;
import com.google.gwt.maps.client.layers.FusionTablesLayerOptions;
import com.google.gwt.maps.client.layers.FusionTablesQuery;
import com.google.gwt.user.client.ui.ResizeComposite;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;

public class FusionTablesMapWidget extends ResizeComposite{
	public SimpleLayoutPanel pWidget;
	public MapWidget mapWidget;
	String currentTableID;
	public FusionTablesMapWidget(String searchCriteria, String currentTableID){
		pWidget = new SimpleLayoutPanel();
		this.currentTableID = currentTableID;
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
		
//		LatLng montana =  LatLng.newInstance(47.0000, 110.0000);

		MapOptions options = MapOptions.newInstance();
		options.setCenter(northDakota);
//		options.setCenter(montana);
		options.setZoom(6);
		options.setMapTypeId(MapTypeId.TERRAIN);

		mapWidget = new MapWidget(options);
		pWidget.add(mapWidget);


	}


	private void setupFusionTablesLayer(MapWidget mapWidget,String searchCriteria) {
		System.out.println("setupFusionTablesLayer");
		FusionTablesQuery query = FusionTablesQuery.newInstance();
		query.setSelect("Latitude");
		query.setFrom(currentTableID);//1iEwxr7UTGNp7TYjb4Qzjn-Ke1mjwJ8_7gaHqsKpc
		System.out.println("currentTableID "+currentTableID);
		//		query.setFrom("1iEwxr7UTGNp7TYjb4Qzjn-Ke1mjwJ8_7gaHqsKpc");
		query.setWhere(searchCriteria);

		FusionTablesLayerOptions options = FusionTablesLayerOptions.newInstance();
		options.setQuery(query);

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














