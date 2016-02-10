
package com.gemalto.tsmRnD_Validation;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class addNode 
{
   public static void main(String[] args)
   {
	   try
	   {
		   //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		   
		   DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	       DocumentBuilder db = dbf.newDocumentBuilder();
	       Document document = db.parse("D:/Source.xml");
	       
	       //root 
	       System.out.println(document.getDocumentElement().getNodeName());
	       
	       //no of tests
	       NodeList nodeList = document.getElementsByTagName("con:testCase");
	       System.out.println(nodeList.getLength());
	       
	       
	       InputStream in = new FileInputStream(new File("D:/Add.xml"));
	       BufferedReader reader = new BufferedReader(new InputStreamReader(in,"UTF-8"));
	       StringBuilder out = new StringBuilder();
	       String line;
	       while ((line = reader.readLine()) != null) {
	           out.append(line);
	       }
		   
		   
	       for(int i=0;i<nodeList.getLength();i++)
	       {
	    	   
	    	   //insert an extra node for the second person
	    	   
	    		   Node node = nodeList.item(i);
	    		   NodeList childNode = node.getChildNodes();
	    		   Node targetNode = null;
	    		   
	    		   for(int j=0;j<childNode.getLength();j++){
	    			   if( childNode.item(j).toString().contains("con:testStep")){
	    				   targetNode = childNode.item(j);
	    				   break;
	    			   }
	    				   
	    		   }

	    		   //Node targetNode = childNode.item(1);
	    		   //System.out.println(childNode.getLength());
	    		   Element teststepElm = document.createElement("con:testStep");
				   teststepElm.setAttribute("name", "addRRS");
				   teststepElm.setAttribute("type", "request");
	    		  
	    		  
	    		   //String teststep = br.readLine();
	    		   String teststep=out.toString();
	    		   System.out.println(teststep);
	    		   
	    		   teststepElm.appendChild(document.createTextNode(teststep));
	    		   
	    		   node.insertBefore(teststepElm, targetNode);
	    		   //node.appendChild(teststepElm);
	    		   
	    		   
	    	   
	       }
	       
	       TransformerFactory tff  = TransformerFactory.newInstance();
	       Transformer transformer = tff.newTransformer();
	      
	       DOMSource xmlSource = new DOMSource(document);
	       StreamResult outputTarget = new StreamResult("D:/Source.xml");
	       
	       transformer.transform(xmlSource, outputTarget);
	       System.out.println("End");
	       reader.close();
	       
	   }
	   catch(Exception e)
	   {
		   e.printStackTrace();
	   }
   }
}