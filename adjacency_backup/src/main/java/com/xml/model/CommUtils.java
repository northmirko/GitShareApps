package com.xml.model;

import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;




public class CommUtils {
	
	
	private Integer getCellCount(String wbts_dn){
		String query = "SELECT DISTINCT "+
				"  COUNT(*) AS COUNT "+
				"FROM "+
				"  CTP_COMMON_OBJECTS WBTS1_O "+
				"    JOIN "+
				"  CTP_COMMON_OBJECTS WCEL2_O "+
				"    ON "+
				"      WCEL2_O.CO_PARENT_GID = WBTS1_O.CO_GID AND "+
				"      WCEL2_O.CO_OC_ID = 987 "+
				"WHERE "+
				"  WBTS1_O.CO_OC_ID = 986 AND "+
				"  WBTS1_O.CO_DN = '"+wbts_dn+"' AND "+
				"  WCEL2_O.CO_STATE = 0";
		Integer cell_count = -1;
		
		try {
  	      Connection con=null;
  	      Class.forName("oracle.jdbc.driver.OracleDriver");
  	      con=DriverManager.getConnection(
  	        "jdbc:oracle:thin:@oss1db.netact.vipsrb.srb:1521:OSS","rdr","rdr");
  	      Statement sm=con.createStatement();
  	      ResultSet rs = sm.executeQuery(query);
  	      
  	      while (rs.next()) {
  	    	cell_count = rs.getInt(1);
  	      }
  	    	  
  	    sm.close();
	    con.close();
	   } 
	catch(Exception e){
		  e.printStackTrace();
		   }
		return cell_count;
		
	}
	
	private Integer getLcelCount(String wbts_dn){
		
		
		String query = "SELECT DISTINCT "+
				"  COUNT(*) AS COUNT "+
				"FROM "+
				"  CTP_COMMON_OBJECTS BTSSCW1_O "+
				"    JOIN "+
				"  CTP_COMMON_OBJECTS LCELW2_O "+
				"    ON "+
				"      LCELW2_O.CO_PARENT_GID = BTSSCW1_O.CO_GID AND "+
				"      LCELW2_O.CO_OC_ID = 210 "+
				"WHERE "+
				"  BTSSCW1_O.CO_OC_ID = 204 AND "+
				"  BTSSCW1_O.CO_DN = '"+wbts_dn+"/MRBTS-1/BTSSCW-1' AND "+
				"  LCELW2_O.CO_STATE = 0 ";
		
		
Integer lcel_count = -1;
		
		try {
  	      Connection con=null;
  	      Class.forName("oracle.jdbc.driver.OracleDriver");
  	      con=DriverManager.getConnection(
  	        "jdbc:oracle:thin:@oss1db.netact.vipsrb.srb:1521:OSS","rdr","rdr");
  	      Statement sm=con.createStatement();
  	      ResultSet rs = sm.executeQuery(query);
  	      
  	      while (rs.next()) {
  	    	lcel_count = rs.getInt(1);
  	    	System.out.println("lcel_count: "+lcel_count);
  	      }
  	    	  
  	    sm.close();
	    con.close();
	   } 
	catch(Exception e){
		  e.printStackTrace();
		   }
		return lcel_count;

		
	}

	
	private Integer checkThirdCarrier(String wbts_dn){
		String query = "SELECT DISTINCT "+
				"  COUNT(*) AS COUNT "+
				"FROM "+
				"  CTP_COMMON_OBJECTS WBTS1_O "+
				"    JOIN "+
				"  CTP_COMMON_OBJECTS WCEL2_O "+
				"    JOIN "+
				"  C_MRC_WCEL WCEL2_MA "+
				"    ON "+
				"      WCEL2_MA.CONF_ID = 1 AND "+
				"      WCEL2_MA.OBJ_GID = WCEL2_O.CO_GID "+
				"    ON "+
				"      WCEL2_O.CO_PARENT_GID = WBTS1_O.CO_GID AND "+
				"      WCEL2_O.CO_OC_ID = 987 "+
				"WHERE "+
				"  WBTS1_O.CO_OC_ID = 986 AND "+
				"  WBTS1_O.CO_DN = '"+wbts_dn+"' AND "+
				"  WCEL2_O.CO_STATE = 0 AND "+
				"  WCEL2_MA.WCEL_UARFCN = 10712 ";
		
Integer cel3rd_count = 0;
		
		try {
  	      Connection con=null;
  	      Class.forName("oracle.jdbc.driver.OracleDriver");
  	      con=DriverManager.getConnection(
  	        "jdbc:oracle:thin:@oss1db.netact.vipsrb.srb:1521:OSS","rdr","rdr");
  	      Statement sm=con.createStatement();
  	      ResultSet rs = sm.executeQuery(query);
  	      
  	      while (rs.next()) {
  	    	cel3rd_count = rs.getInt(1);
  	    	System.out.println("3rd cell: "+cel3rd_count);
  	      }
  	    	  
  	    sm.close();
	    con.close();
	   } 
	catch(Exception e){
		  e.printStackTrace();
		   }
		return cel3rd_count;

	}

    private String getDN(String wbtsname){
    	
    	String query = "SELECT DISTINCT "+
    			"  WBTS2_O.CO_DN AS DN "+
    			"FROM "+
    			"  CTP_COMMON_OBJECTS RNC1_O "+
    			"    JOIN "+
    			"  CTP_COMMON_OBJECTS WBTS2_O "+
    			"    ON "+
    			"      WBTS2_O.CO_PARENT_GID = RNC1_O.CO_GID AND "+
    			"      WBTS2_O.CO_OC_ID = 986 "+
    			"WHERE "+
    			"  RNC1_O.CO_OC_ID = 974 AND "+
    			"  WBTS2_O.CO_STATE = 0 AND "+
    			"  WBTS2_O.CO_NAME LIKE '%"+wbtsname+"%' ";
String name = "Wrong_Name";
		
		try {
  	      Connection con=null;
  	      Class.forName("oracle.jdbc.driver.OracleDriver");
  	      con=DriverManager.getConnection(
  	        "jdbc:oracle:thin:@oss1db.netact.vipsrb.srb:1521:OSS","rdr","rdr");
  	      Statement sm=con.createStatement();
  	      ResultSet rs = sm.executeQuery(query);
  	      
  	      while (rs.next()) {
  	    	name = rs.getString(1);
  	    	System.out.println(name);
  	      }
  	    	  
  	    sm.close();
	    con.close();
	   } 
	catch(Exception e){
		  e.printStackTrace();
		   }
		System.out.println(name);
		return name;
  
    }

    
    private Integer checkSectorCount(String wbts_dn){
    	
    	String query = "SELECT DISTINCT "+
    			"MAX(WCEL2_MA.WCEL_SECTOR_ID)  "+
    			"FROM "+
    			"  CTP_COMMON_OBJECTS WBTS1_O "+
    			"    JOIN "+
    			"  CTP_COMMON_OBJECTS WCEL2_O "+
    			"    JOIN "+
    			"  C_MRC_WCEL WCEL2_MA "+
    			"    ON "+
    			"      WCEL2_MA.CONF_ID = 1 AND "+
    			"      WCEL2_MA.OBJ_GID = WCEL2_O.CO_GID "+
    			"    ON "+
    			"      WCEL2_O.CO_PARENT_GID = WBTS1_O.CO_GID AND "+
    			"      WCEL2_O.CO_OC_ID = 987 "+
    			"WHERE "+
    			"  WBTS1_O.CO_OC_ID = 986 AND "+
    			"  WBTS1_O.CO_DN = '"+wbts_dn+"' AND "+
    			"  WCEL2_O.CO_STATE = 0 ";
    	
    	
Integer sector_count = -1;
		
		try {
  	      Connection con=null;
  	      Class.forName("oracle.jdbc.driver.OracleDriver");
  	      con=DriverManager.getConnection(
  	        "jdbc:oracle:thin:@oss1db.netact.vipsrb.srb:1521:OSS","rdr","rdr");
  	      Statement sm=con.createStatement();
  	      ResultSet rs = sm.executeQuery(query);
  	      
  	      while (rs.next()) {
  	    	sector_count = rs.getInt(1);
  	      }
  	    	  
  	    sm.close();
	    con.close();
	   } 
	catch(Exception e){
		  e.printStackTrace();
		   }
		return sector_count;

    	
    }
    
   private List<String> getLcelDN(String btsscw){
	   
	   
	   List<String> lcel_list = new ArrayList<String>();
	   String query = "SELECT DISTINCT "+
			   "  LCELW2_O.CO_DN AS DN "+
			   "FROM "+
			   "  CTP_COMMON_OBJECTS BTSSCW1_O "+
			   "    JOIN "+
			   "  CTP_COMMON_OBJECTS LCELW2_O "+
			   "    ON "+
			   "      LCELW2_O.CO_PARENT_GID = BTSSCW1_O.CO_GID AND "+
			   "      LCELW2_O.CO_OC_ID = 210 "+
			   "WHERE "+
			   "  BTSSCW1_O.CO_OC_ID = 204 AND "+
			   "  BTSSCW1_O.CO_DN = '"+btsscw+"' AND "+
			   "  LCELW2_O.CO_STATE = 0 ";
	   
	   try {
 	      Connection con=null;
 	      Class.forName("oracle.jdbc.driver.OracleDriver");
 	      con=DriverManager.getConnection(
 	        "jdbc:oracle:thin:@oss1db.netact.vipsrb.srb:1521:OSS","rdr","rdr");
 	      Statement s=con.createStatement();
 	      
 	      ResultSet rs = s.executeQuery(query);
 	      while(rs.next()){
 	    	  lcel_list.add(rs.getString(1));
 	      }
	   }
	   catch(Exception e){
 		  e.printStackTrace();
 		   }
	   
	   return lcel_list;
   }
    
    
   
   
   public StringWriter Algorithm2(String wbts_dn) throws JAXBException{
   	
   	String btsscw = wbts_dn+"/MRBTS-1/BTSSCW-1";
   	List<ManagedObject> managed_list = new ArrayList<ManagedObject>();
   	ManagedObject scw = new ManagedObject();////prvi mamanegd objekat
   	Lista list_scw  = new Lista();
   	list_scw.setName("hsdpaSchedList");
   	
   	
   	//////
   	Item itemthrSep11  = new Item();
   	
   	P hsdpaThrStep_11 = new P();
   	hsdpaThrStep_11.setName("hsdpaThroughputStep");
   	hsdpaThrStep_11.setValue("12");
   	
   	P mod_11 = new P();
   	mod_11.setName("mod");
   	mod_11.setValue("FSM1");
   	
   	P sched_11  = new P();
   	sched_11.setName("sched");
   	sched_11.setValue("1");
   	
   	List<P> list_11 = new ArrayList<P>();
   	list_11.add(sched_11);
   	list_11.add(hsdpaThrStep_11);
   	list_11.add(mod_11);
   	
   	itemthrSep11.setP(list_11);
   	//////
   	
   	//////
       Item itemthrSep12  = new Item();
   	
   	P hsdpaThrStep_12 = new P();
   	hsdpaThrStep_12.setName("hsdpaThroughputStep");
   	hsdpaThrStep_12.setValue("HSDPA throughput is not allocated");
   	
   	P mod_12 = new P();
   	mod_12.setName("mod");
   	mod_12.setValue("FSM1");
   	
   	P sched_12  = new P();
   	sched_12.setName("sched");
   	sched_12.setValue("2");
   	
   	List<P> list_12 = new ArrayList<P>();
   	list_12.add(sched_12);
   	list_12.add(hsdpaThrStep_12);
   	list_12.add(mod_12);
   	
   	itemthrSep12.setP(list_12);
   	//////
   	
   	/////
   	Item itemthrSep21  = new Item();
   	
   	P hsdpaThrStep_21 = new P();
   	hsdpaThrStep_21.setName("hsdpaThroughputStep");
   	hsdpaThrStep_21.setValue("12");
   	
   	P mod_21 = new P();
   	mod_21.setName("mod");
   	mod_21.setValue("FSM2");
   	
   	P sched_21  = new P();
   	sched_21.setName("sched");
   	sched_21.setValue("1");
   	
   	List<P> list_21 = new ArrayList<P>();
   	list_21.add(sched_21);
   	list_21.add(hsdpaThrStep_21);
   	list_21.add(mod_21);
   	
   	itemthrSep21.setP(list_21);
   	/////
   	////
   	Item itemthrSep22  = new Item();
   	
   	P hsdpaThrStep_22 = new P();
   	hsdpaThrStep_22.setName("hsdpaThroughputStep");
   	hsdpaThrStep_22.setValue("HSDPA throughput is not allocated");
   	
   	P mod_22 = new P();
   	mod_22.setName("mod");
   	mod_22.setValue("FSM2");
   	
   	P sched_22  = new P();
   	sched_22.setName("sched");
   	sched_22.setValue("2");
   	
   	List<P> list_22 = new ArrayList<P>();
   	list_22.add(sched_22);
   	list_22.add(hsdpaThrStep_22);
   	list_22.add(mod_22);
   	
   	itemthrSep22.setP(list_22);
   	////
   	List<Item> thrSteplist = new ArrayList<Item>();
   	thrSteplist.add(itemthrSep11);
   	thrSteplist.add(itemthrSep12);
   	thrSteplist.add(itemthrSep21);
   	thrSteplist.add(itemthrSep22);
   	
   	list_scw.setItems(thrSteplist);
   	
   	List<Lista> lista_scw = new ArrayList<Lista>();
   	lista_scw.add(list_scw);
   	scw.setDistName(btsscw);
   	scw.setClass_atribute("BTSSCW");
   	scw.setDistName(btsscw);
   	scw.setOperation("update");
   	scw.setVersion("WN8.0");
   	scw.setId("");
   	scw.setList(lista_scw);
   	
   	P hsdpa3 = new P();
   	hsdpa3.setName("numberOfHSUPASet1");
   	hsdpa3.setValue("12");
   	
   	P hsupa1 = new P();
   	hsupa1.setName("numberOfHSDPASet3");
   	hsupa1.setValue("6");
   	
       List<P> hspa_setovi = new ArrayList<P>();
       hspa_setovi.add(hsupa1);
       hspa_setovi.add(hsdpa3);
       scw.setP(hspa_setovi);
       managed_list.add(scw);
   	
   	
   	//////Setovan BTSSCW
   	
       
       ////LCELW pic setovanja
   	
       List<String> lcel_list = this.getLcelDN(btsscw); 
       
       Iterator<String> iter = lcel_list.iterator();
       
       while(iter.hasNext()){
       	String lcel_dn = iter.next();
       	
       	ManagedObject lcel = new ManagedObject();
       	lcel.setDistName(lcel_dn);
       	lcel.setClass_atribute("LCELW");
       
       	lcel.setOperation("update");
       	lcel.setVersion("WN8.0");
       	lcel.setId("");
       	if(lcel_dn.endsWith("2")||lcel_dn.endsWith("7")){
       		P hspamap = new P();
       		hspamap.setName("hspaMapping");
       		hspamap.setValue("None");
       		P picpool = new P();
       		picpool.setName("picPool");
       		picpool.setValue("2");
       		List<P> lcel_setovi = new ArrayList<P>();
       		lcel_setovi.add(hspamap);
       		lcel_setovi.add(picpool);
       		lcel.setP(lcel_setovi);
       	}
       	else{
       		P hspamap = new P();
       		hspamap.setName("hspaMapping");
       		hspamap.setValue("None");
       		P picpool = new P();
       		picpool.setName("picPool");
       		picpool.setValue("1");
       		List<P> lcel_setovi = new ArrayList<P>();
       		lcel_setovi.add(hspamap);
       		lcel_setovi.add(picpool);
       		lcel.setP(lcel_setovi);
       		
       	}
       	managed_list.add(lcel);//////dodati novi managed objekti u listu
       }

       ////////
       
       ////////LCELGW1
       
       String lcelgw1 = btsscw+"/LCELGW-1";
       String lcelgw2 = btsscw+"/LCELGW-2";
       
       
       ManagedObject gw1 = new ManagedObject();
       gw1.setDistName(lcelgw1);
   	gw1.setClass_atribute("LCELGW");
   	gw1.setOperation("update");
   	gw1.setVersion("WN8.0");
   	gw1.setId("");
   	
   	P access_1 = new P();
   	access_1.setName("accessBbCapacity");
   	access_1.setValue("Sector based BB pooling is used.");
   	
   	P smodid_1 = new P();
   	smodid_1.setName("sModId");
   	smodid_1.setValue("1");
   	
   	P sharehsdpa_1 = new P();
   	sharehsdpa_1.setName("shareOfHSDPAUser");
   	sharehsdpa_1.setValue("50");
   	
   	P sharehsupa_1 = new P();
   	sharehsupa_1.setName("shareOfHSUPALicences");
   	sharehsupa_1.setValue("50");
   	
   	List<P> gw_p_1 = new ArrayList<P>();
   	gw_p_1.add(access_1);
   	gw_p_1.add(smodid_1);
   	gw_p_1.add(sharehsupa_1);
   	gw_p_1.add(sharehsdpa_1);
   	gw1.setP(gw_p_1);
   	
   	
   	
   	Lista cell_list_1 = new Lista();
   	cell_list_1.setName("lCelIdList");
   	Iterator<String> iter_1 = lcel_list.iterator();
   	List<P> lcell_list_1 = new ArrayList<P>();
   	while(iter_1.hasNext()){
   		String lcel_dn = iter_1.next();
   				if(!(lcel_dn.endsWith("2")||lcel_dn.endsWith("7"))){
   					P p = new P();
   					p.setValue(lcel_dn.split("/")[5].split("-")[1]);
   					lcell_list_1.add(p);
   				}
   	}
   	cell_list_1.setP(lcell_list_1);
   	
   	List<Lista> gw_lista_1 = new ArrayList<Lista>();
   	gw_lista_1.add(cell_list_1);
   	gw1.setList(gw_lista_1);
   	
   	
   	managed_list.add(gw1);
   	
   	
   	///////LCELGW2
   	
   	ManagedObject gw2 = new ManagedObject();
       gw2.setDistName(lcelgw2);
   	gw2.setClass_atribute("LCELGW");
   	gw2.setOperation("create");
   	gw2.setVersion("WN8.0");
   	gw2.setId("");
   	
   	P access_2 = new P();
   	access_2.setName("accessBbCapacity");
   	access_2.setValue("Sector based BB pooling is used.");
   	
   	P smodid_2 = new P();
   	smodid_2.setName("sModId");
   	smodid_2.setValue("2");
   	
   	P sharehsdpa_2 = new P();
   	sharehsdpa_2.setName("shareOfHSDPAUser");
   	sharehsdpa_2.setValue("50");
   	
   	P sharehsupa_2 = new P();
   	sharehsupa_2.setName("shareOfHSUPALicences");
   	sharehsupa_2.setValue("50");
   	
   	P cellgroupname_2 = new P();
   	cellgroupname_2.setName("cellGroupName");
   	cellgroupname_2.setValue("Group 2");
   	
   	
   	P dediCap = new P();
   	dediCap.setName("dedicatedBbCapacity");
   	dediCap.setValue("0");
   	
   	P hsupadediBd = new P();
   	hsupadediBd.setName("hsupaBbDecodCapacity");
   	hsupadediBd.setValue("0 Mbit/s");
   	
   	P hsupaMin = new P();
   	hsupaMin.setName("hsupaBbMinimumUsers");
   	hsupaMin.setValue("0");
   	
   	P minNum = new P();
   	minNum.setName("minNumHsfachUsers");
   	minNum.setValue("0");
   	
   	List<P> gw_p_2 = new ArrayList<P>();
   	gw_p_2.add(access_2);
   	gw_p_2.add(smodid_2);
   	gw_p_2.add(sharehsupa_2);
   	gw_p_2.add(sharehsdpa_2);
   	gw_p_2.add(hsupadediBd);
   	gw_p_2.add(dediCap);
   	gw_p_2.add(cellgroupname_2);
   	gw_p_2.add(minNum);
   	gw_p_2.add(hsupaMin);
   	gw2.setP(gw_p_2);
   	
   	
   	
   	Lista cell_list_2 = new Lista();
   	cell_list_2.setName("lCelIdList");
   	Iterator<String> iter_2 = lcel_list.iterator();
   	List<P> lcell_list_2 = new ArrayList<P>();
   	while(iter_2.hasNext()){
   		String lcel_dn = iter_2.next();
   				if(lcel_dn.endsWith("2")||lcel_dn.endsWith("7")){
   					P p = new P();
   					p.setValue(lcel_dn.split("/")[5].split("-")[1]);
   					lcell_list_2.add(p);
   				}
   	}
   	cell_list_2.setP(lcell_list_2);
   	
   	List<Lista> gw_lista_2 = new ArrayList<Lista>();
   	gw_lista_2.add(cell_list_2);
   	gw2.setList(gw_lista_2);
   	
   	
   	managed_list.add(gw2);
   	//////////////////////////
   	
       
   	
   	
   	
   	
   	CmData cmData = new CmData();
   	
   	cmData.setId("PlanConfiguration( 5878 )");
   	cmData.setName("LGC 2");
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
   	cmData.setManagedObject(managed_list);
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
      // m.marshal(raml, System.out);
		return writer;
   	
		
   	
   	
   }
    public StringWriter Algorithm3(String wbts_dn) throws JAXBException{
    	
    	String btsscw = wbts_dn+"/MRBTS-1/BTSSCW-1";
    	List<ManagedObject> managed_list = new ArrayList<ManagedObject>();
    	ManagedObject scw = new ManagedObject();////prvi mamanegd objekat
    	Lista list_scw  = new Lista();
    	list_scw.setName("hsdpaSchedList");
    	
    	
    	//////
    	Item itemthrSep11  = new Item();
    	
    	P hsdpaThrStep_11 = new P();
    	hsdpaThrStep_11.setName("hsdpaThroughputStep");
    	hsdpaThrStep_11.setValue("12");
    	
    	P mod_11 = new P();
    	mod_11.setName("mod");
    	mod_11.setValue("FSM1");
    	
    	P sched_11  = new P();
    	sched_11.setName("sched");
    	sched_11.setValue("1");
    	
    	List<P> list_11 = new ArrayList<P>();
    	list_11.add(sched_11);
    	list_11.add(hsdpaThrStep_11);
    	list_11.add(mod_11);
    	
    	itemthrSep11.setP(list_11);
    	//////
    	
    	//////
        Item itemthrSep12  = new Item();
    	
    	P hsdpaThrStep_12 = new P();
    	hsdpaThrStep_12.setName("hsdpaThroughputStep");
    	hsdpaThrStep_12.setValue("HSDPA throughput is not allocated");
    	
    	P mod_12 = new P();
    	mod_12.setName("mod");
    	mod_12.setValue("FSM1");
    	
    	P sched_12  = new P();
    	sched_12.setName("sched");
    	sched_12.setValue("2");
    	
    	List<P> list_12 = new ArrayList<P>();
    	list_12.add(sched_12);
    	list_12.add(hsdpaThrStep_12);
    	list_12.add(mod_12);
    	
    	itemthrSep12.setP(list_12);
    	//////
    	
    	/////
    	Item itemthrSep21  = new Item();
    	
    	P hsdpaThrStep_21 = new P();
    	hsdpaThrStep_21.setName("hsdpaThroughputStep");
    	hsdpaThrStep_21.setValue("12");
    	
    	P mod_21 = new P();
    	mod_21.setName("mod");
    	mod_21.setValue("FSM2");
    	
    	P sched_21  = new P();
    	sched_21.setName("sched");
    	sched_21.setValue("1");
    	
    	List<P> list_21 = new ArrayList<P>();
    	list_21.add(sched_21);
    	list_21.add(hsdpaThrStep_21);
    	list_21.add(mod_21);
    	
    	itemthrSep21.setP(list_21);
    	/////
    	////
    	Item itemthrSep22  = new Item();
    	
    	P hsdpaThrStep_22 = new P();
    	hsdpaThrStep_22.setName("hsdpaThroughputStep");
    	hsdpaThrStep_22.setValue("HSDPA throughput is not allocated");
    	
    	P mod_22 = new P();
    	mod_22.setName("mod");
    	mod_22.setValue("FSM2");
    	
    	P sched_22  = new P();
    	sched_22.setName("sched");
    	sched_22.setValue("2");
    	
    	List<P> list_22 = new ArrayList<P>();
    	list_22.add(sched_22);
    	list_22.add(hsdpaThrStep_22);
    	list_22.add(mod_22);
    	
    	itemthrSep22.setP(list_22);
    	////
    	List<Item> thrSteplist = new ArrayList<Item>();
    	thrSteplist.add(itemthrSep11);
    	thrSteplist.add(itemthrSep12);
    	thrSteplist.add(itemthrSep21);
    	thrSteplist.add(itemthrSep22);
    	
    	list_scw.setItems(thrSteplist);
    	
    	List<Lista> lista_scw = new ArrayList<Lista>();
    	lista_scw.add(list_scw);
    	scw.setDistName(btsscw);
    	scw.setClass_atribute("BTSSCW");
    	scw.setDistName(btsscw);
    	scw.setOperation("update");
    	scw.setVersion("WN8.0");
    	scw.setId("");
    	scw.setList(lista_scw);
    	
    	P hsdpa3 = new P();
    	hsdpa3.setName("numberOfHSUPASet1");
    	hsdpa3.setValue("12");
    	
    	P hsupa1 = new P();
    	hsupa1.setName("numberOfHSDPASet3");
    	hsupa1.setValue("6");
    	
        List<P> hspa_setovi = new ArrayList<P>();
        hspa_setovi.add(hsupa1);
        hspa_setovi.add(hsdpa3);
        scw.setP(hspa_setovi);
        managed_list.add(scw);
    	
    	
    	//////Setovan BTSSCW
    	
        
        ////LCELW pic setovanja
    	
        List<String> lcel_list = this.getLcelDN(btsscw); 
        
        Iterator<String> iter = lcel_list.iterator();
        
        while(iter.hasNext()){
        	String lcel_dn = iter.next();
        	
        	ManagedObject lcel = new ManagedObject();
        	lcel.setDistName(lcel_dn);
        	lcel.setClass_atribute("LCELW");
        
        	lcel.setOperation("update");
        	lcel.setVersion("WN8.0");
        	lcel.setId("");
        	if(lcel_dn.endsWith("2")||lcel_dn.endsWith("7")){
        		P hspamap = new P();
        		hspamap.setName("hspaMapping");
        		hspamap.setValue("None");
        		P picpool = new P();
        		picpool.setName("picPool");
        		picpool.setValue("2");
        		List<P> lcel_setovi = new ArrayList<P>();
        		lcel_setovi.add(hspamap);
        		lcel_setovi.add(picpool);
        		lcel.setP(lcel_setovi);
        	}
        	else{
        		P hspamap = new P();
        		hspamap.setName("hspaMapping");
        		hspamap.setValue("None");
        		P picpool = new P();
        		picpool.setName("picPool");
        		picpool.setValue("1");
        		List<P> lcel_setovi = new ArrayList<P>();
        		lcel_setovi.add(hspamap);
        		lcel_setovi.add(picpool);
        		lcel.setP(lcel_setovi);
        		
        	}
        	managed_list.add(lcel);//////dodati novi managed objekti u listu
        }

        ////////
        
        ////////LCELGW1
        
        String lcelgw1 = btsscw+"/LCELGW-1";
        String lcelgw2 = btsscw+"/LCELGW-2";
        
        
        ManagedObject gw1 = new ManagedObject();
        gw1.setDistName(lcelgw1);
    	gw1.setClass_atribute("LCELGW");
    	gw1.setOperation("update");
    	gw1.setVersion("WN8.0");
    	gw1.setId("");
    	
    	P access_1 = new P();
    	access_1.setName("accessBbCapacity");
    	access_1.setValue("Sector based BB pooling is used.");
    	
    	P smodid_1 = new P();
    	smodid_1.setName("sModId");
    	smodid_1.setValue("1");
    	
    	P sharehsdpa_1 = new P();
    	sharehsdpa_1.setName("shareOfHSDPAUser");
    	sharehsdpa_1.setValue("67");
    	
    	P sharehsupa_1 = new P();
    	sharehsupa_1.setName("shareOfHSUPALicences");
    	sharehsupa_1.setValue("67");
    	
    	List<P> gw_p_1 = new ArrayList<P>();
    	gw_p_1.add(access_1);
    	gw_p_1.add(smodid_1);
    	gw_p_1.add(sharehsupa_1);
    	gw_p_1.add(sharehsdpa_1);
    	gw1.setP(gw_p_1);
    	
    	
    	
    	Lista cell_list_1 = new Lista();
    	cell_list_1.setName("lCelIdList");
    	Iterator<String> iter_1 = lcel_list.iterator();
    	List<P> lcell_list_1 = new ArrayList<P>();
    	while(iter_1.hasNext()){
    		String lcel_dn = iter_1.next();
    				if(!(lcel_dn.endsWith("2")||lcel_dn.endsWith("7"))){
    					P p = new P();
    					p.setValue(lcel_dn.split("/")[5].split("-")[1]);
    					lcell_list_1.add(p);
    				}
    	}
    	cell_list_1.setP(lcell_list_1);
    	
    	List<Lista> gw_lista_1 = new ArrayList<Lista>();
    	gw_lista_1.add(cell_list_1);
    	gw1.setList(gw_lista_1);
    	
    	
    	managed_list.add(gw1);
    	
    	
    	///////LCELGW2
    	
    	ManagedObject gw2 = new ManagedObject();
        gw2.setDistName(lcelgw2);
    	gw2.setClass_atribute("LCELGW");
    	gw2.setOperation("create");
    	gw2.setVersion("WN8.0");
    	gw2.setId("");
    	
    	P access_2 = new P();
    	access_2.setName("accessBbCapacity");
    	access_2.setValue("Sector based BB pooling is used.");
    	
    	P smodid_2 = new P();
    	smodid_2.setName("sModId");
    	smodid_2.setValue("2");
    	
    	P sharehsdpa_2 = new P();
    	sharehsdpa_2.setName("shareOfHSDPAUser");
    	sharehsdpa_2.setValue("33");
    	
    	P sharehsupa_2 = new P();
    	sharehsupa_2.setName("shareOfHSUPALicences");
    	sharehsupa_2.setValue("33");
    	
    	P cellgroupname_2 = new P();
    	cellgroupname_2.setName("cellGroupName");
    	cellgroupname_2.setValue("Group 2");
    	
    	
    	P dediCap = new P();
    	dediCap.setName("dedicatedBbCapacity");
    	dediCap.setValue("0");
    	
    	P hsupadediBd = new P();
    	hsupadediBd.setName("hsupaBbDecodCapacity");
    	hsupadediBd.setValue("0 Mbit/s");
    	
    	P hsupaMin = new P();
    	hsupaMin.setName("hsupaBbMinimumUsers");
    	hsupaMin.setValue("0");
    	
    	P minNum = new P();
    	minNum.setName("minNumHsfachUsers");
    	minNum.setValue("0");
    	
    	List<P> gw_p_2 = new ArrayList<P>();
    	gw_p_2.add(access_2);
    	gw_p_2.add(smodid_2);
    	gw_p_2.add(sharehsupa_2);
    	gw_p_2.add(sharehsdpa_2);
    	gw_p_2.add(hsupadediBd);
    	gw_p_2.add(dediCap);
    	gw_p_2.add(cellgroupname_2);
    	gw_p_2.add(minNum);
    	gw_p_2.add(hsupaMin);
    	gw2.setP(gw_p_2);
    	
    	
    	
    	Lista cell_list_2 = new Lista();
    	cell_list_2.setName("lCelIdList");
    	Iterator<String> iter_2 = lcel_list.iterator();
    	List<P> lcell_list_2 = new ArrayList<P>();
    	while(iter_2.hasNext()){
    		String lcel_dn = iter_2.next();
    				if(lcel_dn.endsWith("2")||lcel_dn.endsWith("7")){
    					P p = new P();
    					p.setValue(lcel_dn.split("/")[5].split("-")[1]);
    					lcell_list_2.add(p);
    				}
    	}
    	cell_list_2.setP(lcell_list_2);
    	
    	List<Lista> gw_lista_2 = new ArrayList<Lista>();
    	gw_lista_2.add(cell_list_2);
    	gw2.setList(gw_lista_2);
    	
    	
    	managed_list.add(gw2);
    	//////////////////////////
    	
        
    	
    	
    	
    	
    	CmData cmData = new CmData();
    	
    	cmData.setId("PlanConfiguration( 5878 )");
    	cmData.setName("LGC 2");
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
    	cmData.setManagedObject(managed_list);
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
       // m.marshal(raml, System.out);
		return writer;
    	
		
    	
    	
    }
public StringWriter Algorithm4(String wbts_dn) throws JAXBException{
    	
    	String btsscw = wbts_dn+"/MRBTS-1/BTSSCW-1";
    	List<ManagedObject> managed_list = new ArrayList<ManagedObject>();
    	ManagedObject scw = new ManagedObject();////prvi mamanegd objekat
    	Lista list_scw  = new Lista();
    	list_scw.setName("hsdpaSchedList");
    	
    	
    	//////
    	Item itemthrSep11  = new Item();
    	
    	P hsdpaThrStep_11 = new P();
    	hsdpaThrStep_11.setName("hsdpaThroughputStep");
    	hsdpaThrStep_11.setValue("12");
    	
    	P mod_11 = new P();
    	mod_11.setName("mod");
    	mod_11.setValue("FSM1");
    	
    	P sched_11  = new P();
    	sched_11.setName("sched");
    	sched_11.setValue("1");
    	
    	List<P> list_11 = new ArrayList<P>();
    	list_11.add(sched_11);
    	list_11.add(hsdpaThrStep_11);
    	list_11.add(mod_11);
    	
    	itemthrSep11.setP(list_11);
    	//////
    	
    	//////
        Item itemthrSep12  = new Item();
    	
    	P hsdpaThrStep_12 = new P();
    	hsdpaThrStep_12.setName("hsdpaThroughputStep");
    	hsdpaThrStep_12.setValue("HSDPA throughput is not allocated");
    	
    	P mod_12 = new P();
    	mod_12.setName("mod");
    	mod_12.setValue("FSM1");
    	
    	P sched_12  = new P();
    	sched_12.setName("sched");
    	sched_12.setValue("2");
    	
    	List<P> list_12 = new ArrayList<P>();
    	list_12.add(sched_12);
    	list_12.add(hsdpaThrStep_12);
    	list_12.add(mod_12);
    	
    	itemthrSep12.setP(list_12);
    	//////
    	
    	/////
    	Item itemthrSep21  = new Item();
    	
    	P hsdpaThrStep_21 = new P();
    	hsdpaThrStep_21.setName("hsdpaThroughputStep");
    	hsdpaThrStep_21.setValue("12");
    	
    	P mod_21 = new P();
    	mod_21.setName("mod");
    	mod_21.setValue("FSM2");
    	
    	P sched_21  = new P();
    	sched_21.setName("sched");
    	sched_21.setValue("1");
    	
    	List<P> list_21 = new ArrayList<P>();
    	list_21.add(sched_21);
    	list_21.add(hsdpaThrStep_21);
    	list_21.add(mod_21);
    	
    	itemthrSep21.setP(list_21);
    	/////
    	////
    	Item itemthrSep22  = new Item();
    	
    	P hsdpaThrStep_22 = new P();
    	hsdpaThrStep_22.setName("hsdpaThroughputStep");
    	hsdpaThrStep_22.setValue("HSDPA throughput is not allocated");
    	
    	P mod_22 = new P();
    	mod_22.setName("mod");
    	mod_22.setValue("FSM2");
    	
    	P sched_22  = new P();
    	sched_22.setName("sched");
    	sched_22.setValue("2");
    	
    	List<P> list_22 = new ArrayList<P>();
    	list_22.add(sched_22);
    	list_22.add(hsdpaThrStep_22);
    	list_22.add(mod_22);
    	
    	itemthrSep22.setP(list_22);
    	////
    	List<Item> thrSteplist = new ArrayList<Item>();
    	thrSteplist.add(itemthrSep11);
    	thrSteplist.add(itemthrSep12);
    	thrSteplist.add(itemthrSep21);
    	thrSteplist.add(itemthrSep22);
    	
    	list_scw.setItems(thrSteplist);
    	
    	List<Lista> lista_scw = new ArrayList<Lista>();
    	lista_scw.add(list_scw);
    	scw.setDistName(btsscw);
    	scw.setClass_atribute("BTSSCW");
    	scw.setDistName(btsscw);
    	scw.setOperation("update");
    	scw.setVersion("WN8.0");
    	scw.setId("");
    	scw.setList(lista_scw);
    	
    	P hsdpa3 = new P();
    	hsdpa3.setName("numberOfHSUPASet1");
    	hsdpa3.setValue("12");
    	
    	P hsupa1 = new P();
    	hsupa1.setName("numberOfHSDPASet3");
    	hsupa1.setValue("6");
    	
        List<P> hspa_setovi = new ArrayList<P>();
        hspa_setovi.add(hsupa1);
        hspa_setovi.add(hsdpa3);
        scw.setP(hspa_setovi);
        managed_list.add(scw);
    	
    	
    	//////Setovan BTSSCW
    	
        
        ////LCELW pic setovanja
    	
        List<String> lcel_list = this.getLcelDN(btsscw); 
        
        Iterator<String> iter = lcel_list.iterator();
        
        while(iter.hasNext()){
        	String lcel_dn = iter.next();
        	
        	ManagedObject lcel = new ManagedObject();
        	lcel.setDistName(lcel_dn);
        	lcel.setClass_atribute("LCELW");
        
        	lcel.setOperation("update");
        	lcel.setVersion("WN8.0");
        	lcel.setId("");
        	if(lcel_dn.endsWith("2")||lcel_dn.endsWith("7")){
        		P hspamap = new P();
        		hspamap.setName("hspaMapping");
        		hspamap.setValue("None");
        		P picpool = new P();
        		picpool.setName("picPool");
        		picpool.setValue("2");
        		List<P> lcel_setovi = new ArrayList<P>();
        		lcel_setovi.add(hspamap);
        		lcel_setovi.add(picpool);
        		lcel.setP(lcel_setovi);
        	}
        	else{
        		P hspamap = new P();
        		hspamap.setName("hspaMapping");
        		hspamap.setValue("None");
        		P picpool = new P();
        		picpool.setName("picPool");
        		picpool.setValue("1");
        		List<P> lcel_setovi = new ArrayList<P>();
        		lcel_setovi.add(hspamap);
        		lcel_setovi.add(picpool);
        		lcel.setP(lcel_setovi);
        		
        	}
        	managed_list.add(lcel);//////dodati novi managed objekti u listu
        }

        ////////
        
        ////////LCELGW1
        
        String lcelgw1 = btsscw+"/LCELGW-1";
        String lcelgw2 = btsscw+"/LCELGW-2";
        
        
        ManagedObject gw1 = new ManagedObject();
        gw1.setDistName(lcelgw1);
    	gw1.setClass_atribute("LCELGW");
    	gw1.setOperation("update");
    	gw1.setVersion("WN8.0");
    	gw1.setId("");
    	
    	P access_1 = new P();
    	access_1.setName("accessBbCapacity");
    	access_1.setValue("Sector based BB pooling is used.");
    	
    	P smodid_1 = new P();
    	smodid_1.setName("sModId");
    	smodid_1.setValue("1");
    	
    	P sharehsdpa_1 = new P();
    	sharehsdpa_1.setName("shareOfHSDPAUser");
    	sharehsdpa_1.setValue("50");
    	
    	P sharehsupa_1 = new P();
    	sharehsupa_1.setName("shareOfHSUPALicences");
    	sharehsupa_1.setValue("50");
    	
    	List<P> gw_p_1 = new ArrayList<P>();
    	gw_p_1.add(access_1);
    	gw_p_1.add(smodid_1);
    	gw_p_1.add(sharehsupa_1);
    	gw_p_1.add(sharehsdpa_1);
    	gw1.setP(gw_p_1);
    	
    	
    	
    	Lista cell_list_1 = new Lista();
    	cell_list_1.setName("lCelIdList");
    	Iterator<String> iter_1 = lcel_list.iterator();
    	List<P> lcell_list_1 = new ArrayList<P>();
    	while(iter_1.hasNext()){
    		String lcel_dn = iter_1.next();
    				if(!(lcel_dn.endsWith("2")||lcel_dn.endsWith("7")||lcel_dn.endsWith("5")||lcel_dn.endsWith("9")||lcel_dn.endsWith("0"))){
    					P p = new P();
    					p.setValue(lcel_dn.split("/")[5].split("-")[1]);
    					lcell_list_1.add(p);
    				}
    	}
    	cell_list_1.setP(lcell_list_1);
    	
    	List<Lista> gw_lista_1 = new ArrayList<Lista>();
    	gw_lista_1.add(cell_list_1);
    	gw1.setList(gw_lista_1);
    	
    	
    	managed_list.add(gw1);
    	
    	
    	///////LCELGW2
    	
    	ManagedObject gw2 = new ManagedObject();
        gw2.setDistName(lcelgw2);
    	gw2.setClass_atribute("LCELGW");
    	gw2.setOperation("create");
    	gw2.setVersion("WN8.0");
    	gw2.setId("");
    	
    	P access_2 = new P();
    	access_2.setName("accessBbCapacity");
    	access_2.setValue("Sector based BB pooling is used.");
    	
    	P smodid_2 = new P();
    	smodid_2.setName("sModId");
    	smodid_2.setValue("2");
    	
    	P sharehsdpa_2 = new P();
    	sharehsdpa_2.setName("shareOfHSDPAUser");
    	sharehsdpa_2.setValue("50");
    	
    	P sharehsupa_2 = new P();
    	sharehsupa_2.setName("shareOfHSUPALicences");
    	sharehsupa_2.setValue("50");
    	
    	P cellgroupname_2 = new P();
    	cellgroupname_2.setName("cellGroupName");
    	cellgroupname_2.setValue("Group 2");
    	
    	
    	P dediCap = new P();
    	dediCap.setName("dedicatedBbCapacity");
    	dediCap.setValue("0");
    	
    	P hsupadediBd = new P();
    	hsupadediBd.setName("hsupaBbDecodCapacity");
    	hsupadediBd.setValue("0 Mbit/s");
    	
    	P hsupaMin = new P();
    	hsupaMin.setName("hsupaBbMinimumUsers");
    	hsupaMin.setValue("0");
    	
    	P minNum = new P();
    	minNum.setName("minNumHsfachUsers");
    	minNum.setValue("0");
    	
    	List<P> gw_p_2 = new ArrayList<P>();
    	gw_p_2.add(access_2);
    	gw_p_2.add(smodid_2);
    	gw_p_2.add(sharehsupa_2);
    	gw_p_2.add(sharehsdpa_2);
    	gw_p_2.add(hsupadediBd);
    	gw_p_2.add(dediCap);
    	gw_p_2.add(cellgroupname_2);
    	gw_p_2.add(minNum);
    	gw_p_2.add(hsupaMin);
    	gw2.setP(gw_p_2);
    	
    	
    	
    	Lista cell_list_2 = new Lista();
    	cell_list_2.setName("lCelIdList");
    	Iterator<String> iter_2 = lcel_list.iterator();
    	List<P> lcell_list_2 = new ArrayList<P>();
    	while(iter_2.hasNext()){
    		String lcel_dn = iter_2.next();
    				if(lcel_dn.endsWith("2")||lcel_dn.endsWith("7")||lcel_dn.endsWith("5")||lcel_dn.endsWith("9")||lcel_dn.endsWith("0")){
    					P p = new P();
    					p.setValue(lcel_dn.split("/")[5].split("-")[1]);
    					lcell_list_2.add(p);
    				}
    	}
    	cell_list_2.setP(lcell_list_2);
    	
    	List<Lista> gw_lista_2 = new ArrayList<Lista>();
    	gw_lista_2.add(cell_list_2);
    	gw2.setList(gw_lista_2);
    	
    	
    	managed_list.add(gw2);
    	//////////////////////////
    	
        
    	
    	
    	
    	
    	CmData cmData = new CmData();
    	
    	cmData.setId("PlanConfiguration( 5878 )");
    	cmData.setName("LGC 2");
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
    	cmData.setManagedObject(managed_list);
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
       // m.marshal(raml, System.out);
		return writer;
    	
		
    	
    	
    }
    
    private Integer check2ndGroupLocalCell(String wbts_dn){
    	
    	String query = "SELECT DISTINCT "+
    			"  COUNT(*) AS COUNT "+
    			"FROM "+
    			"  CTP_COMMON_OBJECTS BTSSCW1_O "+
    			"    JOIN "+
    			"  CTP_COMMON_OBJECTS LCELGW2_O "+
    			"    ON "+
    			"      LCELGW2_O.CO_PARENT_GID = BTSSCW1_O.CO_GID AND "+
    			"      LCELGW2_O.CO_OC_ID = 209 "+
    			"WHERE "+
    			"  BTSSCW1_O.CO_OC_ID = 204 AND "+
    			"  BTSSCW1_O.CO_DN = '"+wbts_dn+"/MRBTS-1/BTSSCW-1' AND "+
    			"  LCELGW2_O.CO_STATE = 0";
Integer group = 0;
		
		try {
  	      Connection con=null;
  	      Class.forName("oracle.jdbc.driver.OracleDriver");
  	      con=DriverManager.getConnection(
  	        "jdbc:oracle:thin:@oss1db.netact.vipsrb.srb:1521:OSS","rdr","rdr");
  	      Statement sm=con.createStatement();
  	      ResultSet rs = sm.executeQuery(query);
  	      
  	      while (rs.next()) {
  	    	group = rs.getInt(1);
  	      }
  	    	  
  	    sm.close();
	    con.close();
	   } 
	catch(Exception e){
		  e.printStackTrace();
		   }
		return group;
    }
    public StringWriter evaluate(String wbtsname) throws JAXBException{
    	StringWriter log = new StringWriter();
    	
    	
    	String wbts_dn = this.getDN(wbtsname);
    	Integer third_count = this.checkThirdCarrier(wbts_dn);
    	Integer cell_count = this.getCellCount(wbts_dn);
    	Integer lcel_count = this.getLcelCount(wbts_dn);
    	
    	Integer sector_count = this.checkSectorCount(wbts_dn);
    	Integer group_count  = this.check2ndGroupLocalCell(wbts_dn);
    	
    	if(group_count>=2){
    		log.append("LCG 2 vec definisan, proverite WBTS comm. file, nista od plana\n");
    	}
    	else {
    		
    		
    	if(third_count==0){
    		//log.append("Nisu definisane celije 3rd nosioca (10712) na OMS-u\n");
    		//System.out.println("Nisu definisane celije 3rd nosioca (10712) na OMS-u");
    		if(cell_count<=lcel_count){
    			//TODO radi dalje provere i algoritme
    			if(sector_count < 2){
        			//log.append("Broj sektora je manji od dva, nista od plana\n");
        			System.out.println("Broj sektora je manji od dva, nista od plana");
        		}
        		else{
        			switch (sector_count) {
        			case 2:
        				return Algorithm2(wbts_dn);
        				//break;
        			case 3 :
        				System.out.println("branch 1");
        				return Algorithm3(wbts_dn);
        				///break;
        			case 4 :
        				return Algorithm4(wbts_dn);
        				//break;
        				
        			}
        		}
    			
    		}
    		else{
    			System.out.println("Broj lokalnih celija jednak je broju celija na OMS-u, definisite lokalne celije na WBTS-u prvo! ");
    			log.append("Broj lokalnih celija jednak je broju celija na OMS-u, definisite lokalne celije na WBTS-u prvo!\n");
    		}
    	}
    	else{
    		//TODO radi dalje provere i algoritme
    		//log.append("Definisane su "+third_count+" celije 3rd nosioca (10712) na OMS-u\n");
    		log.append("WBTS ima "+sector_count+" sektora\n");
    		if(sector_count < 2){
    			log.append("Broj sektora je manji od dva, nista od plana\n");
    		}
    		else{
    			switch (sector_count) {
    			case 2:
    				return Algorithm2(wbts_dn);
    				//break;
    			case 3 :
    				return Algorithm3(wbts_dn);
    				
    				///break;
    			case 4 :
    				return Algorithm4(wbts_dn);
    				//break;
    				
    			}
    		}
    		
    	}
    }///Prvi else
    	
    return log;	
    }
}
