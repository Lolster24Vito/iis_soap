//
// This file was generated by the Eclipse Implementation of JAXB, v3.0.0 
// See https://eclipse-ee4j.github.io/jaxb-ri 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.09.03 at 01:08:15 AM CEST 
//


package hr.algebra.iis_soap.dto.xml;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Flag complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Flag"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="file" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="emoji" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="unicode" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Flag", propOrder = {
    "file",
    "emoji",
    "unicode"
})
public class Flag {

    @XmlElement(required = true)
    protected String file;
    @XmlElement(required = true)
    protected String emoji;
    @XmlElement(required = true)
    protected String unicode;

    /**
     * Gets the value of the file property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFile() {
        return file;
    }

    /**
     * Sets the value of the file property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFile(String value) {
        this.file = value;
    }

    /**
     * Gets the value of the emoji property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmoji() {
        return emoji;
    }

    /**
     * Sets the value of the emoji property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmoji(String value) {
        this.emoji = value;
    }

    /**
     * Gets the value of the unicode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnicode() {
        return unicode;
    }

    /**
     * Sets the value of the unicode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnicode(String value) {
        this.unicode = value;
    }

}
