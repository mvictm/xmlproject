package com.max.xml.StAX;

import com.max.xml.Util.PathToFolder;

import javax.xml.stream.*;
import javax.xml.stream.events.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

/**
 * Created by 1 on 10.07.2018.
 */
public class StAXFileWork {
    private static void createStAXFile() throws XMLStreamException, IOException {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(PathToFolder.getRootPath() + PathToFolder.getPathStAXFile());
        } catch (IOException e) {
            e.printStackTrace();
        }

        XMLOutputFactory xMLOutputFactory = XMLOutputFactory.newInstance();
        XMLStreamWriter xMLStreamWriter =
                null;
        try {
            xMLStreamWriter = xMLOutputFactory.createXMLStreamWriter(fileWriter);
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }

        xMLStreamWriter.writeStartDocument();
        xMLStreamWriter.writeStartElement("cars");

        xMLStreamWriter.writeStartElement("supercars");
        xMLStreamWriter.writeAttribute("company", "Ferrari");

        xMLStreamWriter.writeStartElement("carname");
        xMLStreamWriter.writeAttribute("type", "formula one");
        xMLStreamWriter.writeCharacters("Ferrari 101");
        xMLStreamWriter.writeEndElement();

        xMLStreamWriter.writeStartElement("carname");
        xMLStreamWriter.writeAttribute("type", "sports");
        xMLStreamWriter.writeCharacters("Ferrari 202");
        xMLStreamWriter.writeEndElement();

        xMLStreamWriter.writeEndElement();
        xMLStreamWriter.writeEndDocument();

        xMLStreamWriter.flush();
        xMLStreamWriter.close();

        //String xmlString = stringWriter.getBuffer().toString();

        fileWriter.close();
    }

    private static void parseStAXFile() throws FileNotFoundException, XMLStreamException {
        /*Usage flags*/
        boolean supecars = false;
        boolean carname = false;

        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLEventReader eventReader =
                factory.createXMLEventReader(new FileReader(PathToFolder.getRootPath() + PathToFolder.getPathStAXFile()));

        System.out.println(" ");
        System.out.println("-----------------------");
        System.out.println(" ");

        while (eventReader.hasNext()) {
            XMLEvent event = eventReader.nextEvent();

            switch (event.getEventType()) {

                case XMLStreamConstants.START_ELEMENT:
                    StartElement startElement = event.asStartElement();
                    String qName = startElement.getName().getLocalPart();

                    if (qName.equalsIgnoreCase("cars")) {
                        System.out.println("Start Element : cars");
                    } else if (qName.equalsIgnoreCase("supercars")) {
                        Iterator<Attribute> attributes = startElement.getAttributes();
                        String company = attributes.next().getValue();
                        System.out.println("Company : " + company);
                        supecars = true;
                    } else if (qName.equalsIgnoreCase("carname")) {
                        carname = true;
                    }
                    break;

                case XMLStreamConstants.CHARACTERS:
                    Characters characters = event.asCharacters();
                    if (supecars) {
                        System.out.println("supercar " + characters.getData());
                        supecars = false;
                    }
                    if (carname) {
                        System.out.println("carname: " + characters.getData());
                        carname = false;
                    }
                    break;

                case XMLStreamConstants.END_ELEMENT:
                    EndElement endElement = event.asEndElement();

                    if(endElement.getName().getLocalPart().equalsIgnoreCase("cars")) {
                        System.out.println("End Element : cars");
                        System.out.println();
                    }
                    break;
            }
        }
    }

    public static void main(String[] args) throws IOException, XMLStreamException {
        createStAXFile();
        parseStAXFile();
    }
}
