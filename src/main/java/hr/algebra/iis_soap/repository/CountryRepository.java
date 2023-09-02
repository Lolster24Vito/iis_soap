package hr.algebra.iis_soap.repository;

import hr.algebra.iis_soap.dto.xml.*;
import jakarta.annotation.PostConstruct;
import org.apache.commons.codec.language.bm.Lang;
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
        spain.setGeonameid(123);
        spain.setName("Spain");
        spain.setCode("SP");

        // Create and set the capital
        Capital capital = new Capital();
        capital.setGeonameid(321);
        capital.setName("CAPtain capital");
        spain.setCapital(capital);

        spain.setAreaSize("asdf km");
        spain.setPopulation(46704314);
        spain.setPhoneCode("phone");
        spain.setIsInEu(true);

        // Create and set the languages
        Languages languages = new Languages();
        Languages.Entry entry2 = new Languages.Entry();
        entry2.setKey("es");
        entry2.setValue("Spanisho languago");
       // Map<String, String> languageMap = new HashMap<>();
        //languageMap.put("es", "Spanish language");
        languages.getEntry().add(entry2);
        //HERE
        spain.setLanguages(languages);

        // Create and set the flag
        Flag flag = new Flag();
        flag.setFile("https://commons.wikimedia.org/wiki/Special:FilePath/Flag_of_Spain.svg");
        flag.setEmoji("🇪🇸");
        flag.setUnicode("U+1F1EA U+1F1F8");
        spain.setFlag(flag);

        spain.setTld(".es");
        spain.setDependsOn(null);

        // Create and set the territories
        Territories territories = new Territories();
        /*Map<String, String> territoryMap = new HashMap<>();
        territoryMap.put("EA", "Ceuta");
        territoryMap.put("EX", "Melilla");*/
       // territories.getAny().add()
        Territories.Entry entry1 = new Territories.Entry();
        entry1.setKey("k");
        entry1.setValue("v");

        territories.getEntry().add(entry1);
                //HERE
        //spain.setTerritories(territoryMap);

        spain.setWikiId("Q29");
        spain.setWikiUrl("https://en.wikipedia.org/wiki/Spain");
        spain.setTotalIsoDivisions(17);
        spain.setTotalAdmDivisions(50);
        spain.setTotalCities(8114);

        // Create and set the currency
        Currency currency = new Currency();
        currency.setCode("EUR");
        currency.setName("Euro");
        spain.setCurrency(currency);

        // Create and set the timezone
        Timezone timezone = new Timezone();
        timezone.setTimezone("Europe/Madrid");
        timezone.setGtmOffset(3600);
        timezone.setGmtOffset(3600);
        timezone.setIsDaylightSaving(true);
        timezone.setCode("CEST");
        spain.setTimezone(timezone);

        // Create and set the continent
        Continent continent = new Continent();
        continent.setGeonameid(6255148);
        continent.setName("Europe");
        continent.setCode("EU");
        spain.setContinent(continent);

        spain.setStatus("success");

        countries.put(spain.getName(), spain);

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
