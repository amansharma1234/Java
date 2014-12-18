public class StringToLongUtil {
	
	/**
	 * Converts a given string to long
	 * 
	 * Limitation : gives incorrect result for Long.MIN_VALUE as Long.MIN_VALUE
	 * (without -ve sign) can't expressed as positive long number
	 * 
	 * @param s
	 * @return
	 */
	public static long stringToLong(String s) {
		int length = s.length();
		if(s==null || length==0) {
			throw new NumberFormatException("For given string: \"\" "); 
		}
		boolean isNegative = false;

		// check if input has minus sign
		if(s.charAt(0) == '-') {
			s = s.substring(1,length);
			isNegative = true;
			length--;
		}

		String numericValRegex = "[0-9]*"; 

		// check is input is all numeric
		if(s.matches(numericValRegex)) {
			long num = 0;

			// multiply each digit with place value
			for(int i=length - 1 ; i>=0 ; i--) {
				long numericVal = s.charAt(i) - '0';
				long addNumber = (long) (Math.pow(10, length - i - 1) * numericVal);
				num += addNumber; 
				
				// if number overflow throw exception
				if(num < 0) {
					throw new NumberFormatException("For given string: " + s);
				}
			
			}

			// negate number of minus was present
			if(isNegative) {
				return -num;
			} else {
				return num;
			}
		} else {
			throw new NumberFormatException("For given string: " + s);
		}

	}
}
