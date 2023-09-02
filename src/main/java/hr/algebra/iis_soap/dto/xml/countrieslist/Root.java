package hr.algebra.iis_soap.dto.xml.countrieslist;
import hr.algebra.iis_soap.dto.api.Country;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.NoArgsConstructor;

import java.util.List;


@XmlRootElement(name = "root")
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor
public class Root {
    @XmlElement(name = "countries")
    private List<Country> countries;
    @XmlElement(name = "status")
    private String status;

    public List<Country> getCountries() {
        return countries;
    }

    public void setCountries(List<Country> countries) {
        this.countries = countries;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}