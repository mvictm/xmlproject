package com.max.xml.JDOM;

import com.max.xml.Util.PathToFolder;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by 1 on 10.07.2018.
 * XMLOutputter work with FileWriter
 */
public class CreateJDOMFile {

    public static void main(String[] args) {

        /*Root element*/
        Element element = new Element("Auto");
        Document document = new Document(element);

        Element typeAuto = new Element("Super");

        Element company = new Element("Company");
        company.setAttribute("Name", "Ferrari");

        Element auto = new Element("Car");
        auto.setText("Ferrari F10");

        typeAuto.addContent(company);
        company.addContent(auto);

        document.getRootElement().addContent(typeAuto);

        XMLOutputter xmlOutput = new XMLOutputter();

        // display ml
        xmlOutput.setFormat(Format.getPrettyFormat());
        try {
            xmlOutput.output(document, new FileWriter(new File(PathToFolder.getRootPath() + PathToFolder.getPathJDOMFile())));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
