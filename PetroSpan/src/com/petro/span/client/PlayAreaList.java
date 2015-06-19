package com.petro.span.client;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.inject.Inject;
import com.petro.span.client.application.common.filter.CommonFilter;


/**
 * 
 * @author vikas.yadav
 *
 */
public class PlayAreaList {

	private static final String FUSION_TABLE_ID_PLAYAREA = "1PZFSiZBUs8gMA0pNYe1OVCowc8kQixX9CsvT4FiA" ;
	public  boolean isFirstTimeObject = true;

	String  responseText;

	//playCodeMap do the mapping between 'Play Description' and 'Play Code'
	private Map<String,String> playCodeMap = new HashMap<String, String>(); 

	//tableIDMap do the mapping between 'Play Code' and 'FusionTableID'
	private Map<String,String> tableIDMap = new HashMap<String, String>(); 



	@Inject
	PlayAreaList(){
		getPlayAreaList();
	}

	String FUSION_TABLE_ACCESS_KEY =	"AIzaSyD7YQT-LFaE8YR3HFeFvzAQtFhZpwXnNvI";

	private void getPlayAreaList() {
		String getUrl = "https://www.googleapis.com/fusiontables/v2/query?sql=SELECT 'Play Description','Play Code','Fusion TableID' from  "+FUSION_TABLE_ID_PLAYAREA+"&key="+ FUSION_TABLE_ACCESS_KEY;
		System.out.println("getUrl  "+getUrl);

		httpRequestForPlayArea(getUrl);


	}



	private void httpRequestForPlayArea(String getUrl) {
		try {
			final RequestBuilder builder = new RequestBuilder(RequestBuilder.POST, getUrl);
			builder.sendRequest(null, new RequestCallback() {

				@Override
				public void onResponseReceived(Request request, Response response) {
					if(200 == response.getStatusCode()){
						String  responseText =  response.getText();
						System.out.println("responseText  "+responseText);
						parsePlayAreaData(responseText);


					}else

						System.out.println("error responseText  "+ response.getText());

				}

				@Override
				public void onError(Request request, Throwable exception) {
					System.out.println(exception.getLocalizedMessage());
				}
			});
		} catch (RequestException e) {
			e.printStackTrace();
		}
	}



	protected void parsePlayAreaData(String responseText) {
		try {
			JSONValue value = JSONParser.parseLenient(responseText);
			JSONObject productsObj = value.isObject();
			JSONArray rowsArray = productsObj.get("rows").isArray();
			commonFilter.playCombo.addItem("Select","-1");
			if (rowsArray != null) {
				for (int i = 0; i <= rowsArray.size() - 1; i++) {
					JSONArray colArray = rowsArray.get(i).isArray();
					String colArray0 = colArray.get(0).isString().stringValue();
					String colArray1 = colArray.get(1).isString().stringValue();
					String colArray2 = colArray.get(2).isString().stringValue();

					commonFilter.playCombo.addItem(colArray0);
					playCodeMap.put(colArray0,colArray1);
					tableIDMap.put(colArray1, colArray2);

					System.out.println("playMap size  "+playCodeMap.size());

				}



			}		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}




	public Map<String, String> getTableIDMap() {
		return tableIDMap;
	}



	public Map<String, String> getPlayMap() {
		return playCodeMap;
	}

	CommonFilter commonFilter;

	public void setRef(CommonFilter commonFilter) {
		this.commonFilter = commonFilter;
	}















}
