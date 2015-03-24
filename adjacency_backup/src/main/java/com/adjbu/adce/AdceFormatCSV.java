package com.adjbu.adce;




import java.io.FileWriter;
import java.util.List;

import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;


/**
 * Hello world!
 *
 */
public class AdceFormatCSV
{
	
	
	
	
    
private  CellProcessor[] getProcessors() {
        
       // final String emailRegex = "[a-z0-9\\._]+@[a-z0-9\\.]+"; // just an example, not very robust!
        //StrRegEx.registerMessage(emailRegex, "must be a valid email address");
        
        final CellProcessor[] processors = new CellProcessor[] { 
        		new NotNull(), //test comment
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

public void writeWithCsvBeanWriter(List<AdceBeanCSV> list) throws Exception {
    
    // create the customer beans
    
    List<AdceBeanCSV> customers = list;
    
    ICsvBeanWriter beanWriter = null;
    try {
            beanWriter = new CsvBeanWriter(new FileWriter("target/adce_backup.csv"),
                    CsvPreference.STANDARD_PREFERENCE);
            
            // the header elements are used to map the bean values to each column (names must match)
            final String[] header = new String[] { "Serving_cell_BCCH","Serving_cell_BCC",
            		"Serving_cell_NCC","Serving_cell_LAC","Serving_cell_ID",
            		"Neighbour_cell_ID","Neighbour_cell_BCCH","Neighbour_cell_BCC",
            		"Neighbour_cell_NCC","Neighbour_LAC"
            		};
            final String[] header_with_space = new String[] { "Serving cell BCCH","Serving cell BCC",
            		"Serving cell NCC","Serving cell LAC",
            		"Serving cell ID","Neighbour cell ID","Neighbour cell BCCH",
            		"Neighbour cell BCC","Neighbour cell NCC","Neighbour LAC"
            		};
            CellProcessor[] processors = getProcessors();
            
            // write the header
            beanWriter.writeHeader(header_with_space);
            
            // write the beans
            for( AdceBeanCSV customer : customers ) {
                    beanWriter.write(customer, header, processors);
            }
            
    }
    finally {
            if( beanWriter != null ) {
                    beanWriter.close();
            }
    }
}



}
