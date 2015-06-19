package com.petro.span.client.application.bydefault;

import com.gwtplatform.mvp.client.UiHandlers;
import com.petro.span.client.application.common.filter.DefaultCommonFilter;

interface DefaultTabUiHandlers extends UiHandlers {
//	void gwtPostHttp(String postUrl, String requestData, ListBox countyListBox);

	void LoadMap(String buildWhereClause, String currentTableID);

	void LoadChart(String searchCreteria, String currentTableID);


	void fireSelectionEvent(DefaultCommonFilter byDefaultFilter);

//	JSONArray gwtGetTableResourceHttp(String getUrl, SuggestBox playSuggestBox);

//	void httpRequestForState(String currentPlayCode);
}