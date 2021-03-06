package com.kata.bankocr;

public class AccountNumberParser {
	/**
	 * Each line in the number is represented by either 0 or 1 if the line 
	 * is present it will be represented as 1 or 0
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
		
	private enum DigitalDefinition {
		_1101111("0"),
		_0001001("1"),
		_1011110("2"),
		_1011011("3"),
		_0111001("4"),
		_1110011("5"),
		_1110111("6"),
		_1001001("7"),
		_1111111("8"),
		_1111011("9");
		
		private final String characterRepresentation;
		DigitalDefinition(String characterRepresentation ) {
			this.characterRepresentation = characterRepresentation;
					
		}
	}
	
	public final static String ConvertAccountNumberToString(AccountNumber accountNumber) {
		char[] aLine =  accountNumber.getLine1().toCharArray();
		char[] bcdLine =  accountNumber.getLine2().toCharArray();
		char[] efgLine =  accountNumber.getLine3().toCharArray();
		String charRepresentation = "";
		for (int i=0;i<bcdLine.length;i+=3) {
			char a = mark(aLine[i+1]);
			char b = mark(bcdLine[i]);
			char c = mark(bcdLine[i+1]);
			char d = mark(bcdLine[i+2]);
			char e = mark(efgLine[i]);
			char f = mark(efgLine[i+1]);
			char g = mark(efgLine[i+2]);
			charRepresentation+= DigitalDefinition.valueOf(new StringBuilder()
									.append('_')
									.append(a)
									.append(b)
									.append(c)
									.append(d)
									.append(e)
									.append(f)
									.append(g).toString()).characterRepresentation;
		}
		
		
		return charRepresentation;
		
	}

	private static char mark(char c) {
		char value = '0';
		if(c == '_' || c == '|') {
			value = '1';
		}
		return value;
		
		
	}
	

}
