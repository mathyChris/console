package com.hybrid.xml;

import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.Node;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.hybrid.model.BusRouteInfoItem;

public class BusRouteInfoParser {
	
	static Log log = LogFactory.getLog(BusRouteInfoParser.class) ;
	
	DocumentBuilderFactory dFactory  ;
	DocumentBuilder builder  ;
	
	
	TransformerFactory tFactory ; // 객체를 다시 string 형태로 만드는 것을 transform이라 한다.
	
	//
	XPathFactory xFactory ; //XPath 객체 정의 , XPath를 이용하여 xml 처리하는 3번째 방법 
	
	
	List<BusRouteInfoItem> model = new ArrayList<>(); 
	
	// constructor
	public BusRouteInfoParser() throws ParserConfigurationException {
		
		dFactory = DocumentBuilderFactory.newInstance(); 
		builder = dFactory.newDocumentBuilder(); 
		
		tFactory = TransformerFactory.newInstance(); 
		
		//
		xFactory = XPathFactory.newInstance();
		
	}
	
	
	// main 
	public static void main(String[] args){
		
		try {
			
			BusRouteInfoParser parser = new BusRouteInfoParser();	
			
			List<BusRouteInfoItem> list  = parser.getBusRouteList("6628"); 
			
//			log.info(xml); 
			
//			System.out.println(list);
			
			
			for(BusRouteInfoItem item : list){
				
				log.info("man start <" + item.getBusRouteId());
				log.info(item.getBusRouteNm());
				log.info(item.getEdStationNm() + " > main end");
				
			}
	
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} 
		
	
		log.info("Program End..."); 
		
	}

	public List<BusRouteInfoItem> getBusRouteList(String strSrch){
		
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
			
			
			//////////////////////////////////////////////////////////////////////
			// XPath 사용법 
			XPath xpath = xFactory.newXPath(); 
			XPathExpression expr = xpath.compile("//ServiceResult/msgBody/itemList"); // 맨앞의 //는 root를 의미 
//			XPathExpression expr = xpath.compile("//msgBody/itemList");	
//			XPathExpression expr = xpath.compile("//itemList"); // (//는 위치에 상관없이 itemlist의 이름을 가진 것을  모두 찾는다.) 
			
			NodeList list = (NodeList) expr.evaluate(document, XPathConstants.NODESET); // expression 을 평가(검증)하는 부분 
			
			for(int i = 0 ; i < list.getLength(); i++){
				
				Element el = (Element) list.item(i) ;
				
				
				//itemlist 아래에 child 를 찾는다. 
				NodeList childs = el.getChildNodes(); 
				
				//
//				List<BusRouteInfoItem> model1 = new ArrayList<>(); 
				
				
				//
				BusRouteInfoItem item = new BusRouteInfoItem();
				
				
				for(int j =0 ; j < childs.getLength() ; j++){
					
					
					if( childs.item(j).getNodeType() == Node.ELEMENT_NODE ){
						
						
						if(childs.item(j).getNodeName().equals("busRouteId"))
							item.setBusRouteId(childs.item(j).getTextContent());
						if(childs.item(j).getNodeName().equals("busRouteNm"))
							item.setBusRouteNm(childs.item(j).getTextContent());
						if(childs.item(j).getNodeName().equals("edStationNm"))
							item.setEdStationNm(childs.item(j).getTextContent());
							
						
						log.info("child's name = " + childs.item(j).getNodeName() );
//						log.info("child's value = "+ childs.item(j).getNodeValue());
						log.info("child's context = "+ childs.item(j).getTextContent());
					}

					
				}
				model.add(item); // List 객체에 첨가
				
				log.info("Element Name = " + el.getNodeName() ); 
				
				
			}
			
			//////////////////////////////////////////////////////////////////////
			
			//Marshall(serialization)이라 한다.  
			tFactory.setAttribute("indent-number", 4); 
			Transformer transformer =  tFactory.newTransformer() ; // dom 을 다시 출력할 때 사용 
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			
			
			///////////////////////////////////////////////////////////
			DOMSource xmlSource = new DOMSource(document) ;
			
			StringWriter writer = new StringWriter();
			StreamResult outputTarget = new StreamResult(writer) ; 
			
			transformer.transform(xmlSource, outputTarget);
			
			
			result = outputTarget.getWriter().toString() ; 
			System.out.println("<Transformer : result > " + result);
			
			
		} catch (SAXException | IOException e) {
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return  model;
	}

}








