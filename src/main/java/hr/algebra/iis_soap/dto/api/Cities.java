package hr.algebra.iis_soap.dto.api;

import jakarta.xml.bind.annotation.XmlElement;

import java.util.List;

public class Cities {
    @XmlElement(name="item")
    List<Item> items;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
