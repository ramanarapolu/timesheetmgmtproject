package com.herguan.cs562.model;

import java.io.Serializable;
import java.util.Calendar;

public enum WeekEnum implements Serializable{
	CURRENT {
	    public String toString() {
	    	Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(System.currentTimeMillis());
			cal.get(Calendar.WEEK_OF_YEAR);
			cal.get(Calendar.YEAR);
			return cal.get(Calendar.YEAR)+"-"+cal.get(Calendar.WEEK_OF_YEAR);
	    }
	},

	CURRENT_MINUS1 {
	    public String toString() {
	    	Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(System.currentTimeMillis());
			cal.get(Calendar.WEEK_OF_YEAR);
			cal.get(Calendar.YEAR);
			return cal.get(Calendar.YEAR)+"-"+(cal.get(Calendar.WEEK_OF_YEAR)-1); 
	    }
	},
	CURRENT_MINUS2 {
	    public String toString() {
	    	Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(System.currentTimeMillis());
			cal.get(Calendar.WEEK_OF_YEAR);
			cal.get(Calendar.YEAR);
			return cal.get(Calendar.YEAR)+"-"+(cal.get(Calendar.WEEK_OF_YEAR)-2); 
	    }
	},
	
	CURRENT_PLUS1 {
	    public String toString() {
	    	Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(System.currentTimeMillis());
			cal.get(Calendar.WEEK_OF_YEAR);
			cal.get(Calendar.YEAR);
			return cal.get(Calendar.YEAR)+"-"+(cal.get(Calendar.WEEK_OF_YEAR)+1); 
	    }
	},
	
	CURRENT_PLUS2 {
	    public String toString() {
	    	Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(System.currentTimeMillis());
			cal.get(Calendar.WEEK_OF_YEAR);
			cal.get(Calendar.YEAR);
			return cal.get(Calendar.YEAR)+"-"+(cal.get(Calendar.WEEK_OF_YEAR)+2); 
	    }
	}
	
}
