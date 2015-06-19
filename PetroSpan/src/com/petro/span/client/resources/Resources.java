package com.petro.span.client.resources;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.ImageResource;

public interface Resources extends ClientBundle{

	
	public interface Style extends CssResource{
		String logout();
		String cursorPointer();
		String imgLogo();
		String pageBackgroundView();
		String loginwrap();
		String loginButton();
		String inline();
		String  success();
		String success_img();
		String failure();
	}
		
		
		
		
	
	Style style();
	
	ImageResource logo();
	ImageResource load();
	ImageResource collapse();
	ImageResource expand();
	ImageResource icon_print();
	ImageResource Bg_logo();
	ImageResource module_icon_login();
	ImageResource user();
	ImageResource unauthorized();
	ImageResource success_icon();
}
