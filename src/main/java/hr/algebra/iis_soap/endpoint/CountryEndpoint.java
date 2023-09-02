package hr.algebra.iis_soap.endpoint;


import hr.algebra.iis_soap.dto.xml.GetCountriesResponse;
import hr.algebra.iis_soap.dto.xml.GetCountryRequest;
import hr.algebra.iis_soap.dto.xml.GetCountryResponse;
import hr.algebra.iis_soap.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class CountryEndpoint {

    private static final String NAMESPACE_URI = "http://www.algebra.hr/iis_soap/dto/xml";

    private CountryRepository countryRepository;

    @Autowired
    public CountryEndpoint(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    //uses http://localhost:8080/ws
    /*@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
    @ResponsePayload
    public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request) {
        GetCountryResponse response = new GetCountryResponse();
        response.setCountry(countryRepository.findCountry(request.getName()));

        return response;
    }*/
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
    @ResponsePayload
    public GetCountriesResponse getCountries(@RequestPayload GetCountryRequest request) {
        GetCountriesResponse response = new GetCountriesResponse();
        response.setCountry(countryRepository.findCountries(request.getName()));

        return response;
    }

}