package com.kata.bankocr;

public class AccountNumberParser {
	/**
	 * Each line in the number is represented by a letter
	 *   a
		 _	    
	b	|_|  d
		 c
	e	|_|	 g
		
	     f
		 _	    
		|_|  
		|_|	 
		 	
	 * 
	 * a b c d e f g so the digit eight will be represented as 
	 *    abcdefg
	 *    1111111
	 *and Nine will represented as
	 *    abcdefg
	 *    1111011 
	 */
										
	private static final String ZERO = "1101111";
	private static final String NINE = "1111011";
	private static final String EIGHT = "1111111";
	private static final String SEVEN = "1001001";
	
	private enum AccountNumberDefinition {
		ZERO("1101111","0"),
		ZERO("1101111","0"),
		ZERO("1101111","0"),
		;
		
		private final String representation;
		private final String accNumber;
		AccountNumberDefinition(String representation, String accNumber ) {
			this.representation = representation;
			this.accNumber = accNumber;
		}
	}
	
	public final static String ConvertAccountNumberToString(AccountNumber accountNumber) {
		char[] a =  accountNumber.getLine1().toCharArray();
		char[] bcd =  accountNumber.getLine2().toCharArray();
		char[] efg =  accountNumber.getLine3().toCharArray();
		
		for (int i=0;i<bcd.length;i++) {
			a[i+1];
		}
		return null;
		
	}
	

}
