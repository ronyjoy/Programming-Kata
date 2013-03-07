package com.kata.bankocr;

public class AccountNumber {
	
	private String line1;
	private String line2;
	private String line3;
	
	private enum AccountNumberDefinition {
		ZERO("1101111",0)
		
		
		private AccountNumber
	}
	private static final String ZERO = "1101111";
	private static final String NINE = "1111011";
	private static final String EIGHT = "1111111";
	private static final String SEVEN = "1001001";
	
	public String parseToString() {
		return AccountNumberParser.ConvertAccountNumberToString(this);
		
	}
	public String getLine1() {
		return line1;
	}

	public String getLine2() {
		return line2;
	}

	public String getLine3() {
		return line3;
	}

	
	public AccountNumber() {
		
	}
	
	private AccountNumber (Builder builder) {
		this.line1 = builder.line1;
		this.line2 = builder.line2;
		this.line3 = builder.line3;
	}
	
	public static class Builder {
		private String line1;
		private String line2;
		private String line3;
		
		public Builder() {
		}
		
		public Builder line1(String line1) {
			this.line1 = line1;
			return this;
		}
		public Builder line2(String line2) {
			this.line2 = line2;
			return this;
		}
		public Builder line3(String line3) {
			this.line3 = line3;
			return this;
		}
		
		public AccountNumber build() {
			return new AccountNumber(this);
		}
		
		
		
		
	
	}

}
