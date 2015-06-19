package com.petro.span.client.application.adminarea;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import com.google.gwt.cell.client.Cell.Context;
import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.SelectionCell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.Header;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import com.petro.span.client.BPSSUtils;
import com.petro.span.client.DataService;
import com.petro.span.client.resources.DataGridResource;
import com.petro.span.shared.AdminMasterModel;
import com.petro.span.shared.RegisteredUser;
import com.petro.span.shared.UserInfo;

class AdminAreaView extends ViewWithUiHandlers<AdminAreaUiHandlers> implements AdminAreaPresenter.MyView {
	interface Binder extends UiBinder<Widget, AdminAreaView> {
	}

	DataGridResource res = GWT.create(DataGridResource.class);

	@UiField (provided = true) DataGrid<RegisteredUser> dataGrid = new DataGrid<RegisteredUser>(30000,res);

	List<RegisteredUser> registeredUserList = new ArrayList<RegisteredUser>();

	Map<String,String> roleIDMap = new HashMap<String, String>();

	List<String> roleIDList;
	

	@UiField Button saveButton;

	Column<RegisteredUser,Boolean>  isSelected;

	TextColumn<RegisteredUser> loginName;

	TextColumn<RegisteredUser> userName;

	TextColumn<RegisteredUser> emailAddress;

	TextColumn<RegisteredUser> gender;

	Column<RegisteredUser,String> roleId;


	Header<Boolean> checkHeader ;


	DataService<RegisteredUser> dataService;

	@UiField(provided = true) AdminFilterSelection	adminFilterSelection;

	List<RegisteredUser> checkList = new ArrayList<RegisteredUser>();

	Boolean isHeaderChecked = false;

	@Inject
	private BPSSUtils bpssUtils;
	
	
	@Inject
	private UserInfo user;

	/**
	 * An html string representation of a checked input box.
	 */
	private final SafeHtml INPUT_CHECKED = SafeHtmlUtils.fromSafeConstant("<input type=\"checkbox\" tabindex=\"-1\" checked/>");


	/**
	 * An html string representation of an unchecked input box.
	 */
	private final SafeHtml INPUT_UNCHECKED = SafeHtmlUtils.fromSafeConstant("<input type=\"checkbox\" tabindex=\"-1\"/>");


	@Inject
	AdminAreaView(Binder uiBinder, AdminFilterSelection	adminFilterSelection,DataService<RegisteredUser> dataService) {
		this.adminFilterSelection = adminFilterSelection;
		initWidget(uiBinder.createAndBindUi(this));
		adminFilterSelection.setAdminAreaViewRef(this);
		this.dataService = dataService;
		roleIDMap.put("Role_USER", "Normal User");
		roleIDMap.put("Role_ADMIN", "Administrator");
		roleIDMap.put("Role_PRIVILAGE", "Privilaged User");

		roleIDList = new ArrayList<String>(roleIDMap.values());

		createGrid();
	}


	@Override
	public void createGrid() {

		checkHeader = new Header<Boolean>(new CheckboxCell(true, true)) {

			@Override
			public Boolean getValue() {
				return isHeaderChecked;
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


		checkHeader.setUpdater(new ValueUpdater<Boolean>() {

			@Override
			public void update(Boolean value) {
				isHeaderChecked = value;
				System.out.println("isHeaderChecked "+isHeaderChecked);
				for(RegisteredUser model : dataGrid.getVisibleItems()){
					model.setIsSelected(isHeaderChecked);
					bpssUtils.checklistUpdate(value,model,checkList);
					getUiHandlers().fireGridRecordSelectionEvent(model);
					dataGrid.redraw();
				}
			}
		}
				);

		isSelected = new Column<RegisteredUser, Boolean>(new CheckboxCell(true, true)) {

			@Override
			public Boolean getValue(RegisteredUser object) {
				return object.IsSelected();
			}
		};

		isSelected.setFieldUpdater(new FieldUpdater<RegisteredUser, Boolean>() {

			@Override
			public void update(int index, RegisteredUser object, Boolean value) {
				object.setIsSelected(value);
				bpssUtils.checklistUpdate(value,object,checkList);
				getUiHandlers().fireGridRecordSelectionEvent(object);

			}
		});


		loginName = new TextColumn<RegisteredUser>() {

			@Override
			public String getValue(RegisteredUser object) {
				return object.getLoginName();
			}
		};


		userName = new TextColumn<RegisteredUser>() {

			@Override
			public String getValue(RegisteredUser object) {
				return object.getUserName();
			}
		};

		emailAddress = new TextColumn<RegisteredUser>() {

			@Override
			public String getValue(RegisteredUser object) {
				return object.getEmailAddress();
			}
		};  


		gender = new TextColumn<RegisteredUser>() {

			@Override
			public String getValue(RegisteredUser object) {
				return object.getGender();
			}
		};


		roleId = new Column<RegisteredUser, String>(new SelectionCell(roleIDList)) {

			@Override
			public String getValue(RegisteredUser object) {
				return   roleIDMap.get(object.getRoleId());
			}
		};


		roleId.setFieldUpdater(new FieldUpdater<RegisteredUser, String>() {

			@Override
			public void update(int index, RegisteredUser object, String value) {
				for(String key :roleIDMap.keySet())
					if(roleIDMap.get(key).equals(value))
				object.setRoleId(key);
			}
		});


		dataGrid.addColumn(loginName, "LoginName");
		dataGrid.addColumn(userName, "UserName");
		dataGrid.addColumn(emailAddress, "Email");
		dataGrid.addColumn(gender, "Gender");
		dataGrid.addColumn(roleId, "RoleID");
		dataGrid.addColumn(isSelected,checkHeader);


		if(!dataService.getDataProvider().getDataDisplays().contains(dataGrid)){
			dataService.addDataDisplay(dataGrid);
		}
	}





	@Override
	public void displayRegisteredUserDetails(List<RegisteredUser> result) {
//		dataService.clear(registeredUserList);
		registeredUserList.addAll(result);
		dataService.refreshDataList(registeredUserList);
	}


	@Override
	public void addHandlers() {
		saveButton.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				
				
				AdminMasterModel masterModel = new AdminMasterModel();
				masterModel.setCheckList(checkList);
				masterModel.setTabsCheckList(adminFilterSelection.tabsCheckList);
				masterModel.setFiltersCheckList(adminFilterSelection.filtersCheckList);
				
			getUiHandlers().freezeUsersAccessRights(masterModel,user.getToken());
			}
		});
	}


	@Override
	public void displayMessage(String result) {
		Window.alert(result);
		
	}


	
	public void getDataForGridRecordSelectionEvent(Object object) {
		getUiHandlers().fireGridRecordSelectionEvent(object);
	}


	@Override
	public void clear() {
		dataService.clear(registeredUserList);
	}

}