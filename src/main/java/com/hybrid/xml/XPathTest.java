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

public class XPathTest {
	
	static String strSrch ="6628"; 
	
	public XPathTest() { }
		
	
	public static void main(String[] args){
		
		String url = "http://ws.bus.go.kr/api/rest/busRouteInfo/getBusRouteList?strSrch="
				+ "6628"
				+"&ServiceKey="
				+"s8HvbWYxtg7rY%2FNlvj4%2F6feKJu66XO%2BLNI3zC%2B7KEUdV0R1Iu2%2B33bKui8OAE%2BV6uJXTKtb9Hcnp94LSG4fajA%3D%3D"; 
		
		DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance(); 
		
		XPathFactory xFactory = XPathFactory.newInstance() ;
		
		try {
			
			DocumentBuilder dBuilder = dFactory.newDocumentBuilder();
			
			Document document = dBuilder.parse(url); 
			
			
			XPath xpath1 = xFactory.newXPath(); 
			XPathExpression expr = xpath1.compile("//itemList"); 
			
			NodeList list = (NodeList)expr.evaluate(document, XPathConstants.NODESET) ; 
			
			for(int i = 0 ; i < list.getLength() ; i++){
				
				Element el = (Element) list.item(i); 
				NodeList childs = el.getChildNodes(); 
				
				
				for(int j = 0 ; j < childs.getLength() ; j++){
				
				System.out.println("Child Name = " + childs.item(j).getNodeName());
				System.out.println("Child Content = " + childs.item(j).getTextContent());
				
				}
				
				
			}
			
			
		} catch (ParserConfigurationException e) {
						e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
	}
	

}
