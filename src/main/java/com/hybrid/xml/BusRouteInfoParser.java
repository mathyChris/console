package com.hybrid.xml;

import java.io.IOException;
import java.io.StringWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class BusRouteInfoParser {
	
	static Log log = LogFactory.getLog(BusRouteInfoParser.class) ;
	
	DocumentBuilderFactory dFactory  ;
	DocumentBuilder builder  ;
	
	
	TransformerFactory tFactory ; // 객체를 다시 string 형태로 만드는 것을 transform이라 한다. 
	
	
	public BusRouteInfoParser() throws ParserConfigurationException {
		
		dFactory = DocumentBuilderFactory.newInstance(); 
		builder = dFactory.newDocumentBuilder(); 
		
		tFactory = TransformerFactory.newInstance(); 
		
	}
	
	public static void main(String[] args){
		
		try {
			
			BusRouteInfoParser parser = new BusRouteInfoParser();	
			
			String xml  = parser.getBusRouteList("4412"); 
			
//			log.info(xml); 
			
			System.out.println(xml);
	
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} 
		
	
		log.info("Program End..."); 
		
	}

	public String getBusRouteList(String strSrch){
		
		log.info("Method : getBusRouteList = " + strSrch);
		
		String url = "http://ws.bus.go.kr/api/rest/busRouteInfo/getBusRouteList?strSrch="
				+strSrch
				+"&ServiceKey="
				+"AaxqTg02PVW%2BZhaIkh4fVAIiknK6EU6ZkfT1lQEHEo2PRlldpzfhjoBwE63YKQGpiY4JdZCjCktTW2yatRX%2FgA%3D%3D";   
		
		String result = null ; 
		
		
		try {
			
			//이 기종 간의 데이터 전송 방식에는 Json과 xml 이 있다. 
			//Unmarshall(deserialization)이라 한다. xml을 자바의 객체로 만들어 내는 것 
			Document document = builder.parse(url); // w3c로 document객체 생성 
			
			//
			
			//Marshall(serialization)이라 한다.  
			Transformer transformer =  tFactory.newTransformer() ; // dom 을 다시 출력할 때 사용 
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
//			transformer.transform(new DOMSource(document), outputTarget);
			
			
			///////////////////////////////////////////////////////////
			DOMSource xmlSource = new DOMSource(document) ;
			
			StringWriter writer = new StringWriter();
			StreamResult outputTarget = new StreamResult(writer) ; 
			
			transformer.transform(xmlSource, outputTarget);
			
			
			result = outputTarget.getWriter().toString() ; 
			
			
		} catch (SAXException | IOException e) {
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		} 
		
		return  result;
	}

}








