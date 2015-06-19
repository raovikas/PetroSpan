package com.petro.span.client.application.current.selection;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;

public class AdminTabSelection extends Composite {

		private static AdminTabSelectionUiBinder uiBinder = GWT
				.create(AdminTabSelectionUiBinder.class);


	public @UiField ScrollPanel scrollPanel;
	public @UiField Grid selectionGrid;


	interface AdminTabSelectionUiBinder extends
	UiBinder<Widget, AdminTabSelection> {
	}

	public AdminTabSelection() {
		initWidget(uiBinder.createAndBindUi(this));
		
		selectionGrid.resize(3, 16);
	}



}
