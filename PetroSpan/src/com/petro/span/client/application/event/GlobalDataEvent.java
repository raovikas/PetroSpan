package com.petro.span.client.application.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;
import com.petro.span.client.application.common.filter.CommonFilter;

public class GlobalDataEvent extends GwtEvent<GlobalDataEvent.GlobalDataHandler> {
    public interface HasGlobalDataHandlers extends HasHandlers {
        HandlerRegistration addGlobalHandler(GlobalDataHandler handler);
    }

    public interface GlobalDataHandler extends EventHandler {
        public void onGlobalDataEvent(GlobalDataEvent event);
    }
    public static void fire(HasHandlers source, CommonFilter data) {
        source.fireEvent(new GlobalDataEvent(data));
    }

    public static void fire(HasHandlers source, GlobalDataEvent eventInstance) {
        source.fireEvent(eventInstance);
    }

    private static final Type<GlobalDataHandler> TYPE = new Type<GlobalDataHandler>();

    private CommonFilter data;

    public GlobalDataEvent(CommonFilter data) {
        this.data = data;
    }

    public static Type<GlobalDataHandler> getType() {
        return TYPE;
    }

    @Override
    public Type<GlobalDataHandler> getAssociatedType() {
        return TYPE;
    }

    public CommonFilter getData() {
        return this.data;
    }

    @Override
    protected void dispatch(GlobalDataHandler handler) {
        handler.onGlobalDataEvent(this);
    }
}

