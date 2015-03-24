package com.adjbu.adce;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;



public class MainAdce {
	
	

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		  ArrayList<BtsBean> bts = new ArrayList<BtsBean>();
		  ArrayList<AdceBeanCSV> adce_out_beans = new ArrayList<AdceBeanCSV>();
		  QueriesAdce adce_utilities = new QueriesAdce();
		  String bts_query = adce_utilities.BtsFromBcfCode("KG3231");
		  //System.out.println(OracleDriver.getDriverVersion());
		  Connection connection;
	      Class.forName("oracle.jdbc.driver.OracleDriver");
	      connection=DriverManager.getConnection("jdbc:oracle:thin:@vipvm4.vipnetact.vipsrb.srb:1521:OSS","rdr","rdr");
	      Statement sm=connection.createStatement();
	      ResultSet rs = sm.executeQuery(bts_query);
          while (rs.next()) {

        	  BtsBean source_bts = new BtsBean();
        	  source_bts.setServing_cell_BCC(rs.getString(6));
        	  ///source_bts.setServing_cell_BCCH(rs.getString(2));
        	  source_bts.setServing_cell_ID(rs.getString(8));
        	  source_bts.setServing_cell_LAC(rs.getString(5));
        	  source_bts.setServing_cell_NCC(rs.getString(7));
        	  source_bts.setBts_DN(rs.getString(2));
        	  
        	  Statement sm_trx=connection.createStatement();
        	  String query_trx = adce_utilities.BcchTrxFromBtsDn(source_bts.getBts_DN());
    	      ResultSet rs_trx = sm_trx.executeQuery(query_trx);
    	      rs_trx.next();
    	      source_bts.setServing_cell_BCCH(rs_trx.getString(4));
        	  sm_trx.close();
        	  
        	  bts.add(source_bts);
        	  //System.out.println(source_bts.getServing_cell_BCC());
        	 
  	       }
          sm.close();
          
          Iterator<BtsBean> bts_iter =  bts.iterator();
          while(bts_iter.hasNext()){
          BtsBean source_bts = bts_iter.next();
          String bts_dn_query = adce_utilities.OutgoingFromBtsDn(source_bts.getBts_DN());
          System.out.println("--------------------------------------------------------------");
          sm=connection.createStatement();
    	  ResultSet rs_adce = sm.executeQuery(bts_dn_query);
          while (rs_adce.next()) {
        	  AdceBeanCSV adce_bean = new AdceBeanCSV();
        	  adce_bean.setNeighbour_cell_BCC(rs_adce.getString(4));
        	  adce_bean.setNeighbour_cell_BCCH(rs_adce.getString(3));
        	  adce_bean.setNeighbour_cell_ID(rs_adce.getString(6));
        	  adce_bean.setNeighbour_cell_NCC(rs_adce.getString(5));
        	  adce_bean.setNeighbour_LAC(rs_adce.getString(2));
        	  
        	  adce_bean.setServing_cell_BCC(source_bts.getServing_cell_BCC());
        	  adce_bean.setServing_cell_NCC(source_bts.getServing_cell_NCC());
        	  adce_bean.setServing_cell_LAC(source_bts.getServing_cell_LAC());
        	  adce_bean.setServing_cell_BCCH(source_bts.getServing_cell_BCCH());
        	  adce_bean.setServing_cell_ID(source_bts.getServing_cell_ID());
        	  
        	  adce_out_beans.add(adce_bean);//Dodati svi outgoing susedi
        	  
  	    	  
  	       }
          sm.close();
          }
          
          
          
          
          
		  
	      connection.close();
	      
	      
	      AdceFormatCSV write_adce_to_csv = new AdceFormatCSV();
	      write_adce_to_csv.writeWithCsvBeanWriter(adce_out_beans);
		

	}
	
	
	
	

}
