package hr.algebra.iis_soap.dto.xml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import hr.algebra.iis_soap.dto.api.Country;
import java.util.List;

@XmlRootElement(name = "countries")
@XmlAccessorType(XmlAccessType.FIELD)

public class CountryList {


    @XmlElement(name = "countriess")
    private List<Country> countries = null;

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }
}
