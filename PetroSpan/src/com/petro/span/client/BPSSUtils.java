package com.petro.span.client;

import java.util.List;

import com.google.gwt.i18n.client.DateTimeFormat;

public class BPSSUtils {

	 public static DateTimeFormat mm_dd_yyyy =  DateTimeFormat.getFormat("MM/dd/yyyy");
	 
	 
	 @SuppressWarnings("unchecked")
	public <T> void checklistUpdate(Boolean value, Object object, List<T> checkList) {
		 System.out.println("value "+value +"checkList size"+checkList.size() +"object "+object.toString());
			if(value && !checkList.contains(object)){
				checkList.add((T) object);
			}else if(!value && checkList.contains(object)){
				checkList.remove(object);
			}

			System.out.println("checklist size "+checkList.size());
		}
}
