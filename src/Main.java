import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Main 
{	
	static int MAX_ITER = 100;
	static double LEARNING_RATE = 0.1;
	static int theta = 0;
	
	public static void main(String args[]) throws IOException, InterruptedException
	{ 
		
        ArrayList<Group> input_group_one = new ArrayList<>();
        
        ArrayList<Group> input_group_two = new ArrayList<>();

		
		/**************
		 * READ INPUT *
		 **************/
		BufferedReader reader = new BufferedReader(new FileReader("../PA-A-train.dat"));

		//do I even need this?
		int number_of_inputs = 0; 
		
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
		    number_of_inputs = splited_inputs.length;	//5	    
		    
		    /************************************
		     * GET THE LABEL (2nd LAYER OUTPUT) *
		     ************************************/
			String trueLabel = label_detector[label_detector.length - 1];
		    //System.out.println("this is the corresponding label: " + trueLabel);
			String[] splited_labels = trueLabel.split("\\s+");
			
			int number_of_output_neurons = splited_labels.length;			
			
			input_group_one.add(new Group( splited_inputs, splited_labels[0] ));
		    
			input_group_two.add(new Group( splited_inputs, splited_labels[0] )); 
			

		}
		reader.close();
		
		
//	    for (Group p : input_group_one)
//	        System.out.println( "check it out: " + p.toString() );
//		ArrayList<String> weights_one = new ArrayList<>();
		
	    
	    //PLUS ONE FOR BIAS
		double[] weights_one = new double[ number_of_inputs + 1 ];
		double[] weights_two = new double[ number_of_inputs + 1 ];
	    
		//MAKE SURE YOU HAVE ONE FOR BIAS
		for (int j = 0; j < weights_one.length ; j++) 
		{
			weights_one[j] = randDub(-0.5, 0.5);
		}
		for (int j = 0; j < weights_two.length ; j++) 
		{
			weights_two[j] = randDub(-0.5, 0.5);
		}
		
		
//		for (int j = 0; j < weights_one.length ; j++) 
//		{
//			System.out.println("weights_one[" + j + "]: " + weights_one[j]);
//			
//			System.out.println("weights_two[" + j + "]: " + weights_two[j]);
//		}

		System.out.println("NEURON ONE");
		System.out.println();
		Computation( input_group_one, weights_one );
		System.out.println();
		
		System.out.println("NEURON TWO");
		System.out.println();
		Computation( input_group_one, weights_two );
		
	}
	
	
	
	public static double randDub(double min, double max) throws InterruptedException 
	{

	    Random rand = new Random( System.currentTimeMillis() );

	    double randomValue = min + (max - min) * rand.nextDouble();
	    
	    //DELAY FOR SEED CHANGE
	    TimeUnit.SECONDS.sleep(1);
	    
	    return randomValue;
	}
	
	
	
	static int calculateOutput(int theta, double weights[], ArrayList<Group> input_group)
	{
		double sum = 0;
		
	    for (Group pattern : input_group)
	    {
		    for (int i = 0; i < pattern.value.length; i++) 
		    {
		    	//ORIGINALLY STORED AS STRING MUST CHANGE TO INT
		        sum += Integer.parseInt( pattern.value[i] ) * weights[i];
		    }
		    //BIAS
		    sum += weights[ pattern.value.length ]; 
	    }
		return (sum >= theta) ? 1 : 0;
	    
	}
	
	
	public static void Computation( ArrayList<Group> input_group, double[] weights )
	{
		double localError, globalError;
		int i, p, iteration, output;
		iteration = 0;
		do 
		{
			iteration++;
			globalError = 0;
			
			//loop through all instances (complete one epoch)
			for (p = 0; p < input_group.size(); p++) // !!!!! is input_group_one.size() right?
			{	
				// calculate predicted class
				output = calculateOutput(theta, weights, input_group);
				
				// difference between predicted and actual class values
				localError = Integer.parseInt( input_group.get(iteration).value[0] ) - output;
				
				//update weights and bias
				for (int weight_index = 0; weight_index < input_group.get(iteration).value.length ; weight_index++) 
				{
					weights[weight_index] +=  LEARNING_RATE * localError * Integer.parseInt( input_group.get(iteration).value[weight_index] );
				}
				//BIAS, (-1 because it starts from zero)
				weights[weights.length - 1] +=  LEARNING_RATE * localError;
				
				//summation of squared error (error value for all instances)
				globalError += (localError*localError);
			}

			/* Root Mean Squared Error */
			System.out.println("Iteration "+iteration+", : RMSE = "+Math.sqrt(globalError/input_group.size()));
					
		} 
		while (globalError != 0 && iteration<=MAX_ITER);
		
		System.out.println();
		System.out.println("Decision boundary equation:");
		System.out.println();
		
		//PRINT EQUATION
		for (int weight_index = 0; weight_index < input_group.get(iteration).value.length ; weight_index++) 
		{
			System.out.print(weights[weight_index] + " * " + Integer.parseInt( input_group.get(iteration).value[weight_index] ) + " + " );
		}
		//BIAS 
		System.out.print( weights[weights.length - 1] + " = 0" );
		System.out.println();
		System.out.println();
		
			
	}
	
	

}


