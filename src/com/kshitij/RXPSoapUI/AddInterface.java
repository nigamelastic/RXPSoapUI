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

public class AddInterface {
	public static Element getNodeWithAttribute(Node root, String attrName,
			String attrValue) {
		NodeList nl = root.getChildNodes();
		for (int i = 0; i < nl.getLength(); i++) {
			Node n = nl.item(i);
			if (n instanceof Element) {
				Element el = (Element) n;
				if (el.getAttribute(attrName).equals(attrValue)) {
					return el;
				} else {
					el = getNodeWithAttribute(n, attrName, attrValue); // search
					// recursively
					if (el != null) {
						return el;
					}
				}
			}
		}
		return null;
	}

	public static void main(String[] args) {
		try {
			// BufferedReader br = new BufferedReader(new
			// InputStreamReader(System.in));

			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document document = db.parse("D:/Source.xml");

			// root

			System.out.println(document.getDocumentElement().getNodeName());
			NodeList nodelist=document.getElementsByTagName("con:interface");
			Node node = nodelist.item(0).getParentNode();
			
			
			Element interfaceElm = document.createElement("con:interface");
			interfaceElm.setAttribute("anonymous", "optional");
			interfaceElm.setAttribute("bindingName", "{http://admin.connector.core.tsm.gemalto.com/}AdminWsConnectorPortBinding");
			interfaceElm.setAttribute("definition", "file:/D:/Current%20Dev/4.0/Test%20Construstion/wsdl/AdminWsConnectorService.wsdl");
			interfaceElm.setAttribute("name", "AdminWsConnectorPortBinding");
			interfaceElm.setAttribute("soapVersion", "1_1");
			interfaceElm.setAttribute("type", "wsdl");
			interfaceElm.setAttribute("wsaVersion", "NONE");
			interfaceElm.setAttribute("xsi:type", "con:WsdlInterface");
			interfaceElm.setAttribute("xmlns:xsi", "ttp://www.w3.org/2001/XMLSchema-instance");
			
			
			
			
			
			//InputStream in = new FileInputStream(new File("D:/Adding-interfaces_foraddRRS7.xml"));
			//BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));
		//	StringBuilder out = new StringBuilder();
			//String line;
			//while ((line = reader.readLine()) != null) {
			//	out.append(line);
			//}
			
			
			
			Element adminInterface = getNodeWithAttribute(document.getDocumentElement(), "name", "spInterfaceSOAP-2.0Binding");
			//Element KmsWsConnectorPortBinding = getNodeWithAttribute(document.getDocumentElement(), "name", "KmsWsConnectorPortBinding");
			//Element ValidConnector_RTDP2PortBinding = getNodeWithAttribute(document.getDocumentElement(), "name", "ValidConnector_RTDP2PortBinding");
			
			if(adminInterface != null){
				System.out.println(adminInterface);
				adminInterface.getParentNode().removeChild(adminInterface);
			}
			
			
		//	String interfaceData=out.toString();
			//interfaceElm.appendChild(document.createTextNode(interfaceData));
			//node.appendChild(interfaceElm);

			TransformerFactory tff = TransformerFactory.newInstance();
			Transformer transformer = tff.newTransformer();

			DOMSource xmlSource = new DOMSource(document);
			StreamResult outputTarget = new StreamResult("D:/Source.xml");

			transformer.transform(xmlSource, outputTarget);
			System.out.println("End");
		//	reader.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}