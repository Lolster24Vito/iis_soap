package hr.algebra.iis_soap.service;

import hr.algebra.iis_soap.dto.xml.Root;
import hr.algebra.iis_soap.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    @Autowired
    private CountryRepository repository;

    public Root searchCountry(String countryName){

        return repository.findCountries(countryName).stream().findFirst().get();
    }
    public List<Root> getAllCountries(){
        return repository.getAllCountry();
    }
}
