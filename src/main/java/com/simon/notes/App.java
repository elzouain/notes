package com.simon.notes;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.simon.notes.controller.Controller;

public class App {
	
	public static final Logger LOG = LogManager.getLogger(App.class); 
	
    public static void main( String[] args )
    {
    	try {
    		Controller controller = new Controller();
    		controller.init();
    	}catch(Exception e) {
    		LOG.error("Error found. App will exit.", e);    		
    	}
    }
}
