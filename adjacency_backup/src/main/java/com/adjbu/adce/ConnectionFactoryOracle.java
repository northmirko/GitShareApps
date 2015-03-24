package com.adjbu.adce;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import oracle.jdbc.OracleDriver;

public class ConnectionFactoryOracle {
	
	

	
	public static ResultSet establishAndQeury(String query) throws SQLException, ClassNotFoundException{
		  System.out.println(OracleDriver.getDriverVersion());
		  Connection connection;
	      Class.forName("oracle.jdbc.driver.OracleDriver");
	      connection=DriverManager.getConnection("jdbc:oracle:thin:@vipvm4.vipnetact.vipsrb.srb:1521:OSS","rdr","rdr");
	      Statement sm=connection.createStatement();
	      ResultSet rs = sm.executeQuery(query);
	      sm.close();
	      connection.close();
		return rs;
	}

}
