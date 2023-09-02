package hr.algebra.iis_soap.dto.api;

import com.fasterxml.jackson.annotation.*;

public class Division {
    private String code;
    private long geonameid;

    @JsonProperty("code")
    public String getCode() { return code; }
    @JsonProperty("code")
    public void setCode(String value) { this.code = value; }

    @JsonProperty("geonameid")
    public long getGeonameid() { return geonameid; }
    @JsonProperty("geonameid")
    public void setGeonameid(long value) { this.geonameid = value; }
}
