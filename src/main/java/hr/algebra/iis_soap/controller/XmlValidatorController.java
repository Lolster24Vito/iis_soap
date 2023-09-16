package hr.algebra.iis_soap.controller;

import hr.algebra.iis_soap.dto.form.SearchDto;
import hr.algebra.iis_soap.dto.xml.Root;
import hr.algebra.iis_soap.dto.xml.GetCountriesResponse;
import hr.algebra.iis_soap.dto.xml.GetCountryRequest;
import hr.algebra.iis_soap.endpoint.CountryEndpoint;
import hr.algebra.iis_soap.service.CountryService;
import hr.algebra.iis_soap.service.RpcService;
import hr.algebra.iis_soap.service.XpathService;
import hr.algebra.iis_soap.service.XsdValidator;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.PropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StreamUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Controller
public class XmlValidatorController {

    @Autowired
    private CountryService service;

    @Autowired
    private CountryEndpoint countryEndpoint;

    @Autowired
    private XpathService xpathService;

    @Autowired
    private RpcService rpcService;

    XsdValidator xsdValidator=new XsdValidator();
    private final String xmlBadPath="/src/main/resources/badCities.xml";
    private final String xmlGoodPath ="/src/main/resources/cities.xml";
    @GetMapping()
    public String validateXML()
    {

        return "xmlValidator";
    }

    @RequestMapping(path = {"/","/search"})
    public String home(Root country, Model model, String keyword){
        if(keyword!=null){
            List<Root>list=new ArrayList<>();
            Root countrySearched=service.searchCountry(keyword);
            list.add(countrySearched);
            model.addAttribute("list",list);
        }
        else{
            List<Root> allCountries = service.getAllCountries();
            model.addAttribute("list",allCountries);
        }
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

    @PostMapping("/searchCities.html")
    public String searchCities(@ModelAttribute SearchDto searchDto, BindingResult errors, Model model) {
        return "xmlValidator";
    }


    @PostMapping("/searchXPath.html")
    public String searchXPath(@RequestParam String query,Model model){
        //treci zadatak part 2 za svaki slucaj
        File currentFile = null;

         //   currentFile = ResourceUtils.getFile("classpath:deleteTutorial.xml");
            currentFile=new File("target/classes/individualCountry.xml");

        String results="";
        //String absolutePath = currentFile.getAbsolutePath();
        //String returnValue="Cities.xml validated with citiesRng.Rng is:"+ String.valueOf(xsdValidator .isXMLValidRNG(absolutePath+ xmlGoodPath));
        try {
             results= xpathService.UseExpression(query,currentFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //"/Tutorials/Tutorial"
        model.addAttribute("xPath",results);
        return "xmlValidator";
    }
    @PostMapping("/xmlSoapCities.html")
    public String queryCountryCities(@RequestParam String query,Model model){
        //treci zadatak

        GetCountryRequest getCountryRequest=new GetCountryRequest();
        getCountryRequest.setName(query);
        GetCountriesResponse countryResponse = countryEndpoint.getCountries(getCountryRequest);
        model.addAttribute("countryList",countryResponse.getCountry());

        String xml="";
        try {
           xml=readFile("target/classes/individualCountry.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        model.addAttribute("countryXML",xml);

        /*
         JAXBContext jaxbContext = JAXBContext.newInstance(Question.class);

        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        Question que= (Question) jaxbUnmarshaller.unmarshal(file);

        System.out.println(que.getId()+" "+que.getQuestionname());
        System.out.println("Answers:");
         */

        return "xmlValidator";

    }
    @PostMapping("/rpcWeather.html")
    public String rpcWeather(@RequestParam String query,Model model){
        //peti zadatak
        String rpcResult = rpcService.getTemperature(query);
        model.addAttribute("rpcResult",rpcResult);


        return "xmlValidator";

    }

    private String readFile(String path)
            throws IOException
    {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, StandardCharsets.UTF_8);
    }


}
