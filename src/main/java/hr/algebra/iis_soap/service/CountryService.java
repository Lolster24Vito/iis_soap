package hr.algebra.iis_soap.service;

import hr.algebra.iis_soap.dto.xml.Country;
import hr.algebra.iis_soap.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    @Autowired
    private CountryRepository repository;

    public Country searchCountry(String countryName){

        return repository.findCountry(countryName);
    }
    public List<Country> getAllCountries(){
        return repository.getAllCountry();
    }
}
