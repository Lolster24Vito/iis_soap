package hr.algebra.iis_soap.service;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

@Service
public class RpcService {
final private String url="https://vrijeme.hr/hrvatska_n.xml";
    public String getTemperature(String cityName){
        StringBuilder resultTemperatures= new StringBuilder();
        DocumentBuilderFactory dbf=DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(false);
        dbf.setValidating(false);
        try {
            DocumentBuilder db = dbf.newDocumentBuilder();
            URLConnection urlConnection = new URL(url).openConnection();
            urlConnection.addRequestProperty("Accept", "application/xml");
            Document doc = db.parse(urlConnection.getInputStream());
            doc.getDocumentElement().normalize();
            NodeList gradList=doc.getElementsByTagName("Grad");
            for (int i=0;i< gradList.getLength();i++){
                Element element=(Element) gradList.item(i);
                String elementCityName = element.getElementsByTagName("GradIme").item(0).getTextContent();
                if(elementCityName.toLowerCase().contains(cityName.toLowerCase())){
                    String temp=element.getElementsByTagName("Temp").item(0).getTextContent();
                    resultTemperatures.append(elementCityName).append("=").append(temp).append("C   \n");
                }
            }

        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
        return resultTemperatures.toString();
    }
}
