package com.petro.span.client;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.SuggestBox;
import com.petro.span.shared.UserInfo;

public class APIRequests {

	private ListBox listBox;

	AuthUtil authUtil ;
	UserInfo user;
	
	
	
	
	public APIRequests(AuthUtil authUtil, UserInfo user) {
		this.authUtil = authUtil;
		this.user = user;
	}

	public void gwtPOSTHttp(String apiUrl, ListBox listBox) {

		this.listBox = listBox;
        try {
        	RequestBuilder builder = new RequestBuilder(RequestBuilder.POST, apiUrl);
			 builder.sendRequest(null, handleResponseCallback);
		} catch (RequestException e) {
			e.printStackTrace();
		}
		
	}
	
	



	private RequestCallback handleResponseCallback = new RequestCallback() {
		public void onError(Request request, Throwable e) {
			System.out.println(e.getMessage());
			
		}

		public void onResponseReceived(Request request, Response response) {
			if (200 == response.getStatusCode()) {
				String resString = response.getText();
				parseJsonData(resString);
			} else {
				
				System.out.println(response.getText());
			}
		}
	};
	
	

	private void parseJsonData(String json) {
		listBox.clear();
		listBox.addItem("Select","-1");
		JSONValue value = JSONParser.parseLenient(json);
		JSONObject productsObj = value.isObject();
		JSONArray rowsArray = productsObj.get("rows").isArray();

		if (rowsArray != null) {
			for (int i = 0; i <= rowsArray.size() - 1; i++) {
				JSONArray colArray = rowsArray.get(i).isArray();
				
				listBox.addItem(colArray.get(0).isString().stringValue());
			}


		}

	}
	
	
	

	String responseText = null ;
	public String gwtTableResourceHttp(String getUrl, SuggestBox playSuggestBox) {
        try {
				RequestBuilder builder = new RequestBuilder(RequestBuilder.POST, getUrl);
			 builder.sendRequest(null, new RequestCallback() {
				
				@Override
				public void onResponseReceived(Request request, Response response) {
				 if(200 == response.getStatusCode()){
					 responseText =  response.getText();
					System.out.println("responseText  "+responseText);
					
				 }else
					
					System.out.println("responseText  "+ response.getText());
				    
				}
				
				@Override
				public void onError(Request request, Throwable exception) {
					System.out.println(exception.getLocalizedMessage());
				}
			});
		} catch (RequestException e) {
			e.printStackTrace();
		}
        return responseText;
	}

	
	
	
}
