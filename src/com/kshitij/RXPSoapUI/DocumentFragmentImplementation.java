package com.gemalto.tsmRnD_Validation;



import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DocumentFragmentImplementation {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws ParserConfigurationException 
	 * @throws SAXException 
	 * @throws TransformerException 
	 */
	public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException, TransformerException {

		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder db = dbf.newDocumentBuilder();
		Document document = db.parse("D:/Source.xml");
		Document doc1 = db.parse("D:/yo.xml");
		NodeList nodeList = document.getElementsByTagName("con:testSuite");


		for(int i=0;i<nodeList.getLength();i++)
		{

			//insert an extra node for the second person

			Node node = nodeList.item(i);
			NodeList childNode = node.getChildNodes();
			Node targetNode = null;

			for(int j=0;j<childNode.getLength();j++){
				if( childNode.item(j).toString().contains("con:testCase")){
					targetNode = childNode.item(j);
					break;
				}

			}

			//Node targetNode = childNode.item(1);
			//System.out.println(childNode.getLength());


			Node n = document.importNode(doc1.getDocumentElement(), true);

			node.insertBefore(n, targetNode);
			//node.appendChild(teststepElm);



		}
		TransformerFactory tff  = TransformerFactory.newInstance();
		Transformer transformer = tff.newTransformer();

		DOMSource xmlSource = new DOMSource(document);
		StreamResult outputTarget = new StreamResult("D:/Source.xml");

		transformer.transform(xmlSource, outputTarget);
		System.out.println("End");

	}

}
