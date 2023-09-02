package hr.algebra.iis_soap.repository;

import hr.algebra.iis_soap.dto.xml.Country;
import hr.algebra.iis_soap.dto.xml.Currency;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class CountryRepository {

    private static final Map<String, Country> countries = new HashMap<>();

    @PostConstruct
    public void initData() {
        Country spain = new Country();
        spain.setName("Spain");
        spain.setCapital("Madrid");
        spain.setCurrency(Currency.EUR);
        spain.setPopulation(46704314);

        countries.put(spain.getName(), spain);

        Country poland = new Country();
        poland.setName("Poland");
        poland.setCapital("Warsaw");
        poland.setCurrency(Currency.PLN);
        poland.setPopulation(38186860);

        countries.put(poland.getName(), poland);

        Country uk = new Country();
        uk.setName("United Kingdom");
        uk.setCapital("London");
        uk.setCurrency(Currency.GBP);
        uk.setPopulation(63705000);

        countries.put(uk.getName(), uk);
    }

    public Country findCountry(String name) {
        return countries.get(name);
    }
    public List<Country> searchCountries(String name) {
        List<Country> list=new ArrayList<>();
        list.add(countries.get(name));
        return list;
    }
    public List<Country>getAllCountry(){
        return new ArrayList<>(countries.values());
    }

    public List<Country> findCountries(String name) {
       return countries.values().stream().filter(
               str->str.getName().contains(name))
               .collect(Collectors.toList());
    }
}
