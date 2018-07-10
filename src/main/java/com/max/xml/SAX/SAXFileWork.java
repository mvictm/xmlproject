package com.max.xml.SAX;

import com.max.xml.Util.PathToFolder;
import lombok.Getter;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 1 on 10.07.2018.
 * Sax parser use to read information to XML file. You can't create XML file. If you want, you can use SAX framework.
 */
public class SAXFileWork extends DefaultHandler{

    @Getter
    private List<Employee> employeeList = null;
    private Employee emp;

    /*Flags, which are help*/
    private boolean bAge = false;
    private boolean bName = false;
    private boolean bGender = false;
    private boolean bRole = false;

    private void employeeInfo(){
        for (Employee emp: employeeList) {
            System.out.println(emp.toString());
        }
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("Employee")) {
            //create a new Employee and put it in Map
            String id = attributes.getValue("id");
            //initialize Employee object and set id attribute
            emp = new Employee();
            emp.setId(Integer.parseInt(id));
            //initialize list
            if (employeeList == null)
                employeeList = new ArrayList<>();
        }
        else if (qName.equalsIgnoreCase("name")) {
            //set boolean values for fields, will be used in setting Employee variables
            bName = true;
        } else if (qName.equalsIgnoreCase("age")) {
            bAge = true;
        } else if (qName.equalsIgnoreCase("gender")) {
            bGender = true;
        } else if (qName.equalsIgnoreCase("role")) {
            bRole = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("Employee")) {
            //add Employee object to list
            employeeList.add(emp);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (bAge) {
            //age element, set Employee age
            emp.setAge(Integer.parseInt(new String(ch, start, length)));
            bAge = false;
        } else if (bName) {
            emp.setName(new String(ch, start, length));
            bName = false;
        } else if (bRole) {
            emp.setRole(new String(ch, start, length));
            bRole = false;
        } else if (bGender) {
            emp.setGender(new String(ch, start, length));
            bGender = false;
        }
    }

    public static void main(String[] args) {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = saxParserFactory.newSAXParser();
            SAXFileWork handler = new SAXFileWork();
            parser.parse(new File(PathToFolder.getRootPath() + PathToFolder.getPathSAXFile()), handler);
            handler.employeeInfo();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
