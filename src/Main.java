import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

public class Main 
{	
		
	
	public static void main(String args[]) throws IOException
	{ 
		
        ArrayList<Group> input_group_one = new ArrayList<>();
        
        ArrayList<Group> input_group_two = new ArrayList<>();

		
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
		    List<String> input_list = Arrays.asList(splited_inputs);
		    
		    splited_inputs = Arrays.stream(splited_inputs) //Remove null values
                    .filter(s -> (s != null && s.length() > 0))
                    .toArray(String[]::new); 
		    
		    //for this training datum, how many features does it have
		    int number_of_inputs = splited_inputs.length;	//5	    
		    
		    /************************************
		     * GET THE LABEL (2nd LAYER OUTPUT) *
		     ************************************/
			String trueLabel = label_detector[label_detector.length - 1];
		    //System.out.println("this is the corresponding label: " + trueLabel);
			String[] splited_labels = trueLabel.split("\\s+");
			
			int number_of_labels = splited_labels.length;			
			
			
			
			
			input_group_one.add(new Group( splited_inputs, splited_labels[0] ));
		    
			input_group_two.add(new Group( splited_inputs, splited_labels[0] )); 
			

		}
		reader.close();
		
	    for (Group p : input_group_one)
	        System.out.println( "check it out: " + p.toString() );
	    
	    
	    
	    //now that we have inputs --> we must initialize the weight vectors
	    
	    //then we do our calculation // our rule --> and then we are finished. 
		
		
	}
	


	
	

	
	public static double RandomNumberGenerator(long seed)
	{
	    Random r = new Random(seed);
	    return r.nextDouble() >= 0.5? 0.5 : -0.5;
	    
		//PRINTING AND USAGE, SHOULD GO ABOVE
		//System.out.println("Object after seed 12222222: " + RandomNumberGenerator(12222222) );
	}
	

}


