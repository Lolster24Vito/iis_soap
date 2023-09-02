package hr.algebra.iis_soap.dto.api;

import lombok.NoArgsConstructor;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "root1")
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor
public class Root1 {
    @XmlElement(name="Cities")
    Cities cities;
}
