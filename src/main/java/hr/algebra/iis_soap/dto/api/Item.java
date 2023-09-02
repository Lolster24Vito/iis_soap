package hr.algebra.iis_soap.dto.api;

import com.fasterxml.jackson.annotation.*;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@XmlRootElement(name = "Item")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class Item {
    @XmlElement(name = "geonameid")
    private long geonameid;
    @XmlElement(name = "population")
    private long population;
    @XmlElement(name = "name")
    private String name;
    @XmlElement(name = "latitude")
    private double latitude;
    @XmlElement(name = "longitude")
    private double longitude;
    @XmlElement(name = "country")
    private Country country;
    @XmlElement(name = "division")
    private Division division;

    @JsonProperty("geonameid")
    public long getGeonameid() { return geonameid; }
    @JsonProperty("geonameid")
    public void setGeonameid(long value) { this.geonameid = value; }

    @JsonProperty("population")
    public long getPopulation() { return population; }
    @JsonProperty("population")
    public void setPopulation(long value) { this.population = value; }

    @JsonProperty("name")
    public String getName() { return name; }
    @JsonProperty("name")
    public void setName(String value) { this.name = value; }

    @JsonProperty("latitude")
    public double getLatitude() { return latitude; }
    @JsonProperty("latitude")
    public void setLatitude(double value) { this.latitude = value; }

    @JsonProperty("longitude")
    public double getLongitude() { return longitude; }
    @JsonProperty("longitude")
    public void setLongitude(double value) { this.longitude = value; }

    @JsonProperty("country")
    public Country getCountry() { return country; }
    @JsonProperty("country")
    public void setCountry(Country value) { this.country = value; }

    @JsonProperty("division")
    public Division getDivision() { return division; }
    @JsonProperty("division")
    public void setDivision(Division value) { this.division = value; }
}
