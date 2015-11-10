import java.util.ArrayList;
import java.util.Arrays;

class Group {

   public String key;
   public String[] value;

   public String getKey() {
      return key;
   }

   public String[] getValue() {
      return value;
   }

   Group(String[] splited_inputs, String k) 
   {
      this.key = k;
      this.value = splited_inputs;
   }

   @Override
   public String toString() 
   {
	   return this.key + " " + Arrays.toString(this.value);
   }
//   public String toString() 
//   {
//	    String result = this.key + "::";
//
//	    for (int i = 0; i < this.value.length; i++) 
//	    {
//	        result += " " + this.value[i];
//	    }
//
//	    return result;
//	}

}

