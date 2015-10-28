package com.hybrid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

// log을 사용하여 Log4J없이도 사용할 수 있게 함  

public class CommonsLogTest {
	
	static Log log = LogFactory.getLog(CommonsLogTest.class) ;
	
	public static void main(String[] args){
		
		log.info("Hello, Commons Logging");
	
		
		for(int i = 0 ; i < 10 ; i++){

			log.info(" i = " + i);
			
		}
		
	}

}
