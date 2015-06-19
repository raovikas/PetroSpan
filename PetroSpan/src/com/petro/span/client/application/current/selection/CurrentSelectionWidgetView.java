package com.petro.span.client.application.current.selection;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimpleLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.petro.span.client.BPSSUtils;
import com.petro.span.client.application.common.filter.DefaultCommonFilter;
import com.petro.span.client.application.common.filter.OperationalCommonFilter;
import com.petro.span.client.place.NameTokens;
import com.petro.span.shared.FilterSelectionModel;
import com.petro.span.shared.RegisteredUser;
import com.petro.span.shared.TabSelectionModel;

class CurrentSelectionWidgetView extends ViewWithUiHandlers<CurrentSelectionWidgetUiHandlers> implements CurrentSelectionWidgetPresenter.MyView {
	interface Binder extends UiBinder<Widget, CurrentSelectionWidgetView> {
	}

	private List<RegisteredUser> adminUserDetailSelectionList = new ArrayList<RegisteredUser>();
	private List<TabSelectionModel> adminTabSelectionList = new ArrayList<TabSelectionModel>();
	private List<FilterSelectionModel> adminFiltersSelectionList = new ArrayList<FilterSelectionModel>();

	@Inject
	BPSSUtils bpssUtils;

	@UiField SimpleLayoutPanel currentSelectionLayout;

	AdminTabSelection adminTabSelection = new AdminTabSelection();
	@UiField(provided = true) OtherTabSelection otherTabSelection;

	@Inject
	CurrentSelectionWidgetView(Binder uiBinder,OtherTabSelection otherTabSelection) {
		this.otherTabSelection = otherTabSelection;
		initWidget(uiBinder.createAndBindUi(this));
		otherTabSelection.selectionGrid.resize(2, 12);
	}

	DefaultCommonFilter defaultFilter;
	OperationalCommonFilter operationalFilter;


	@Override
	public void displayCurrentSelection(Object selection) {
		otherTabSelection.selectionGrid.clear();
		System.out.println("displayCurrentSelection");
		if(selection instanceof DefaultCommonFilter)	{
			defaultFilter = (DefaultCommonFilter)selection;
			System.out.println(" instanceof DefaultCommonFilter");
			List<String> list = defaultFilter.defaultSelectionList;
			System.out.println("currentSelectionList "+list.size());
			for (int i = 0; i < list.size(); i++) {
				if(i<=11)
					otherTabSelection.selectionGrid.setWidget(0, i,new Label(list.get(i)));
				else
					otherTabSelection.selectionGrid.setWidget(1, i-12,new Label(list.get(i)));	
			}

		}

		else if(selection instanceof OperationalCommonFilter){
			operationalFilter = (OperationalCommonFilter)selection;
			System.out.println(" instanceof OperationalCommonFilter");
			List<String> list = operationalFilter.operationalSelectionList;
			System.out.println("currentSelectionList "+list.size());
			for (int i = 0; i < list.size(); i++) {
				if(i<=11)
					otherTabSelection.selectionGrid.setWidget(0, i,new Label(list.get(i)));
				else
					otherTabSelection.selectionGrid.setWidget(1, i-12,new Label(list.get(i)));	
			}
		}
	}





	List<String> list = null;

	@Override
	public void clearCurrentSelectionPanel(String placeToken) {
		
		if(placeToken.equalsIgnoreCase(NameTokens.adminArea)){
			currentSelectionLayout.setWidget(adminTabSelection);
		}else
			currentSelectionLayout.setWidget(otherTabSelection);
		

		otherTabSelection.selectionGrid.clear();
		System.out.println("placeToken  "+placeToken );

		if(placeToken.equalsIgnoreCase(NameTokens.byDefault)){

			list = defaultFilter!=null ? defaultFilter.defaultSelectionList : null;
		}
		else if(placeToken.equalsIgnoreCase(NameTokens.operational))	{

			list = operationalFilter !=null ? operationalFilter.operationalSelectionList : null;
		}

		if(list!=null)
			for (int i = 0; i < list.size(); i++) {
				if(i<=11)
					otherTabSelection.selectionGrid.setWidget(0, i,new Label(list.get(i)));
				else
					otherTabSelection.selectionGrid.setWidget(1, i-12,new Label(list.get(i)));	
			}

	}


	@Override
	public void displayAdminCurrentSelection(Object data) {
		if(data instanceof RegisteredUser){


			int cellCount = adminUserDetailSelectionList.size();

			for (int i = 0; i < cellCount; i++) {
				adminTabSelection.selectionGrid.clearCell(0, i+1);

			}

			adminTabSelection.selectionGrid.setWidget(0, 0, new InlineLabel("Selected LoginName:"));
			RegisteredUser registeredUser = (RegisteredUser) data;
			bpssUtils.checklistUpdate(registeredUser.IsSelected(),registeredUser,adminUserDetailSelectionList);
			System.out.println("adminSelectionList.size() "+adminUserDetailSelectionList.size());
			for (int i = 0; i < adminUserDetailSelectionList.size(); i++) {

				adminTabSelection.selectionGrid.setWidget(0, i+1, new InlineLabel(adminUserDetailSelectionList.get(i).getLoginName()));
				System.out.println("adminSelectionList.get(i).getLoginName()  "+adminUserDetailSelectionList.get(i).getLoginName());
			}
		}

		else if(data instanceof FilterSelectionModel){

			int cellCount = adminFiltersSelectionList.size();

			for (int i = 0; i < cellCount; i++) {
				adminTabSelection.selectionGrid.clearCell(1, i+1);

			}

			adminTabSelection.selectionGrid.setWidget(1, 0, new InlineLabel("Selected Filters:"));
			FilterSelectionModel filterSelectionModel = (FilterSelectionModel) data;
			bpssUtils.checklistUpdate(filterSelectionModel.isFilterCheck(),filterSelectionModel,adminFiltersSelectionList);
			System.out.println("adminFiltersSelectionList.size() "+adminFiltersSelectionList.size());
			for (int i = 0; i < adminFiltersSelectionList.size(); i++) {

				adminTabSelection.selectionGrid.setWidget(1, i+1, new InlineLabel(adminFiltersSelectionList.get(i).getFilterName()));
				System.out.println("adminFiltersSelectionList.get(i).getFilterName()  "+adminFiltersSelectionList.get(i).getFilterName());
			}
		}

		else if(data instanceof TabSelectionModel){

			int cellCount = adminTabSelectionList.size();

			for (int i = 0; i < cellCount; i++) {
				adminTabSelection.selectionGrid.clearCell(2, i+1);
			}
			adminTabSelection.selectionGrid.setWidget(2, 0, new InlineLabel("Selected Tabs:"));

			TabSelectionModel tabSelectionModel = (TabSelectionModel) data;
			bpssUtils.checklistUpdate(tabSelectionModel.isTabCheck(),tabSelectionModel,adminTabSelectionList);
			System.out.println("adminTabSelectionList.size() "+adminTabSelectionList.size());

			for (int i = 0; i < adminTabSelectionList.size(); i++) {

				adminTabSelection.selectionGrid.setWidget(2, i+1, new InlineLabel(adminTabSelectionList.get(i).getTabName()));
				System.out.println("adminTabSelectionList.get(i).getTabName()  "+adminTabSelectionList.get(i).getTabName());
			}

		}
	}



}

