package blog.bindingfile;

import java.io.FileInputStream;
import java.util.*;
import javax.xml.bind.*;
import javax.xml.transform.stream.StreamSource;
import org.eclipse.persistence.jaxb.JAXBContextProperties;

public class Demo {
    
    public static void main(String[] args) throws Exception {
        Map<String, Object> properties = new HashMap<String, Object>(3);
        properties.put(JAXBContextProperties.OXM_METADATA_SOURCE, "blog/bindingfile/binding.json");
        properties.put(JAXBContextProperties.MEDIA_TYPE, "application/json");
        properties.put(JAXBContextProperties.JSON_INCLUDE_ROOT, false);
        JAXBContext jc = JAXBContext.newInstance("blog.bindingfile", Customer.class.getClassLoader() , properties);


        Unmarshaller unmarshaller = jc.createUnmarshaller();
        StreamSource json = new StreamSource(new FileInputStream("src/main/resources/blog/bindingfile/input.json"));
        Customer customer = unmarshaller.unmarshal(json, Customer.class).getValue();

        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(customer, System.out);
    }

}