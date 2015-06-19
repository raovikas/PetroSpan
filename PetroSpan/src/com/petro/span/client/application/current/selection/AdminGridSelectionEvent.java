package com.petro.span.client.application.current.selection;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.event.shared.HasHandlers;
import com.google.web.bindery.event.shared.HandlerRegistration;

public class AdminGridSelectionEvent extends GwtEvent<AdminGridSelectionEvent.AdminGridSelectionHandler> {
    private static Type<AdminGridSelectionHandler> TYPE = new Type<AdminGridSelectionHandler>();
    
    public interface AdminGridSelectionHandler extends EventHandler {
        void onAdminGridSelection(AdminGridSelectionEvent event);
    }
    
    public interface HasAdminGridSelectionHandlers extends HasHandlers {
        HandlerRegistration addAdminGridSelectionHandler(AdminGridSelectionHandler handler);
    }
    
    public static void fire(HasHandlers source, Object data) {
        source.fireEvent(new AdminGridSelectionEvent(data));
    }

    public static void fire(HasHandlers source, AdminGridSelectionEvent eventInstance) {
        source.fireEvent(eventInstance);
    }

    
    private final Object data;
   
    public AdminGridSelectionEvent(final Object data) {
        this.data = data;
    }

    public static Type<AdminGridSelectionHandler> getType() {
        return TYPE;
    }

    @Override
    protected void dispatch(final AdminGridSelectionHandler handler) {
        handler.onAdminGridSelection(this);
    }

    @Override
    public Type<AdminGridSelectionHandler> getAssociatedType() {
        return TYPE;
    }

	public Object getData() {
		return this.data;
	}
    
   
}