package com.petro.span.client.application.current.selection;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class OtherTabSelection extends Composite {

	
	public @UiField ScrollPanel scrollPanel;
	public @UiField Grid selectionGrid;
	
//	private static OtherTabSelectionUiBinder uiBinder = GWT
//			.create(OtherTabSelectionUiBinder.class);

	interface OtherTabSelectionUiBinder extends
			UiBinder<Widget, OtherTabSelection> {
	}

	@Inject
	public OtherTabSelection(OtherTabSelectionUiBinder uiBinder) {
		initWidget(uiBinder.createAndBindUi(this));
	}

	

	
}
