package com.petro.span.client.application.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.google.web.bindery.event.shared.HandlerRegistration;

public class GlobalEvent extends GwtEvent<GlobalEvent.GlobalHandler> {
   

	public static void fire(HasHandlers source,String tabNo) {
        source.fireEvent(new GlobalEvent(tabNo));
    }

    public static void fire(HasHandlers source, GlobalEvent eventInstance) {
        source.fireEvent(eventInstance);
    }

    public interface HasGlobalHandlers extends HasHandlers {
        HandlerRegistration addGlobalHandler(GlobalHandler handler);
    }

    public interface GlobalHandler extends EventHandler {
        public void onGlobalEvent(GlobalEvent event);
    }
    
    private String data;

    public GlobalEvent(String tabNo) {
        this.data = tabNo;
   	}
   
    public String getData() {
        return this.data;
    }

    private static final Type<GlobalHandler> TYPE = new Type<GlobalHandler>();

    public static Type<GlobalHandler> getType() {
        return TYPE;
    }

    @Override
    public Type<GlobalHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(GlobalHandler handler) {
        handler.onGlobalEvent(this);
    }
}
