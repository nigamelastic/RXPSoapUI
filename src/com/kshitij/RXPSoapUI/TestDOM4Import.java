package com.gemalto.tsmRnD_Validation;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import org.w3c.dom.*;
//import com.sun.org.apache.xerces.internal.parsers.DOMParser;

public class TestDOM4Import {

	static Document m_doc = null;
	
	public static void main(String[] args){
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		
		if(factory != null)
			System.out.println(factory.toString());
		else
			System.out.println("Problem with Parser");
		
		//factory.setValidating(true);
		try{
			DocumentBuilder parser = factory.newDocumentBuilder();
			m_doc = parser.parse(new java.io.File("D:\\Workspaces\\Core Java\\XML\\xml\\employee.xml"));
			printNode(m_doc);
			
			
			//Open details xml
			Document detailsDoc = parser.parse(new java.io.File("D:\\Workspaces\\Core Java\\XML\\xml\\empDetails4Rob.xml"));
			Node detailsNode = m_doc.importNode(detailsDoc.getDocumentElement(), true);
			
			//Find emp node for 'Rob'
			Element e = (Element)m_doc.getElementById("e22");
			e.appendChild(detailsNode);
			printNode(m_doc);
			
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	/**Print data as it is
	 * */
	static void printSingleNode(Node node){
		
		System.out.print(node.getNodeType() + " ");
		System.out.print(node.getNodeName() + " ");
		System.out.println(node.getNodeValue());

		if(node.getNodeType() == Node.DOCUMENT_TYPE_NODE)
			printDTD(node);
		
		NamedNodeMap attrs = node.getAttributes();
		if(attrs != null && attrs.getLength() > 0){
			//System.out.println("Now printing attrs of " + node.getNodeName() + " " + node.getNodeValue());
			for(int i=0; i<attrs.getLength(); i++){
				Node attr = attrs.item(i);
				printSingleNode(attr);
				System.out.print(",");
			}
			//System.out.println("Done printing attrs of " + node.getNodeName() + " " + node.getNodeValue());
		}
		
		NodeList list = node.getChildNodes();
		if(list != null && list.getLength() > 0){
			System.out.println("Now printing child nodes of " + node.getNodeName() + " " + node.getNodeValue());
			for(int i=0; i<list.getLength(); i++){
				printSingleNode( list.item(i));
			}
			System.out.println("Done printing child nodes of " + node.getNodeName() + " " + node.getNodeValue());
		}
	}
		
	static void printDTD(Node node){
		
		System.out.println("Printing the DTD");
		
		DocumentType dtd = (DocumentType)node;
		System.out.println(dtd.getName());
		System.out.println(dtd.getPublicId());
		System.out.println(dtd.getSystemId());
		System.out.println(dtd.getInternalSubset());
		
		NamedNodeMap entities = dtd.getEntities();
		System.out.println("Entities: " + entities.getLength());
		if(entities.getLength() > 0){
			for(int i=0; i<entities.getLength(); i++){
				Node entity = entities.item(i);
				printSingleNode(entity);
			}
		}
	}
	
	static void printNode(Node node){
		
		short type = node.getNodeType();
		
		switch(type){
			case Node.DOCUMENT_NODE:
				System.out.println("<xml version=\"1.0\" >");
				
				NodeList list = node.getChildNodes();
				if(list != null){
					for(int i=0; i<list.getLength(); i++){
						printNode( list.item(i));
					}
				}
				
				break;
			
			case Node.ELEMENT_NODE:
				System.out.print("<" + node.getNodeName());
				
				NamedNodeMap attrs = node.getAttributes();
				
				if(attrs != null){
					for(int i=0; i<attrs.getLength(); i++){
						Node attr = attrs.item(i);
						System.out.print(" " +attr.getNodeName() + "=" + 
								"\"" + attr.getNodeValue() + "\"" + " ");
					}
				}
				
				System.out.print(">");
				
				NodeList c_nodes = node.getChildNodes();
				if(c_nodes != null){
					for(int i=0; i<c_nodes.getLength(); i++){
						printNode(c_nodes.item(i));
					}
				}
				
				System.out.println("</" + node.getNodeName() + ">");
				
				break;
				
			case Node.TEXT_NODE:
				System.out.print(node.getNodeValue());
				break;
				
			default: 
				System.out.println("Cannot recognize");
		}
	}
	
	static void printNode(String nodeName) throws java.io.IOException{
		if(nodeName.equals("department")){
			printDept();
			return;
		}
		
		Node node = m_doc.getElementsByTagName(nodeName).item(0);
		
		if(node != null){
			printNode(node);
		}
	}
	
	static void printDept() throws java.io.IOException{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String value = reader.readLine();
		
		int dept_id = Integer.parseInt(value);
		
		Node dept = m_doc.getElementsByTagName("department").item(--dept_id);
		if(dept != null)
			printNode(dept);
	}
}
