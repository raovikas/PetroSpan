package com.petro.span.client;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.Response;

public class ParallelRequestCallback implements RequestCallback {


	/** A reference to the parent callback, which runs when all are complete. */
	private ParentRequestCallback parentRequestCallback;


    /** The data that is returned from the service call. */
    private Response data;
    
	@Override
	public void onResponseReceived(Request request, Response response) {
		if(200 == response.getStatusCode()){
			 this.data = response;
			parentRequestCallback.done();
			System.out.println("onResponseReceived 200 ");

		}else

			System.out.println("error responseText  "+ response.getText());

	}

	@Override
	public void onError(Request request, Throwable exception) {
		System.out.println(exception.getLocalizedMessage());
	}


	/**
	 * Called by the parent callback, to inject a reference to itself into the child.
	 */
	public void setParent(ParentRequestCallback parentRequestCallback) {
           this.parentRequestCallback = parentRequestCallback;
	}
	
	
	 /**
     * Method that can be used by the parent callback to get the data from this service call and
     * process it.
     */
    public Response getData() {
        return data;
    }


}
