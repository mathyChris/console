package com.hybrid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4JTest {
	
	// 출력을 위해서는 logger를 생성해야 함 
	// log 를 만들려면 logger 를 하나 생성 
	static Logger log = LogManager.getLogger(); 
	
	
	public static void main(String[] args){
		
	
		log.info("Hello, Log4J");

		for(int i = 0 ; i < 100 ; i++){
			
			log.info(" Index = " +  i); 

			
		}
		
	}

}
