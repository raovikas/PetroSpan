package com.petro.span.client;


import com.google.gwt.event.logical.shared.ResizeEvent;
import com.google.gwt.event.logical.shared.ResizeHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.inject.Inject;
import com.petro.span.client.resources.Resources;

public class Loader {

	private  PopupPanel popUp = new PopupPanel();
	
	@Inject
	public Loader(Resources resources){
		popUp.setWidget(new Image(resources.load()));
		popUp.setGlassEnabled(true);
		popUp.center();
		popUp.setSize("50px", "50px");
		popUp.setStyleName("pop-style");
		popUp.hide();


		Window.addResizeHandler(new ResizeHandler() {

			@Override
			public void onResize(ResizeEvent event) {
				if(popUp.isShowing())
					popUp.center();
			}
		});
	}

	public void show() {
		popUp.center();
		popUp.show();

	};

	public void hide(){
		popUp.hide();
	}

}
