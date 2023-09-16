package hr.algebra.iis_soap.service;
/*
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
*/
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import hr.algebra.iis_soap.dto.api.Converter.Converter;
import hr.algebra.iis_soap.dto.api.CountriesList;
import hr.algebra.iis_soap.dto.xml.Root;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CountriesListService {

    public boolean isXMLValid(){
        return true;
    }
    //program uses com.mashape.unirest:unirest-java library for accessing http
    //com.mashape.unirest:unirest-java
    public CountriesList getCountriesList() throws  IOException {


        CountriesList countriesList=new CountriesList();
        Map<String,String> map=new HashMap<>();
        map.put("hrj","jrvatska");
        countriesList.setCountries(map);
        HttpResponse<String> response = null;

        try {
            response = Unirest.get("https://countries-cities.p.rapidapi.com/location/country/list")
                    .header("X-RapidAPI-Key", "9ff84b14c4msh3628e42628ebbcep1f3f38jsn0eef35d1537e")
                    .header("X-RapidAPI-Host", "countries-cities.p.rapidapi.com")
                    .asString();
        } catch (UnirestException e) {
            e.printStackTrace();
            //logger.error("Application error in: [" + e.getClass().getName() + "]", e);
        }
        CountriesList countriesList1 = Converter.fromJsonString(response.getBody());

        return countriesList1;
    }

    public Root getCountry(String code){
        HttpResponse<String> response = null;
        Root unmarshaledCountry = null;
        String searchGet="https://countries-cities.p.rapidapi.com/location/country/"+code+"?format=xml";
        try {
            response = Unirest.get(searchGet)
                    .header("X-RapidAPI-Key", "9ff84b14c4msh3628e42628ebbcep1f3f38jsn0eef35d1537e")
                    .header("X-RapidAPI-Host", "countries-cities.p.rapidapi.com")
                    .asString();
        } catch (UnirestException e) {
            e.printStackTrace();
            //logger.error("Application error in: [" + e.getClass().getName() + "]", e);
        }
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            File individualCountry = new File(classLoader.getResource(".").getFile() + "/individualCountry.xml");
            if (individualCountry.createNewFile()) {
                System.out.println("File is created!");
            } else {
                System.out.println("File already exists.");
            }
            String xml=formatSoItWorks(response.getBody());

           SaveStringToFile(xml,individualCountry);
            StringReader reader = new StringReader(xml);

            JAXBContext context = JAXBContext.newInstance(Root.class.getPackage().getName());
            Unmarshaller unmarshaller=context.createUnmarshaller();
            unmarshaledCountry = (Root) unmarshaller.unmarshal(individualCountry);




        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException | TransformerException | SAXException e) {
            e.printStackTrace();
        }


        return unmarshaledCountry;
    }
//root needs
    private String formatSoItWorks(String xml) throws IOException, SAXException, TransformerException, ParserConfigurationException {

        int rootStart = xml.indexOf("<root>");
        int rootEnd = xml.indexOf("</root>");

        if (rootStart != -1 && rootEnd != -1) {
            String rootElement = xml.substring(rootStart, rootEnd);
            String modifiedRoot = rootElement.replaceFirst(">", " xmlns=\"" + "http://www.algebra.hr/iis_soap/dto/xml" + "\">");

            return xml.substring(0, rootStart) + modifiedRoot + xml.substring(rootEnd);
        } else {
            // Handle the case where <root> and </root> are not found
            return xml;
        }
    }


    /*
    ClassLoader classLoader = getClass().getClassLoader();
        File countryCreatedfile = new File(classLoader.getResource(".").getFile() + "/countryCreated.xml");
        if (countryCreatedfile.createNewFile()) {
            System.out.println("File is created!");
        } else {
            System.out.println("File already exists.");
        }

        SaveStringToFile(modifiedXml,countryCreatedfile);
        Root root1 = xmlMapper.readValue(modifiedXml, Root.class);

        return root1;
    }
    private String modifyToRoot(String xmlCode){
        String modifiedXml = xmlCode.replaceAll("<([A-Z]{2})>", "<country><countryCode>$1</countryCode><countryName>");
        modifiedXml = modifiedXml.replaceAll("</([A-Z]{2})>", "</countryName> </country>");
        return modifiedXml;
    }

     */
    public CountriesList getCountriesServicePost(){
        CountriesList countriesList=new CountriesList();
        Map<String,String> map=new HashMap<>();
        map.put("hr","jrvatska");
        countriesList.setCountries(map);
        return countriesList;
    }
    private void SaveStringToFile(String text, File file){
        try (PrintWriter out = new PrintWriter(file)) {
            out.println(text);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
