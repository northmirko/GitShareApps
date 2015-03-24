package com.xml.model;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;








public class MainApp {

	public static void main(String[] args) throws JAXBException, IOException {
		// TODO Auto-generated method stub
		
		List<ManagedObject> remove_list = new ArrayList<ManagedObject>();
		
		
		FileReader reader_cell  = new FileReader(new File("C:\\Users\\m.kovacevic\\Desktop\\lcel.csv"));
        CSVFormat csv = new CSVFormat();
        List<CellBean> cell_beans = csv.readCsvCellBeanReader(reader_cell);
        
        int lcel_counter = cell_beans.size();
        System.out.println(cell_beans.size());
        int lcel_temp = 1;
		
		FileReader reader  = new FileReader(new File("C:\\Users\\m.kovacevic\\Desktop\\wbts_parametri_primer.csv"));
      //  CSVFormat csv = new CSVFormat();
        List<WbtsIpBean> wbts_ip_beans = csv.readWithCsvBeanReader(reader);
        WbtsIpBean wbts_ip_bean = wbts_ip_beans.get(0);
        System.out.println(wbts_ip_bean.getWbts_gateway());
        System.out.println(wbts_ip_bean.getWbts_id());
		
		JAXBContext context = JAXBContext.newInstance(Raml.class);
        Unmarshaller unmarchaller = context.createUnmarshaller();
        Raml raml  = (Raml) unmarchaller.unmarshal(new File("C:\\Users\\m.kovacevic\\Desktop\\template.xml"));
        List<ManagedObject> managed_list = raml.getCmData().getManagedObject();
        Iterator<ManagedObject> iter  = managed_list.iterator();
        while(iter.hasNext()){
        	ManagedObject man_obj = iter.next();
        	/////Izmene
        	String[] class_att = man_obj.getDistName().split("/");
        	class_att[0]="WBTS-"+wbts_ip_bean.getWbts_id();
        	String class_att_concat = "";
        	for(int i = 0;i < class_att.length;i++){
        		class_att_concat = class_att_concat +"/"+ class_att[i];
        	}
        	String class_att_final = class_att_concat.substring(1, class_att_concat.length());
        	man_obj.setDistName(class_att_final);
        	//////
        	
        	System.out.println(man_obj.getClass_atribute()+"="+man_obj.getDistName());
        	////////////
        	if(man_obj.getClass_atribute().contains("IPNO")){
        		
        		List<P> p_list = man_obj.getP();
            	if(p_list!=null){
            	Iterator<P> p_iter = p_list.iterator();
            	while(p_iter.hasNext()){
            		P p_obj = p_iter.next();
            		if(p_obj.getName().contains("uPlaneIpAddress")){
            			p_obj.setValue(wbts_ip_bean.getWbts_transport_ip().split("/")[0]);
            		}
            		if(p_obj.getName().contains("sPlaneIpAddress")){
            			p_obj.setValue(wbts_ip_bean.getWbts_transport_ip().split("/")[0]);
            		}
            		if(p_obj.getName().contains("mPlaneIpAddress")){
            			p_obj.setValue(wbts_ip_bean.getOm_trs_ip().split("/")[0]);
            		}
            		if(p_obj.getName().contains("cPlaneIpAddress")){
            			p_obj.setValue(wbts_ip_bean.getWbts_transport_ip().split("/")[0]);
            		}
            		if(p_obj.getName().contains("ftmIpAddr")){
            			p_obj.setValue(wbts_ip_bean.getOm_trs_ip().split("/")[0]);
            		}
            		if(p_obj.getName().contains("btsIpAddr")){
            			p_obj.setValue(wbts_ip_bean.getOm_bts_ip().split("/")[0]);
            		}
            		
            		if(p_obj.getName().contains("btsId")){
            			
            			p_obj.setValue(wbts_ip_bean.getWbts_id());
            		}
	
            	}
            	}
            	
            	List<Lista> lista_list = man_obj.getList();
            	if(lista_list != null){
            		Lista lista_obj = lista_list.get(0);
            		if(lista_obj.getName().contains("twampFlag")){
            		//System.out.println("\t\tlist: "+lista_obj.getName());
            		List<Item> item_list = lista_obj.getItem();
            		if(item_list != null){
            		Iterator<Item> iter_item = item_list.iterator();
            		String ip=wbts_ip_bean.getWbts_transport_ip().split("/")[0];
            		while(iter_item.hasNext()){
            			Item item = iter_item.next();
                    	List<P> p_list_item = item.getP();
                    	Iterator<P> p_iter_item = p_list_item.iterator();
                    	
                    	while(p_iter_item.hasNext()){
                    		P p_obj_item = p_iter_item.next();
                    		if(p_obj_item.getName().contains("twampIpAddress")){
                    			p_obj_item.setValue(ip);
                    			ip = wbts_ip_bean.getOm_trs_ip().split("/")[0];
                    			
                    		}
                    		///System.out.println("\t\t\t\t"+p_obj_item.getName()+"="+p_obj_item.getValue());
                    	}
                    	
            		}
            	}
            	}
            		List<P> p_lista_list = lista_obj.getP();
            		if(p_lista_list != null){
            			Iterator<P> p_obj_iter = p_lista_list.iterator();
            			while(p_obj_iter.hasNext()){
            				P p_lista_obj = p_obj_iter.next();
            				System.out.println("\t\t\t\t"+p_lista_obj.getValue());
            			}
            		}
            		
            	}
            	
        		
        	}
        	
        	
        	if(man_obj.getClass_atribute().contains("LCELGW")){
        		List<Lista> lista_list = man_obj.getList();
            	if(lista_list != null){
            		Lista lista_obj = lista_list.get(0);
            		if(lista_obj.getName().contains("lCelIdList")){
            			List<P> p_lista_list = lista_obj.getP();
                		if(p_lista_list != null){
                			
                			p_lista_list.clear();
                			Iterator<CellBean> cell_iter = cell_beans.iterator();
                			while(cell_iter.hasNext()){
                				CellBean lcel = cell_iter.next();
                				P p_lcel = new P();
                				p_lcel.setValue(lcel.getLcel());
                				p_lista_list.add(p_lcel);
                				
                			}
                			
                			
                		}
            			
            		}
            		
            	}
        		
        		
        		
        	}
        	
        	
        	
        	if(man_obj.getClass_atribute().contains("LCELW")){
        		
        		if(lcel_temp<=lcel_counter){
        		//System.out.println("MMMMMMMMMMMMMMMMMMMMM");
        		String lcelw_name = "";
        		String[] lcel_names = man_obj.getDistName().split("/");
        		lcel_names[lcel_names.length-1] = "LCELW-"+cell_beans.get(lcel_temp-1).getLcel();
        		
        			for(int j=0;j<lcel_names.length;j++){
        				lcelw_name = lcelw_name +"/"+ lcel_names[j];
        				
        			}
        			String lcelw_name_final = lcelw_name.substring(1, lcelw_name.length());
        			///System.out.println(lcelw_name_final);
        			man_obj.setDistName(lcelw_name_final);
        			System.out.println(man_obj.getClass_atribute()+"="+man_obj.getDistName());
        			
        			
        		}
        		else{
        			remove_list.add(man_obj);
        			
        		}
        		lcel_temp++;
        		
        	
        		
        	}
        	//else
        	//{
        	//	if(man_obj.getClass_atribute().contains("LCELW")){
        	//		remove_list.add(man_obj);
        	//	}
        	//}
        	
        	
        	
        	
        	
        	
        	
        	
        	if(man_obj.getClass_atribute().contains("IPRT")){
        		List<Lista> lista_list = man_obj.getList();
            	if(lista_list != null){
            		Lista lista_obj = lista_list.get(0);
            		if(lista_obj.getName().contains("staticRoutes")){
            		//System.out.println("\t\tlist: "+lista_obj.getName());
            		List<Item> item_list = lista_obj.getItem();
            		if(item_list != null){
            		Iterator<Item> iter_item = item_list.iterator();
            		
            		while(iter_item.hasNext()){
            			Item item = iter_item.next();
                    	List<P> p_list_item = item.getP();
                    	Iterator<P> p_iter_item = p_list_item.iterator();
                    	
                    	while(p_iter_item.hasNext()){
                    		P p_obj_item = p_iter_item.next();
                    		if(p_obj_item.getName().contains("gateway")){
                    			p_obj_item.setValue(wbts_ip_bean.getWbts_gateway().split("/")[0]);
                    		}
                    		///System.out.println("\t\t\t\t"+p_obj_item.getName()+"="+p_obj_item.getValue());
                    	}
                    	
            		}
            	}
            	}
            		List<P> p_lista_list = lista_obj.getP();
            		if(p_lista_list != null){
            			Iterator<P> p_obj_iter = p_lista_list.iterator();
            			while(p_obj_iter.hasNext()){
            				P p_lista_obj = p_obj_iter.next();
            				System.out.println("\t\t\t\t"+p_lista_obj.getValue());
            			}
            		}
            		
            	}
        		
        	}
        	if(man_obj.getClass_atribute().contains("TMPAR")){
        		List<P> p_list = man_obj.getP();
            	if(p_list!=null){
            	Iterator<P> p_iter = p_list.iterator();
            	while(p_iter.hasNext()){
            		P p_obj = p_iter.next();
            		if(p_obj.getName().contains("minSCTPPort")){
            			p_obj.setValue(wbts_ip_bean.getSctp());
            		}
            		
	
            	}
            	}
        		
        	}
        	if(man_obj.getClass_atribute().contains("BTSSCW")){
        		List<P> p_list = man_obj.getP();
            	if(p_list!=null){
            	Iterator<P> p_iter = p_list.iterator();
            	while(p_iter.hasNext()){
            		P p_obj = p_iter.next();
            		
            		if(p_obj.getName().contains("btsName")){
            			p_obj.setValue(wbts_ip_bean.getWbts_code());
            		}
            		if(p_obj.getName().contains("btsId")){
            			p_obj.setValue(wbts_ip_bean.getWbts_id());
            		}
            		
	
            	}
            	}
        		
        	}
        	if(man_obj.getClass_atribute().contains("IVIF")){
        		List<P> p_list = man_obj.getP();
            	if(p_list!=null){
            	Iterator<P> p_iter = p_list.iterator();
            	while(p_iter.hasNext()){
            		P p_obj = p_iter.next();
            		if(p_obj.getName().contains("vlanId")){
            			p_obj.setValue(wbts_ip_bean.getVlan_id());
            		}
            		if(p_obj.getName().contains("localIpAddr")){
            			p_obj.setValue(wbts_ip_bean.getWbts_transport_ip().split("/")[0]);
            		}
            		
	
            	}
            	}
        		
        	}
        	if(man_obj.getClass_atribute().contains("SMOD")){
        		List<P> p_list = man_obj.getP();
            	if(p_list!=null){
            	Iterator<P> p_iter = p_list.iterator();
            	while(p_iter.hasNext()){
            		P p_obj = p_iter.next();
            		if(p_obj.getName().contains("moduleLocation")){
            			p_obj.setValue("KGMR4");
            		}
            		
	
            	}
            	}
        		
        	}
        	if(man_obj.getClass_atribute().contains("FTM")){
        		List<P> p_list = man_obj.getP();
            	if(p_list!=null){
            	Iterator<P> p_iter = p_list.iterator();
            	while(p_iter.hasNext()){
            		P p_obj = p_iter.next();
            		if(p_obj.getName().contains("systemTitle")){
            			p_obj.setValue(wbts_ip_bean.getWbts_code());
            		}
            		if(p_obj.getName().contains("userLabel")){
            			p_obj.setValue(wbts_ip_bean.getWbts_code());
            		}
            		if(p_obj.getName().contains("locationName")){
            			p_obj.setValue("KGMR4");
            		}
            		
	
            	}
            	}
        		
        	}
        	if(man_obj.getClass_atribute().contains("TWAMP")){
        		List<P> p_list = man_obj.getP();
            	if(p_list!=null){
            	Iterator<P> p_iter = p_list.iterator();
            	while(p_iter.hasNext()){
            		P p_obj = p_iter.next();
            		
            		if(p_obj.getName().contains("destIpAddress")){
            			p_obj.setValue(wbts_ip_bean.getTwamp_rnc_ip());
            		}
            		if(p_obj.getName().contains("sourceIpAddress")){
            			p_obj.setValue(wbts_ip_bean.getWbts_transport_ip().split("/")[0]);
            		}
            		
	
            	}
            	}
        		
        	}
        	
        	
        	List<P> p_list = man_obj.getP();
        	if(p_list!=null){
        	Iterator<P> p_iter = p_list.iterator();
        	while(p_iter.hasNext()){
        		P p_obj = p_iter.next();
        		System.out.println("\t\t"+p_obj.getName()+"="+p_obj.getValue());
        		
        	}
        	}
        	List<Lista> lista_list = man_obj.getList();
        	if(lista_list != null){
        		Lista lista_obj = lista_list.get(0);
        		System.out.println("\t\tlist: "+lista_obj.getName());
        		List<Item> item_list = lista_obj.getItem();
        		if(item_list != null){
        		Iterator<Item> iter_item = item_list.iterator();
        		while(iter_item.hasNext()){
        			Item item = iter_item.next();
                	List<P> p_list_item = item.getP();
                	Iterator<P> p_iter_item = p_list_item.iterator();
                	while(p_iter_item.hasNext()){
                		P p_obj_item = p_iter_item.next();
                		System.out.println("\t\t\t\t"+p_obj_item.getName()+"="+p_obj_item.getValue());
                	}
                	
        		}
        	}
        		List<P> p_lista_list = lista_obj.getP();
        		if(p_lista_list != null){
        			Iterator<P> p_obj_iter = p_lista_list.iterator();
        			while(p_obj_iter.hasNext()){
        				P p_lista_obj = p_obj_iter.next();
        				System.out.println("\t\t\t\t"+p_lista_obj.getValue());
        			}
        		}
        		
        	}
        	
        	
        }
        managed_list.removeAll(remove_list);
        ///////Kraj
        
        
    	raml.setVersion("2.1");
    	raml.setXmlns("raml21.xsd");
        JAXBContext context_final = JAXBContext.newInstance(Raml.class);
        
        Marshaller m = context_final.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        
        m.marshal(raml, System.out);
      m.marshal(raml,new File("C:\\Users\\m.kovacevic\\Desktop\\Comm_file_"+wbts_ip_bean.getWbts_code()+".xml") );
        
	}

}
