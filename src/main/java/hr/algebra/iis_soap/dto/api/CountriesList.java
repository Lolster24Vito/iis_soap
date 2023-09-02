package hr.algebra.iis_soap.dto.api;


import com.fasterxml.jackson.annotation.*;
import java.util.Map;

public class CountriesList {
    private Map<String, String> countries;
    private String status;

    @JsonProperty("countries")
    public Map<String, String> getCountries() { return countries; }
    @JsonProperty("countries")
    public void setCountries(Map<String, String> value) { this.countries = value; }

    @JsonProperty("status")
    public String getStatus() { return status; }
    @JsonProperty("status")
    public void setStatus(String value) { this.status = value; }
}
