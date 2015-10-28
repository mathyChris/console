package com.hybrid;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

// log을 사용하여 Log4J없이도 사용할 수 있게 함  

public class CommonsLogTest {
	
	static Log log = LogFactory.getLog(CommonsLogTest.class) ;
	
	public static void main(String[] args){
		
		
//		String url = "http://ws.bus.go.kr/api/rest/busRouteInfo/getBusRouteList?strSrch=6628&ServiceKey=s8HvbWYxtg7rY%2FNlvj4%2F6feKJu66XO%2BLNI3zC%2B7KEUdV0R1Iu2%2B33bKui8OAE%2BV6uJXTKtb9Hcnp94LSG4fajA%3D%3D";     
// 		
		
		String url = "http://ws.bus.go.kr/api/rest/buspos/getBusPosByRtid?busRouteId=4662800&ServiceKey=AaxqTg02PVW%2BZhaIkh4fVAIiknK6EU6ZkfT1lQEHEo2PRlldpzfhjoBwE63YKQGpiY4JdZCjCktTW2yatRX%2FgA%3D%3D";
		
		try {
			
			URL u = new URL(url); // io 는 항상 exception check
	
			InputStream in = u.openStream(); 
			//Scanner 사용 가능 , 여기서는 BufferedReader 를 사용함		
			Reader r  = new InputStreamReader(in); 
			BufferedReader reader = new BufferedReader(r); 
			
			String line = null ; 
			
			while( (line = reader.readLine()) != null){
				
				// 연습을 위해 Log4J사용해본다. 
				log.info(line);
				
				// 일반적인 출력 
				System.out.println(line);
				
			
			}
			
			reader.close();
			
			
			
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		
	}
		
	
	
	public static void test1(){
	
	log.info("Hello, Commons Logging");
	
		
		for(int i = 0 ; i < 10 ; i++){

			log.info(" i = " + i);
			
		}
		
	}

}
