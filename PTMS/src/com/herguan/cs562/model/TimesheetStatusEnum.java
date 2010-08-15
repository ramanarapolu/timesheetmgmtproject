package com.herguan.cs562.model;

import java.io.Serializable;

public enum TimesheetStatusEnum implements Serializable{
	
	APPROVED {
	    public String toString() {
	        return "Approved";
	    }
	},

	INCOMPLETE {
	    public String toString() {
	        return "Incomplete";
	    }
	},
	
	SUBMITTED {
	    public String toString() {
	        return "Submitted";
	    }
	}
	
	
	


}
