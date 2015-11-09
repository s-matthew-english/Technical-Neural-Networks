import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class Main 
{	
		
	
	public static void main(String args[]) throws IOException
	{ 
		/**************
		 * READ INPUT *
		 **************/
		BufferedReader reader = new BufferedReader(new FileReader("../PA-A-train.dat"));

		String line;//new variable
		while ((line = reader.readLine()) != null) //read the line and compare
		{
			/*********************************************************************
			 * GET THE LINE, SPLIT ON THE TAB FOR LABEL VS. INPUT IDENTIFICATION *
			 *********************************************************************/
		    String[] label_detector = line.split("\t"); //split
		    
		    /*****************************
		     * GET THE INDIVIDUAL INPUTS *
		     *****************************/
		    String inputs = label_detector[label_detector.length - 2];
		    String[] splited_inputs = inputs.split("\\s+");
		    
		    splited_inputs = Arrays.stream(splited_inputs) //Remove null values
                    .filter(s -> (s != null && s.length() > 0))
                    .toArray(String[]::new); 
		    
		    for (String individual_input : splited_inputs)
		        System.out.print(individual_input + ", ");
		        System.out.println();
		    
		    /************************************
		     * GET THE LABEL (2nd LAYER OUTPUT) *
		     ************************************/
			String trueLabel = label_detector[label_detector.length - 1];
		    //System.out.println("this is the corresponding label: " + trueLabel);
			String[] splited_labels = trueLabel.split("\\s+");
			
		    for (String individual_labels : splited_labels)
		    	System.out.print(individual_labels + ", ");
	        	System.out.println();
		}
		reader.close();
		

		


		
		
		
	}
	
	public static int RandomNumberGenerator(long seed)
	{
	    Random r = new Random(seed);
	    return r.nextDouble() >= 0.5? 1 : 0;
	    
		//PRINTING AND USAGE, SHOULD GO ABOVE
		//System.out.println("Object after seed 12222222: " + RandomNumberGenerator(12222222) );
	}

	
	
	
	

}
