package com.hybrid;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ParsingTest {
	
	 private final String XML_FILE_PATH = "nyfeed.xml";  
	  
	
	 public static void main(String[] args) throws Exception {  
		 ParsingTest xpt = new ParsingTest();  
	  xpt.domParseTest();  
//	  xpt.saxParseTest();  
	 }
	 
	 public void domParseTest() throws ParserConfigurationException, SAXException, IOException{
		 
		 
		  System.out.println("==============================");  
		  System.out.println("domParseTest()");  
		  System.out.println("==============================");  
		  
		  File xmlFile = new File(XML_FILE_PATH); 
		  DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		  
		  DocumentBuilder db = dbf.newDocumentBuilder() ;
		  Document doc = db.parse(xmlFile) ;
		  
		  doc.getDocumentElement().normalize();  
		  
		  
		  //////////////////////////////////////////////////////////////////////////
		    
		  System.out.printf("Root element:%s\n", doc.getDocumentElement().getNodeName());
		  
		  
		  NodeList itemNodeList = doc.getElementsByTagName("itemList");  
		    
		  for (int s = 0; s < itemNodeList.getLength(); s++) {  
		  
		   Node itemNode = itemNodeList.item(s);  
		  
		   if (itemNode.getNodeType() == Node.ELEMENT_NODE) {  
		  
		    Element itemElement = (Element)itemNode;  
		      
		   NodeList edStationNm = itemElement.getElementsByTagName("edStationNm"); 
		   
		    Element titleElement = (Element)edStationNm.item(0);  
		    
		   NodeList childTitleNodeList = titleElement.getChildNodes();  
		   
		    System.out.printf("[title : %s]\n", ((Node)childTitleNodeList.item(0)).getNodeValue());  
//		      
//		   NodeList linkNodeList = itemElement.getElementsByTagName("link");  
//		   
//		    Element linkElement = (Element) linkNodeList.item(0);  
//		    
//		   NodeList childLinkNodeList = linkElement.getChildNodes();
//		   
//		    System.out.printf("[link : %s]\n", ((Node)childLinkNodeList.item(0)).getNodeValue());  
		   
		   
		   } //if  
		  
		  }//for   
		
	 }
		 

	 
	 
	 public void saxParseTest() {}
	 
	 
	 
	

}
