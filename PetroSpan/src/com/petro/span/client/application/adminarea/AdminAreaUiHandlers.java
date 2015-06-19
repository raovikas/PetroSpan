package com.petro.span.client.application.adminarea;

import com.gwtplatform.mvp.client.UiHandlers;
import com.petro.span.shared.AdminMasterModel;

interface AdminAreaUiHandlers extends UiHandlers {

	 void freezeUsersAccessRights(AdminMasterModel masterModel, String token);

	void fireGridRecordSelectionEvent(Object object);


}