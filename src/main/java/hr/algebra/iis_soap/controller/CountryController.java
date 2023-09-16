package hr.algebra.iis_soap.controller;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import hr.algebra.iis_soap.dto.xml.countrieslist.Root;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.nio.file.Files;

@RestController
public class CountryController {

    @GetMapping("/countries")
    public Root getCountries() throws JAXBException, IOException {

        File xmlFile = null;
        try {
            xmlFile = ResourceUtils.getFile("classpath:countries.xml");
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }
        String xmlResponse="";
        try {
            xmlResponse = new String(Files.readAllBytes(xmlFile.toPath()));
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

        JAXBContext jaxbContext = JAXBContext.newInstance(Root.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        StringReader reader = new StringReader(xmlResponse);
        Root unmarshal = (Root) unmarshaller.unmarshal(reader);
        //new test method
        XmlMapper xmlMapper=new XmlMapper();
       // Root groot=xmlMapper.readValue(xmlResponse,Root.class);

        String modifiedXml = modifyToRoot(xmlResponse);

        ClassLoader classLoader = getClass().getClassLoader();
        File countryCreatedfile = new File(classLoader.getResource(".").getFile() + "/countryCreated.xml");
        if (countryCreatedfile.createNewFile()) {
            System.out.println("File is created!");
        } else {
            System.out.println("File already exists.");
        }

        SaveStringToFile(modifiedXml);
        Root root1 = xmlMapper.readValue(modifiedXml, Root.class);

        return root1;
    }
    private String modifyToRoot(String xmlCode){
        String modifiedXml = xmlCode.replaceAll("<([A-Z]{2})>", "<country><countryCode>$1</countryCode><countryName>");
        modifiedXml = modifiedXml.replaceAll("</([A-Z]{2})>", "</countryName> </country>");
        return modifiedXml;
    }
    private void SaveStringToFile(String text){
        try {
            // Replace "example.txt" with the desired file name
            String fileName = "countryCreated.xml";

            // Create a File object for the resource folder
            File resourceFolder = ResourceUtils.getFile("classpath:");

            // Create the file path within the resource folder
            File file = new File(resourceFolder, fileName);

            // Write content to the file
            try (FileWriter fileWriter = new FileWriter(file);
                 BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
                bufferedWriter.write("This is some sample content that will be written to the file.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}