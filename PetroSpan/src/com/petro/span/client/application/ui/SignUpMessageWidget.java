package com.petro.span.client.application.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class SignUpMessageWidget extends Composite {

	private static SignUpMessageWidgetUiBinder uiBinder = GWT
			.create(SignUpMessageWidgetUiBinder.class);

	interface SignUpMessageWidgetUiBinder extends
			UiBinder<Widget, SignUpMessageWidget> {
	}

	
	
	public SignUpMessageWidget() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	
}
