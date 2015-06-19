package com.petro.span.client.application.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.Widget;

public class WidgetDescription extends Composite  {

	private static WidgetDescriptionUiBinder uiBinder = GWT
			.create(WidgetDescriptionUiBinder.class);


	
	public @UiField Button resetButton;
	
	public @UiField Image expandIcon;
	public @UiField Image collapseIcon;
	public @UiField Image printIcon;

	@UiField
	MenuBar optionMenuBar ;
	@UiField MenuItem mapsItem;
	@UiField MenuItem chartOpt1Menu;
	@UiField MenuItem chartOpt2Menu;
	@UiField MenuItem chartOpt3Menu;
	@UiField MenuBar chartMenuBar;

	interface WidgetDescriptionUiBinder extends
	UiBinder<Widget, WidgetDescription> {
	}

	public WidgetDescription() {
		initWidget(uiBinder.createAndBindUi(this));
		optionMenuBar.setAnimationEnabled(true);
		optionMenuBar.setAutoOpen(true);
		
		chartMenuBar.setAnimationEnabled(true);
		chartMenuBar.setAutoOpen(true);
		
		


	}




}
