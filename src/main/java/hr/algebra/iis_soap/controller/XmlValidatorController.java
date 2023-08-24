package hr.algebra.iis_soap.controller;

import hr.algebra.iis_soap.service.XsdValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.File;

@Controller
public class XmlValidatorController {

    XsdValidator xsdValidator=new XsdValidator();
    private final String xmlBadPath="/src/main/resources/badCities.xml";
    private final String xmlGoodPath ="/src/main/resources/cities.xml";
    @GetMapping()
    public String validateXML()
    {
        return "xmlValidator";
    }

    @PostMapping("/xmlValidator.html")
    public String validateWithXSD(Model model){
    //prvi zadatak
        File currentFile = new File("");
        String absolutePath = currentFile.getAbsolutePath();
        String returnValue="Cities.xml validated with cities.xsd is:"+ String.valueOf(xsdValidator .isXMLValidXSD(absolutePath+ xmlGoodPath));
        String returnValue1="badcities.xml validates against cities.xsd is:" + String.valueOf(xsdValidator.isXMLValidXSD( absolutePath+ xmlBadPath));
        model.addAttribute("xsdMessage",returnValue);
        model.addAttribute("xsdMessage1",returnValue1);
        return "xmlValidator";
    }
    @PostMapping("/xmlValidatorRng.html")
    public String validateWithRng(Model model){
        //drugi zadatak
        File currentFile = new File("");
        String absolutePath = currentFile.getAbsolutePath();
        String returnValue="Cities.xml validated with citiesRng.Rng is:"+ String.valueOf(xsdValidator .isXMLValidRNG(absolutePath+ xmlGoodPath));
        String returnValue1="badcities.xml validates against cities.xsd is:" + String.valueOf(xsdValidator.isXMLValidRNG( absolutePath+ xmlBadPath));
        model.addAttribute("rngMessage",returnValue);
        model.addAttribute("rngMessage1",returnValue1);
        return "xmlValidator";
    }
    @PostMapping("/searchSoap.html")
    public String searchCities(Model model){
        //treci zadatak
        File currentFile = new File("");
        String absolutePath = currentFile.getAbsolutePath();
        String returnValue="Cities.xml validated with citiesRng.Rng is:"+ String.valueOf(xsdValidator .isXMLValidRNG(absolutePath+ xmlGoodPath));
        String returnValue1="badcities.xml validates against cities.xsd is:" + String.valueOf(xsdValidator.isXMLValidRNG( absolutePath+ xmlBadPath));
        model.addAttribute("rngMessage",returnValue);
        model.addAttribute("rngMessage1",returnValue1);
        return "xmlValidator";
    }
    @PostMapping("/searchXPath.html")
    public String searchCitiesXpath(Model model){
        //treci zadatak part 2 za svaki slucaj
        File currentFile = new File("");
        String absolutePath = currentFile.getAbsolutePath();
        String returnValue="Cities.xml validated with citiesRng.Rng is:"+ String.valueOf(xsdValidator .isXMLValidRNG(absolutePath+ xmlGoodPath));
        String returnValue1="badcities.xml validates against cities.xsd is:" + String.valueOf(xsdValidator.isXMLValidRNG( absolutePath+ xmlBadPath));
        model.addAttribute("rngMessage",returnValue);
        model.addAttribute("rngMessage1",returnValue1);
        return "xmlValidator";
    }


}
