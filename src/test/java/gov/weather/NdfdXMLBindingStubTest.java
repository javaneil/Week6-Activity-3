package gov.weather;

import org.junit.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import java.io.StringReader;

import static org.junit.Assert.*;

/**
 * Created by Neil on 2/21/2017.
 */
public class NdfdXMLBindingStubTest {
    @Test
    public void latLonListZipCode() throws Exception {
        NdfdXMLBindingStub binding = (NdfdXMLBindingStub) new NdfdXMLLocator().getndfdXMLPort() ;

        String result = binding.latLonListZipCode( "53719" ) ;
        // Setting expected value to ??? will fail, but allows us to see the returned value
//        assertEquals( "Result did not match expected value", "???", result ) ;
        String latlon = unmarshallResult( result ) ;
        assertEquals( "43.0798,-89.3875", latlon ) ;
    }

    private String unmarshallResult( String response ) throws Exception {
        JAXBContext jaxbContext = JAXBContext.newInstance( DwmlType.class ) ;
        try {
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller() ;
            DwmlType dwml = (DwmlType) jaxbUnmarshaller.unmarshal( new StringReader( response ) ) ;
            return dwml.getLatLonList() ;
        } catch (JAXBException e) {
            e.printStackTrace() ;
        }
        return null ;
    }
}