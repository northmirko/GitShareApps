package com.vipmobile.Pch24;

import java.io.File;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws JAXBException, IOException
    {
    	
    	ArrayList<ManagedObject> managed = new ArrayList<ManagedObject>();
    	FileReader reader  = new FileReader(new File("C:\\Users\\m.kovacevic\\Desktop\\PCH24Enable.csv"));
        CSVFormat csv = new CSVFormat();
        List<Pch24Bean> pch24beans = csv.readWithCsvBeanReader(reader);
        Iterator<Pch24Bean> iter = pch24beans.iterator();
        while(iter.hasNext()){
        	P p1 = new P();
            P p2 = new P();
            
            p1.setName("NbrOfSCCPCHs");
            p2.setName("PCH24kbpsEnabled");
            Pch24Bean  bean = iter.next();
            p1.setValue("2");
            p2.setValue("Enabled");
            

            ManagedObject object = new ManagedObject();

             ArrayList<P> p_elements = new ArrayList<P>();
             p_elements.add(p1);
             p_elements.add(p2);
             
             object.setP(p_elements);
             object.setDistName(bean.getCO_DN());
             object.setClass_atribute("WCEL");
             object.setVersion("RN6.0_2.0");
             object.setId("");
             object.setOperation("update");
             managed.add(object);
            ///break;
        }
    	System.out.println( "Hello World!" );
       
    	CmData cmData = new CmData();
    	cmData.setId("PlanConfiguration( 5878 )");
    	cmData.setName("PCH24_TEST");
    	cmData.setScope("all");
    	cmData.setType("plan");
    	Log log  = new Log();
    	log.setAction("created");
    	log.setAppInfo("PlanExporter");
    	log.setDateTime("2013-11-28T14:26:50");
    	log.setValue("UIValues are used");
    	Header header = new Header();
    	header.setLog(log);
    	cmData.setHeader(header);
    	cmData.setManagedObject(managed);
    	Raml raml = new Raml();
    	raml.setCmData(cmData);
    	raml.setVersion("2.0");
    	raml.setXmlns("raml20.xsd");
        JAXBContext context = JAXBContext.newInstance(Raml.class);
        
        Marshaller m = context.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        
        m.marshal(raml, System.out);
        m.marshal(raml,new File("C:\\Users\\m.kovacevic\\Desktop\\PCH24Enable_New.xml") );
    }
}
