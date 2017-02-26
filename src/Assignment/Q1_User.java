package Assignment;

import java.io.*;
import java.sql.Timestamp;
import com.csvreader.CsvWriter;

public class Q1_User {

	public static void main(String[] args)throws IOException {
		// TODO Auto-generated method stub
		
		User.input();
        couple_generator c_g = new couple_generator();
        c_g.create();
        
         Timestamp ts = new Timestamp(System.currentTimeMillis());
         
         String outputFile = "C:\\Users\\Anurag\\Desktop\\Q1_TimeStamp.csv";
 		
 		boolean alreadyExists = new File(outputFile).exists();
 			
 		try {
 	
 			CsvWriter csvOutput = new CsvWriter(new FileWriter(outputFile, true), ',');
 			
 			
 			if (!alreadyExists)
 			{
 				csvOutput.write("TimeStamp");
 				csvOutput.write("Couple_id");
 				csvOutput.endRecord();
 			}
 			
 			
 			
 			
 			
 			for (int it = 0; it < User.cArray.size(); it++) {
 	           csvOutput.write("Time :-" + ts.toString());
 	           csvOutput.write(Integer.toString(User.cArray.get(it).couple_id));
 	           csvOutput.endRecord();
 	        }
 			
 			csvOutput.close();
 		} catch (IOException e) {
 			e.printStackTrace();
 		}		

	}

}
