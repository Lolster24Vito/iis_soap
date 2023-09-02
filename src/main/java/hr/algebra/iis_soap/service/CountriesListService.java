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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
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
    public CountriesList getCountriesServicePost(){
        CountriesList countriesList=new CountriesList();
        Map<String,String> map=new HashMap<>();
        map.put("hr","jrvatska");
        countriesList.setCountries(map);
        return countriesList;
    }
}
