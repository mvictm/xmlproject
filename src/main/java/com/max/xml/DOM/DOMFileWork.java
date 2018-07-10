package com.max.xml.DOM;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import com.max.xml.Util.PathToFolder;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 1 on 09.07.2018.
 */
public class DOMFileWork {

    private static List<Person> personList = new ArrayList();

    private static void viewPersonList(){
        for (Person aList : personList) {
            System.out.println(aList.toString());
        }
    }

    private static void createDocument(){
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = null;
        Document document = null;
        try {
            documentBuilder = documentBuilderFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        if(documentBuilder != null) {
            document = documentBuilder.newDocument();
        } else {
            System.err.println("DocumentBuilder is null! " + DOMFileWork.class.getName());
            return;
        }

        if(document != null) {
            /*Write root element*/
            Element rootElement = document.createElement("Class");
            document.appendChild(rootElement);

            Element typeClass = document.createElement("Star");
            rootElement.appendChild(typeClass);

            Element student = document.createElement("Student");
            typeClass.appendChild(student);

            /*Write attribute and link with element*/
            Attr attribut = document.createAttribute("number");
            attribut.setValue("101");
            student.setAttributeNode(attribut);

            Element element = document.createElement("name");
            element.appendChild(document.createTextNode("Ivanov"));
            student.appendChild(element);

            Element element1 = document.createElement("telephone");
            element1.appendChild(document.createTextNode("892777772727"));
            student.appendChild(element1);

            Element student1 = document.createElement("Student");
            typeClass.appendChild(student1);

            /*Write attribute and link with element*/
            Attr attribut1 = document.createAttribute("number");
            attribut1.setValue("102");
            student1.setAttributeNode(attribut1);

            Element element2 = document.createElement("name");
            element2.appendChild(document.createTextNode("Chernov"));
            student1.appendChild(element2);

            Element element3 = document.createElement("telephone");
            element3.appendChild(document.createTextNode("89288882828"));
            student1.appendChild(element3);

            /*Write to file*/
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = null;
            try {
                transformer = transformerFactory.newTransformer();
            } catch (TransformerConfigurationException e) {
                System.err.println("Problem with transformer! " + DOMFileWork.class.getName());
            }
            if (transformer != null) {
                DOMSource source = new DOMSource(document);
                StreamResult stream = new StreamResult(new File(PathToFolder.getRootPath() + PathToFolder.getPathDOMFile()));
                try {
                    transformer.transform(source, stream);
                } catch (TransformerException e) {
                    System.err.println("Problem with transform to file");
                }
            } else {
                System.err.println("Transformer is null! " + DOMFileWork.class.getName());
            }
        } else {
            System.err.println("Document is null! " + DOMFileWork.class.getName());
        }
    }

    private static void parseDOMFile(){
        File fis = new File(PathToFolder.getRootPath() + PathToFolder.getPathDOMFile());
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        try {
            Document document = builder.parse(fis);
            document.getDocumentElement().normalize();
            System.out.println("------------------------");
            System.out.println("Root " + document.getDocumentElement().getNodeName());
            NodeList list = document.getElementsByTagName("Student");
            System.out.println("------------------------");

            for (int i = 0; i <list.getLength(); i++) {
                Node node = list.item(i);
                System.out.println("\nCurrent Element :" + node.getNodeName());

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) node;
                    System.out.println("Number: " + eElement.getAttribute("number"));
                    System.out.println("Name: "+ eElement
                            .getElementsByTagName("name")
                            .item(0)
                            .getTextContent());
                    System.out.println("Telephone : "
                            + eElement
                            .getElementsByTagName("telephone")
                            .item(0)
                            .getTextContent());

                    Long l = new Long(eElement.getElementsByTagName("telephone").item(0).getTextContent());
                    Person person = new Person(eElement.getElementsByTagName("name").item(0).getTextContent(), l);
                    personList.add(person);
                }
            }

        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        createDocument();
        parseDOMFile();
        viewPersonList();
    }
}
