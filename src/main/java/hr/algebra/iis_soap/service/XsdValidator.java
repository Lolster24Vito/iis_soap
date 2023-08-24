package hr.algebra.iis_soap.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import com.thaiopensource.relaxng.jaxp.XMLSyntaxSchemaFactory;

@Service
public class XsdValidator {
    //private final String xsdPath ="/src/main/resources/cities.xsd";
    private  File xsd;
    private File validationRng;

    public String isXMLValidXSD( String xmlPath) {
        try {
            xsd=ResourceUtils.getFile("classpath:cities.xsd");
            SchemaFactory factory =
                    SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(xsd);
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(xmlPath));

        } catch (IOException | SAXException e) {
            System.out.println("Exception: " + e);
            return e.getMessage();
        }
        return "true";
    }

    //jing jar package downloaded from https://code.google.com/archive/p/jing-trang/downloads
    public String isXMLValidRNG(String xmlPath) {
        boolean valid=true;
        String errorMessage="";
        try {
            validationRng=ResourceUtils.getFile("classpath:citiesRng.rng");
            (new XMLSyntaxSchemaFactory().newSchema(validationRng)).newValidator()
                    .validate(new StreamSource(new File(xmlPath)));
        } catch (SAXException | IOException e) {
            e.printStackTrace();
            errorMessage=e.getMessage();
            valid=false;
        }

        return String.valueOf(valid)+" "+errorMessage;
    }
}
