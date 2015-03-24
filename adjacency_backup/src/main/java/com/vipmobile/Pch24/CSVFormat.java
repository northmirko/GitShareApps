package com.vipmobile.Pch24;


import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.supercsv.cellprocessor.Optional;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

/**
 * Hello world!
 *
 */
public class CSVFormat
{
    
private  CellProcessor[] getProcessors() {
        
       // final String emailRegex = "[a-z0-9\\._]+@[a-z0-9\\.]+"; // just an example, not very robust!
        //StrRegEx.registerMessage(emailRegex, "must be a valid email address");
        
        final CellProcessor[] processors = new CellProcessor[] { 
              //  new UniqueHashCode(), // customerNo (must be unique)
                new NotNull(), 
                new NotNull(), 
                new NotNull(), 
                new NotNull(), 
                new NotNull(), 
                new NotNull(),
                new NotNull(), 
                new NotNull(), 
                new NotNull(), 
                new NotNull(), 
                new NotNull(), 
                new NotNull(),
                new NotNull()
               
        };
        
        return processors;
}

public List<Pch24Bean> readWithCsvBeanReader(FileReader reader) throws IOException {
	//Calendar cal = Calendar.getInstance();
	//cal.add(Calendar.DATE, -1);

	//Date date = cal.getTime();  
	// SimpleDateFormat format = new SimpleDateFormat("yyyy_M_d");
	// Date date = cal.getTime();
	// String formated_date = format.format(date);
	// System.out.println(formated_date);
	
	List<Pch24Bean> beans = new ArrayList<Pch24Bean>();
    
    ICsvBeanReader beanReader = null;
    try {
    	
    	
	   
            beanReader = new CsvBeanReader(reader, CsvPreference.STANDARD_PREFERENCE);
            
            // the header elements are used to map the values to the bean (names must match)
            final String[] header = beanReader.getHeader(true);
            final CellProcessor[] processors = getProcessors();
            
            Pch24Bean customer;
            while( (customer = beanReader.read(Pch24Bean.class, header, processors)) != null ) {
                   // System.out.println(String.format("lineNo=%s, rowNo=%s, customer=%s", beanReader.getLineNumber(),
                           // beanReader.getRowNumber(), customer));
            //	System.out.println(customer.getRncId()+" "+customer.getLcrId());
            	
            	beans.add(customer);
            }
            
    }
    finally {
            if( beanReader != null ) {
                    beanReader.close();
            }
    }
    return beans;
}




}
