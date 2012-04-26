package blog.bindingfile;

import java.io.File;
import java.util.*;
import javax.xml.bind.*;
import javax.xml.transform.stream.StreamSource;
import org.eclipse.persistence.jaxb.JAXBContextFactory;

public class Demo {

    public static void main(String[] args) throws Exception {
        Map<String, Object> properties = new HashMap<String, Object>(3);
        properties.put(JAXBContextFactory.ECLIPSELINK_OXM_XML_KEY, "blog/bindingfile/binding.json");
        properties.put("eclipselink.media-type", "application/json");
        properties.put("eclipselink.json.include-root", false);
        JAXBContext jc = JAXBContext.newInstance("blog.bindingfile", Customer.class.getClassLoader() , properties);

        Unmarshaller unmarshaller = jc.createUnmarshaller();
        StreamSource json = new StreamSource(new File("src/main/resources/blog/bindingfile/input.json"));
        Customer customer = (Customer) unmarshaller.unmarshal(json, Customer.class).getValue();

        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(customer, System.out);
    }

}