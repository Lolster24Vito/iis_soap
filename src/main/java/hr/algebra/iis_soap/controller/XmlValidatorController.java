package hr.algebra.iis_soap.controller;

import hr.algebra.iis_soap.dto.form.SearchDto;
import hr.algebra.iis_soap.dto.xml.Country;
import hr.algebra.iis_soap.dto.xml.GetCountriesResponse;
import hr.algebra.iis_soap.dto.xml.GetCountryRequest;
import hr.algebra.iis_soap.dto.xml.GetCountryResponse;
import hr.algebra.iis_soap.endpoint.CountryEndpoint;
import hr.algebra.iis_soap.service.CountryService;
import hr.algebra.iis_soap.service.XsdValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class XmlValidatorController {

    @Autowired
    private CountryService service;

    @Autowired
    private CountryEndpoint countryEndpoint;

    XsdValidator xsdValidator=new XsdValidator();
    private final String xmlBadPath="/src/main/resources/badCities.xml";
    private final String xmlGoodPath ="/src/main/resources/cities.xml";
    @GetMapping()
    public String validateXML()
    {
        return "xmlValidator";
    }

    @RequestMapping(path = {"/","/search"})
    public String home(Country country, Model model, String keyword){
        if(keyword!=null){
            List<Country>list=new ArrayList<>();
            Country countrySearched=service.searchCountry(keyword);
            list.add(countrySearched);
            model.addAttribute("list",list);
        }
        else{
            List<Country> allCountries = service.getAllCountries();
            model.addAttribute("list",allCountries);
        }
        return "xmlValidator";
    }
    /*
     @RequestMapping(path = {"/","/search"})
 public String home(Shop shop, Model model, String keyword) {
  if(keyword!=null) {
   List<Shop> list = service.getByKeyword(keyword);
   model.addAttribute("list", list);
  }else {
  List<Shop> list = service.getAllShops();
  model.addAttribute("list", list);}
  return "index";
 }
}
     */

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
    //not working version but backup
    /*
    @PostMapping("/searchSoap.html")
    public String searchCities(Model model){
        //treci zadatak
        GetCountryRequest getCountryRequest=new GetCountryRequest();
        String searchKeyword=model.getAttribute("query");
        getCountryRequest.setName(searchKeyword);
        GetCountryResponse countryResponse = countryEndpoint.getCountry(getCountryRequest);
       /* final String uri = "http://localhost:8080/ws";
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> params = new HashMap<String, String>();
        params.put("str", "my_String");
        String response = restTemplate.getForObject(uri, String.class, params);
*/
      /*  File currentFile = new File("");
        String absolutePath = currentFile.getAbsolutePath();
        String returnValue="Cities.xml validated with citiesRng.Rng is:"+ String.valueOf(xsdValidator .isXMLValidRNG(absolutePath+ xmlGoodPath));
        String returnValue1="badcities.xml validates against cities.xsd is:" + String.valueOf(xsdValidator.isXMLValidRNG( absolutePath+ xmlBadPath));
        model.addAttribute("rngMessage",returnValue);
        model.addAttribute("rngMessage1",returnValue1);*/
    /*
        return "xmlValidator";
    }
    */
    @PostMapping("/searchCities.html")
    public String searchCities(@ModelAttribute SearchDto searchDto, BindingResult errors, Model model) {
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
    @PostMapping("/xmlSoapCities.html")
    public String queryCountryCities(@RequestParam String query,Model model){
        //treci zadatak
        GetCountryRequest getCountryRequest=new GetCountryRequest();
        getCountryRequest.setName(query);
        GetCountriesResponse countryResponse = countryEndpoint.getCountries(getCountryRequest);
        model.addAttribute("countryList",countryResponse.getCountry());
        return "xmlValidator";

    }


}
