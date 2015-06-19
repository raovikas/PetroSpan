package com.petro.span.client;



import java.util.List;

import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.ListDataProvider;

/**This is the class which we use to clear and refresh data and to show in datagrid
 * 
 * @author vikas.yadav
 *
 * @param <T>  is the datagrid row type object which we want to show in datagrid  rows
 */
public class DataService<T extends Object> {
	
	DataService(){
		System.out.println("inside dataDervice");
	}
	
	/**
	 * The provider that holds the list of DataModel in the database.
	 */
	private ListDataProvider<T> dataProvider = new ListDataProvider<T>();
	
	
    public ListDataProvider<T> getDataProvider() {
        return dataProvider;
      }
    
    /**
     * Refresh all displays.
     */
    public void refreshDisplays() {
      dataProvider.refresh();
    }
    

    
    /**
     * Add a display to the database. The current range of interest of the display
     * will be populated with data.
     * 
     * @param display a {@Link HasData}.
     */
    public void addDataDisplay(HasData<T> display) {
      dataProvider.addDataDisplay(display);
    }
    
    /**
     * 
     * @param dataList is the list which we want to clear
     */
    public void clear(List<T> dataList){
    	dataList.clear();
    	refreshDataList(dataList);
      
    }

    /**
     * 
     * @param dataList is the list which we want to refresh and show in datagrid
     */
	public void refreshDataList(List<T> dataList) {
		 dataProvider.setList(dataList);
	       dataProvider.refresh();
	}
}
