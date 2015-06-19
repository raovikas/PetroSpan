package com.petro.span.client.application.tabbar;


import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import com.google.gwt.user.client.ui.TabBar;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;
import com.petro.span.client.place.NameTokens;
import com.petro.span.shared.CurrentUser;
import com.petro.span.shared.PrivilegedTabModel;

class TabBarView extends ViewWithUiHandlers<TabBarUiHandlers> implements TabBarPresenter.MyView {
	interface Binder extends UiBinder<Widget, TabBarView> {
	}

	@UiField
	SimpleLayoutPanel main;

	@UiField TabBar tabBar;

	@Inject
	PlaceManager placeManager;
	//	MyPlaceManager placeManager;

	@Inject
	CurrentUser currentUser;

	@Inject
	TabBarView(Binder uiBinder) {
		initWidget(uiBinder.createAndBindUi(this));
	}





	@Override
	public void initialize() {
		tabBar.addTab("Default");
		tabBar.addTab("Operational");
		//		tabBar.addTab("Technical");
		//		tabBar.addTab("Commercial");
		//		tabBar.addTab("Forecast");
		//		tabBar.addTab("Production");
		//		tabBar.addTab("AdminArea");


	}


	@Override
	public void addAdminTab() {



		int count = tabBar.getTabCount();
		for (int i = count-1; i >1; i--) {
			System.out.println("remove tab"+i);
			System.out.println("tabHTML"+tabBar.getTabHTML(i));
			System.out.println("tabcount"+tabBar.getTabCount());
			tabBar.removeTab(i);
		}

		System.out.println("addAdminTab "+currentUser.getRoles().contains("Role_ADMIN"));


		if(currentUser.getRoles().contains("Role_PRIVILAGE")){

			System.out.println("Role_PRIVILAGE");

			PrivilegedTabModel  tabModel= currentUser.getTabModel();

			if(tabModel.getTechnical().equalsIgnoreCase("true") || currentUser.getRoles().contains("Role_ADMIN"))
				tabBar.insertTab("Technical", tabBar.getTabCount());

			if(tabModel.getCommercial().equalsIgnoreCase("true") || currentUser.getRoles().contains("Role_ADMIN"))
				tabBar.insertTab("Commercial", tabBar.getTabCount());

			if(tabModel.getForecast().equalsIgnoreCase("true") || currentUser.getRoles().contains("Role_ADMIN"))
				tabBar.insertTab("Forecast", tabBar.getTabCount());

			if(tabModel.getProduction().equalsIgnoreCase("true") || currentUser.getRoles().contains("Role_ADMIN"))
				tabBar.insertTab("Production", tabBar.getTabCount());

		}


		if(currentUser.getRoles().contains("Role_ADMIN")){
			System.out.println("Role_ADMIN");
			tabBar.insertTab("AdminArea", tabBar.getTabCount());
		}
		//		if(currentUser.getRoles().contains("Role_ADMIN")){
		//			if(tabBar.getTabCount() != 7){
		//				tabBar.insertTab("AdminArea", 6);
		//				
		//				
		//			}
		//		}	else {
		//			if(tabBar.getTabCount() == 7){
		//				tabBar.removeTab(6);
		//			}
		//		}
		tabBarSelectionHandler();
	}

	@Override
	public void addHandler() {
		tabBar.addSelectionHandler(new SelectionHandler<Integer>() {

			@Override
			public void onSelection(SelectionEvent<Integer> event) {
				System.out.println("tab bar html for tab 0"+tabBar.getTabHTML(0));
				if(tabBar.getTabHTML(event.getSelectedItem()).equalsIgnoreCase( NameTokens.byDefault)){

					PlaceRequest defaultPlaceRequest = new PlaceRequest.Builder().nameToken(NameTokens.byDefault).build();
					placeManager.revealPlace(defaultPlaceRequest);
					getUiHandlers().clearSelectionPanel(NameTokens.byDefault);

				}else if(tabBar.getTabHTML(event.getSelectedItem()).equalsIgnoreCase( NameTokens.operational)){

					PlaceRequest operationPlaceRequest = new PlaceRequest.Builder().nameToken(NameTokens.operational).build();
					placeManager.revealPlace(operationPlaceRequest);
					getUiHandlers().clearSelectionPanel(NameTokens.operational);

				}else if(tabBar.getTabHTML(event.getSelectedItem()).equalsIgnoreCase( NameTokens.technical)){

					PlaceRequest operationPlaceRequest = new PlaceRequest.Builder().nameToken(NameTokens.technical).build();
					placeManager.revealPlace(operationPlaceRequest);
					getUiHandlers().clearSelectionPanel(NameTokens.technical);

				}
				else if(tabBar.getTabHTML(event.getSelectedItem()).equalsIgnoreCase( NameTokens.commercial)){

					PlaceRequest operationPlaceRequest = new PlaceRequest.Builder().nameToken(NameTokens.commercial).build();
					placeManager.revealPlace(operationPlaceRequest);
					getUiHandlers().clearSelectionPanel(NameTokens.commercial);

				}

				else if(tabBar.getTabHTML(event.getSelectedItem()).equalsIgnoreCase( NameTokens.forecast)){

					PlaceRequest operationPlaceRequest = new PlaceRequest.Builder().nameToken(NameTokens.forecast).build();
					placeManager.revealPlace(operationPlaceRequest);
					getUiHandlers().clearSelectionPanel(NameTokens.forecast);

				}

				else if(tabBar.getTabHTML(event.getSelectedItem()).equalsIgnoreCase( NameTokens.production)){

					PlaceRequest operationPlaceRequest = new PlaceRequest.Builder().nameToken(NameTokens.production).build();
					placeManager.revealPlace(operationPlaceRequest);
					getUiHandlers().clearSelectionPanel(NameTokens.production);

				}
				else if(tabBar.getTabHTML(event.getSelectedItem()).equalsIgnoreCase(NameTokens.adminArea)){

					PlaceRequest operationPlaceRequest = new PlaceRequest.Builder().nameToken(NameTokens.adminArea).build();
					placeManager.revealPlace(operationPlaceRequest);
					getUiHandlers().clearSelectionPanel(NameTokens.adminArea);

				}

				//				if(tabBar.getTabCount()==2){
				//					if(event.getSelectedItem() == 0) {
				//						PlaceRequest defaultPlaceRequest = new PlaceRequest.Builder().nameToken(NameTokens.byDefault).build();
				//						placeManager.revealPlace(defaultPlaceRequest);
				//						getUiHandlers().clearSelectionPanel(NameTokens.byDefault);
				//					}
				//
				//					else if(event.getSelectedItem() == 1){
				//						PlaceRequest operationPlaceRequest = new PlaceRequest.Builder().nameToken(NameTokens.operational).build();
				//						placeManager.revealPlace(operationPlaceRequest);
				//						getUiHandlers().clearSelectionPanel(NameTokens.operational);
				//					}
				//				}else if(tabBar.getTabCount() == 3){
				//					if(event.getSelectedItem() == 0) {
				//						PlaceRequest defaultPlaceRequest = new PlaceRequest.Builder().nameToken(NameTokens.byDefault).build();
				//						placeManager.revealPlace(defaultPlaceRequest);
				//						getUiHandlers().clearSelectionPanel(NameTokens.byDefault);
				//					}
				//
				//					else if(event.getSelectedItem() == 1){
				//						PlaceRequest operationPlaceRequest = new PlaceRequest.Builder().nameToken(NameTokens.operational).build();
				//						placeManager.revealPlace(operationPlaceRequest);
				//						//						getUiHandlers().clearSelectionPanel();
				//						getUiHandlers().clearSelectionPanel(NameTokens.operational);
				//					}
				//
				//					else if(event.getSelectedItem() == 2){
				//						PlaceRequest adminPlaceRequest = new PlaceRequest.Builder().nameToken(NameTokens.adminArea).build();
				//						placeManager.revealPlace(adminPlaceRequest);
				//						getUiHandlers().clearSelectionPanel(NameTokens.adminArea);
				//
				//					}
				//
				//				}else{
				//
				//					if(event.getSelectedItem() == 0) {
				//						PlaceRequest defaultPlaceRequest = new PlaceRequest.Builder().nameToken(NameTokens.byDefault).build();
				//						placeManager.revealPlace(defaultPlaceRequest);
				//						getUiHandlers().clearSelectionPanel(NameTokens.byDefault);
				//					}
				//
				//					else if(event.getSelectedItem() == 1){
				//						PlaceRequest operationPlaceRequest = new PlaceRequest.Builder().nameToken(NameTokens.operational).build();
				//						placeManager.revealPlace(operationPlaceRequest);
				//						//					getUiHandlers().clearSelectionPanel();
				//						getUiHandlers().clearSelectionPanel(NameTokens.operational);
				//					}
				//
				//					else if(event.getSelectedItem() == 2){
				//						PlaceRequest technicalPlaceRequest = new PlaceRequest.Builder().nameToken(NameTokens.technical).build();
				//						placeManager.revealPlace(technicalPlaceRequest);
				//
				//						//					getUiHandlers().clearSelectionPanel(event.getSelectedItem());
				//						getUiHandlers().clearSelectionPanel(NameTokens.operational);
				//					}
				//
				//
				//					else if(event.getSelectedItem() == 3){
				//						PlaceRequest commercialPlaceRequest = new PlaceRequest.Builder().nameToken(NameTokens.commercial).build();
				//						placeManager.revealPlace(commercialPlaceRequest);
				//						//					getUiHandlers().clearSelectionPanel();
				//						getUiHandlers().clearSelectionPanel(NameTokens.operational);
				//
				//					}
				//
				//
				//					else if(event.getSelectedItem() == 4){
				//						PlaceRequest forecastPlaceRequest = new PlaceRequest.Builder().nameToken(NameTokens.forecast).build();
				//						placeManager.revealPlace(forecastPlaceRequest);
				//						getUiHandlers().clearSelectionPanel(NameTokens.operational);
				//
				//					}
				//
				//
				//					else if(event.getSelectedItem() == 5){
				//						PlaceRequest productionPlaceRequest = new PlaceRequest.Builder().nameToken(NameTokens.production).build();
				//						placeManager.revealPlace(productionPlaceRequest);
				//						getUiHandlers().clearSelectionPanel(NameTokens.production);
				//
				//					}
				//					else if(event.getSelectedItem() == 6){
				//						PlaceRequest adminPlaceRequest = new PlaceRequest.Builder().nameToken(NameTokens.adminArea).build();
				//						placeManager.revealPlace(adminPlaceRequest);
				//						getUiHandlers().clearSelectionPanel(NameTokens.adminArea);
				//
				//					}

				//				}

			}
		});
	}





	@Override
	public void tabBarSelectionHandler() {
		
		System.out.println("tabbar count "+tabBar.getTabCount());
		for (int count = 0; count < tabBar.getTabCount(); count++) {
			System.out.println("hash value "+Window.Location.getHash());
			if(tabBar.getTabHTML(count).equalsIgnoreCase(Window.Location.getHash().substring(1))){
				tabBar.selectTab(count);
				System.out.println("inside tabbar match count"+count);
				return;
			}
			System.out.println("tabBarSelectionHandler");
		}




		//		if(tabBar.getTabCount()==2){
		//			if(Window.Location.getHash().contains(NameTokens.byDefault)){
		//				tabBar.selectTab(0);
		//			}
		//
		//			else if(Window.Location.getHash().contains(NameTokens.operational)){
		//				tabBar.selectTab(1);
		//
		//			}
		//		}
		//
		//		else if(tabBar.getTabCount() == 3){
		//			if(Window.Location.getHash().contains(NameTokens.byDefault)){
		//				tabBar.selectTab(0);
		//			}
		//
		//			else if(Window.Location.getHash().contains(NameTokens.operational)){
		//				tabBar.selectTab(1);
		//
		//			}
		//			else if(Window.Location.getHash().contains(NameTokens.adminArea)){
		//				tabBar.selectTab(3);
		//			}
		//		}else{
		//			if(Window.Location.getHash().contains(NameTokens.operational)){
		//				tabBar.selectTab(1);
		//
		//
		//			}else if(Window.Location.getHash().contains(NameTokens.technical)){
		//				tabBar.selectTab(2);
		//
		//			}else if(Window.Location.getHash().contains(NameTokens.commercial)){
		//				tabBar.selectTab(3);
		//
		//			}else if(Window.Location.getHash().contains(NameTokens.forecast)){
		//				tabBar.selectTab(4);
		//			}
		//
		//			else if(Window.Location.getHash().contains(NameTokens.production)){
		//				tabBar.selectTab(5);
		//			}
		//
		//			else if(Window.Location.getHash().contains(NameTokens.adminArea)){
		//				tabBar.selectTab(6);
		//			}
		//
		//			else if(Window.Location.getHash().contains(NameTokens.byDefault)){
		//				tabBar.selectTab(0);
		//				//			getUiHandlers().clearSelectionPanel(NameTokens.byDefault);
		//			}
		//		}
		//



	}





}



