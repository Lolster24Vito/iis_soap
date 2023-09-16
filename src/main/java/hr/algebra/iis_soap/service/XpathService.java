package hr.algebra.iis_soap.service;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@Service
public class XpathService {
    public String UseExpression(String expression, File file) throws XPathExpressionException, IOException, ParserConfigurationException, SAXException {
        FileInputStream fileIS = new FileInputStream(file);
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = builderFactory.newDocumentBuilder();
        Document xmlDocument = builder.parse(fileIS);
        XPath xPath = XPathFactory.newInstance().newXPath();
        //String expression = "/Tutorials/Tutorial";
        String nodeList = (String) xPath.compile(expression).evaluate(xmlDocument, XPathConstants.STRING);
        return nodeList;
    }
}
