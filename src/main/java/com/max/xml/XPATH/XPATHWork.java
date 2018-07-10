package com.max.xml.XPATH;

import com.max.xml.Util.PathToFolder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 1 on 10.07.2018.
 * XPATH can parse file, but can't create. If you want, use DOM.
 */
public class XPATHWork {

    private static List<Student> students = new ArrayList<>();

    private static void viewInfo(){
        for (Student s : students) {
            System.out.println(s.toString());
        }
    }

    private static void parseXPATHFile() throws ParserConfigurationException, IOException, SAXException, XPathExpressionException {
        File inputFile = new File(PathToFolder.getRootPath() + PathToFolder.getPathXPATHFile());
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;

        dBuilder = dbFactory.newDocumentBuilder();

        Document document = dBuilder.parse(inputFile);
        document.getDocumentElement().normalize();

        XPath xPath =  XPathFactory.newInstance().newXPath();

        String expression = "student/person";

        NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(document, XPathConstants.NODESET);

        for (int i = 0; i < nodeList.getLength(); i++) {
            Node nNode = nodeList.item(i);
            System.out.println("\nCurrent Element :" + nNode.getNodeName());

            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) nNode;
                System.out.println("person group :" + element.getAttribute("group"));
                System.out.println("Name : "
                        + element
                        .getElementsByTagName("name")
                        .item(0)
                        .getTextContent());
                System.out.println("Telephone : "
                        + element
                        .getElementsByTagName("telephone")
                        .item(0)
                        .getTextContent());
                System.out.println("Gender : "
                        + element
                        .getElementsByTagName("gender")
                        .item(0)
                        .getTextContent());

                Student student = new Student(element.getElementsByTagName("name").item(0).getTextContent(),
                        Long.parseLong(element.getElementsByTagName("telephone").item(0).getTextContent()),
                        element.getElementsByTagName("gender").item(0).getTextContent());

                students.add(student);
            }
        }
    }

    public static void main(String[] args) throws ParserConfigurationException, SAXException, XPathExpressionException, IOException {
        parseXPATHFile();
        System.out.println("-------------");
        viewInfo();
    }
}
