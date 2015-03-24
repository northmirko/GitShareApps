package com.xml.model;



import java.io.IOException;

import java.io.StringWriter;
import java.util.ArrayList;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class McRncSetManagedObjectClass 
{
    public StringWriter setUpXml(ResultSet rs, float decrease_db) throws JAXBException, IOException, SQLException
    {
    	float decrease = decrease_db*10;
    	
    	ArrayList<ManagedObject> managed = new ArrayList<ManagedObject>();
    	//FileReader reader  = new FileReader(new File("C:\\Users\\m.kovacevic\\Desktop\\PCH24Enable.csv"));
       
        //List<PtxBean> pch24beans = csv.readWithCsvBeanReader(reader);
       ///<PtxBean> iter = pch24beans.iterator();
    	
        while(rs.next()){
        	
        	/*
        	 * 
    PtxPrimaryCPICH
	PtxCellMax
	PtxMaxHSDPA
	PtxTarget
	PtxTargetPSMin
	PtxTargetPSMax
	PtxHighHSDPAPwr
        	 */
        	 String dn = rs.getString(1);
   	      Integer ptx1 = rs.getInt(2);
   	      Integer ptx2 = rs.getInt(3);
   	      Integer ptx3 = rs.getInt(4);
   	      Integer ptx4 = rs.getInt(5);
   	      Integer ptx5 = rs.getInt(6);
   	      Integer ptx6 = rs.getInt(7);
   	      Integer ptx7 = rs.getInt(8);
        	
   	        P p1 = new P();
            P p2 = new P();
            P p3 = new P();
            P p4 = new P();
            P p5 = new P();
            P p6 = new P();
            P p7 = new P();
           
            
            p1.setName("PtxPrimaryCPICH");
            p2.setName("PtxCellMax");
            p3.setName("PtxMaxHSDPA");
            p4.setName("PtxTarget"); 
            p5.setName("PtxTargetPSMin");
            p6.setName("PtxTargetPSMax");
            p7.setName("PtxHighHSDPAPwr");
           
            
            
            String x = Float.toString(ptx1-decrease);
            //x = x.split(".")[0];
           // System.out.println(x);
           // System.out.println(x.substring(0, 3));
            x = x.substring(0, 3); 
            x = x.substring(0, 2) + "." + x.substring(2, x.length());
            p1.setValue(x);
            
            x = Float.toString(ptx2-decrease);
            x = x.substring(0, 3); 
            x = x.substring(0, 2) + "." + x.substring(2, x.length());
            p2.setValue(x);
            
            x = Float.toString(ptx3-decrease);
            x = x.substring(0, 3); 
            x = x.substring(0, 2) + "." + x.substring(2, x.length());
            p3.setValue(x);
            
            x = Float.toString(ptx4-decrease);
            x = x.substring(0, 3); 
            x = x.substring(0, 2) + "." + x.substring(2, x.length());
            p4.setValue(x);
            
            x = Float.toString(ptx5-decrease);
            x = x.substring(0, 3); 
            x = x.substring(0, 2) + "." + x.substring(2, x.length());
            p5.setValue(x);
            
            x = Float.toString(ptx6-decrease);
            x = x.substring(0, 3); 
            x = x.substring(0, 2) + "." + x.substring(2, x.length());
            p6.setValue(x);
            
            x = Float.toString(ptx7-decrease);
            x = x.substring(0, 3); 
            x = x.substring(0, 2) + "." + x.substring(2, x.length());
            p7.setValue(x);
           
            

            ManagedObject object = new ManagedObject();

             ArrayList<P> p_elements = new ArrayList<P>();
             p_elements.add(p1);
             p_elements.add(p2);
             p_elements.add(p3);
             p_elements.add(p4);
             p_elements.add(p5);
             p_elements.add(p6);
             p_elements.add(p7);
             
             object.setP(p_elements);
             object.setDistName(dn);
             object.setClass_atribute("WCEL");
             object.setVersion("mcRN3.0");
             object.setId("");
             object.setOperation("update");
             managed.add(object);
            ///break;
        }
    	//System.out.println( "Hello World!" );
       
    	CmData cmData = new CmData();
    	
    	cmData.setId("PlanConfiguration( 5878 )");
    	cmData.setName("Power_wcel_change");
    	cmData.setScope("all");
    	cmData.setType("plan");
    	Log log  = new Log();
    	log.setAction("created");
    	log.setAppInfo("PlanExporter");
    	log.setDateTime("2014-04-16T14:26:50");
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
        // File file = 
       //FileOutputStream stream  = new FileOutputStream(file,false);
      // m.marshal(raml, System.out);
        StringWriter writer = new StringWriter();
        m.marshal(raml, writer );
       
        return writer;
        
      /// return raml;
    }
    public StringWriter setUpFRGT40W(ResultSet rs) throws JAXBException, IOException, SQLException
    {
    	
    	
    	ArrayList<ManagedObject> managed = new ArrayList<ManagedObject>();
    	//FileReader reader  = new FileReader(new File("C:\\Users\\m.kovacevic\\Desktop\\PCH24Enable.csv"));
       
        //List<PtxBean> pch24beans = csv.readWithCsvBeanReader(reader);
       ///<PtxBean> iter = pch24beans.iterator();
    	
        while(rs.next()){
        	
        	/*
        	 * 
    PtxPrimaryCPICH
	PtxCellMax
	PtxMaxHSDPA
	PtxTarget
	PtxTargetPSMin
	PtxTargetPSMax
	PtxHighHSDPAPwr
        	 */
        	 String dn = rs.getString(1);
   	     
        	
   	        P p1 = new P();
            P p2 = new P();
            P p3 = new P();
            P p4 = new P();
            P p5 = new P();
            P p6 = new P();
            P p7 = new P();
            P p8 = new P();
           
            
            p1.setName("PtxPrimaryCPICH");
            p2.setName("PtxCellMax");
            p3.setName("PtxMaxHSDPA");
            p4.setName("PtxTarget"); 
            p5.setName("PtxTargetPSMin");
            p6.setName("PtxTargetPSMax");
            p7.setName("PtxHighHSDPAPwr");
            p7.setName("PtxHighHSDPAPwr");
            p8.setName("PtxOffset");

            p1.setValue("36.0");
            p2.setValue("46.0");
            p3.setValue("46.0");
            p4.setValue("44.0");
            p5.setValue("39.0");
            p6.setValue("42.0");
            p7.setValue("43.0");
            p8.setValue("0.8");
           
            
            
           

            ManagedObject object = new ManagedObject();

             ArrayList<P> p_elements = new ArrayList<P>();
             p_elements.add(p1);
             p_elements.add(p2);
             p_elements.add(p3);
             p_elements.add(p4);
             p_elements.add(p5);
             p_elements.add(p6);
             p_elements.add(p7);
             p_elements.add(p8);
             
             object.setP(p_elements);
             object.setDistName(dn);
             object.setClass_atribute("WCEL");
             object.setVersion("mcRN3.0");
             object.setId("");
             object.setOperation("update");
             managed.add(object);
            ///break;
        }
    	//System.out.println( "Hello World!" );
       
    	CmData cmData = new CmData();
    	
    	cmData.setId("PlanConfiguration( 5878 )");
    	cmData.setName("Power_param_frgt_40_set_mcrnc");
    	cmData.setScope("all");
    	cmData.setType("plan");
    	Log log  = new Log();
    	log.setAction("created");
    	log.setAppInfo("PlanExporter");
    	log.setDateTime("2014-04-16T14:26:50");
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
        // File file = 
       //FileOutputStream stream  = new FileOutputStream(file,false);
      // m.marshal(raml, System.out);
        StringWriter writer = new StringWriter();
        m.marshal(raml, writer );
       
        return writer;
        
      /// return raml;
    }
    public StringWriter setTcellParametars(ResultSet rs) throws JAXBException, IOException, SQLException
    {
    	
    	
    	ArrayList<ManagedObject> managed = new ArrayList<ManagedObject>();
    	//FileReader reader  = new FileReader(new File("C:\\Users\\m.kovacevic\\Desktop\\PCH24Enable.csv"));
       
        //List<PtxBean> pch24beans = csv.readWithCsvBeanReader(reader);
       ///<PtxBean> iter = pch24beans.iterator();
    	
        while(rs.next()){
        	
        	/*
        	 * 
    PtxPrimaryCPICH
	PtxCellMax
	PtxMaxHSDPA
	PtxTarget
	PtxTargetPSMin
	PtxTargetPSMax
	PtxHighHSDPAPwr
        	 */
        	 String dn = rs.getString(1);
        	 String name = rs.getString(2);
        	 
   	     
        	
   	        P p1 = new P();
            
           
            
            p1.setName("Tcell");
            if(name!=null){
            
           if(name.endsWith("U1")){
        	   p1.setValue("0 chips");
        	   
           }
           else if(name.endsWith("U2")){
        	   p1.setValue("512 chips");
        	   
           }
           else if(name.endsWith("U3")){
        	   p1.setValue("1536 chips");
        	   
           }
           else if(name.endsWith("D1")){
        	   p1.setValue("0 chips");
        	   
           }
           else if(name.endsWith("D2")){
        	   p1.setValue("512 chips");
        	   
           }
           else if(name.endsWith("D3")){
        	   p1.setValue("1536 chips");
        	   
           }
           else if(name.endsWith("T1")){
        	   p1.setValue("0 chips");
        	   
           }
           else if(name.endsWith("T2")){
        	   p1.setValue("512 chips");
        	   
           }
           else if(name.endsWith("T3")){
        	   p1.setValue("1536 chips");
        	   
           } 
            }
            
            	
           

            
           
            
            
           

            ManagedObject object = new ManagedObject();

             ArrayList<P> p_elements = new ArrayList<P>();
             p_elements.add(p1);
             
             
             object.setP(p_elements);
             object.setDistName(dn);
             object.setClass_atribute("WCEL");
             object.setVersion("mcRN3.0");
             object.setId("");
             object.setOperation("update");
             managed.add(object);
            ///break;
        }
    	//System.out.println( "Hello World!" );
       
    	CmData cmData = new CmData();
    	
    	cmData.setId("PlanConfiguration( 5878 )");
    	cmData.setName("Tcell value set");
    	cmData.setScope("all");
    	cmData.setType("plan");
    	Log log  = new Log();
    	log.setAction("created");
    	log.setAppInfo("PlanExporter");
    	log.setDateTime("2014-04-16T14:26:50");
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
        // File file = 
       //FileOutputStream stream  = new FileOutputStream(file,false);
      // m.marshal(raml, System.out);
        StringWriter writer = new StringWriter();
        m.marshal(raml, writer );
       
        return writer;
        
      /// return raml;
    }
}
