package com.kata.bankocr;

public class AccountNumber {
	
	private String line1;
	private String line2;
	private String line3;
	
	public String getLine1() {
		return line1;
	}

	public String getLine2() {
		return line2;
	}

	public String getLine3() {
		return line3;
	}

	private AccountNumber() {
		
	}
	
	public String parseToString() {
		return DigitalAccountNumberParser.ConvertAccountNumberToString(this);
		
	}
	
	public boolean isaValidAccountNumber() {
		String accountNumber = this.parseToString();
		boolean isValid = false;
		if(accountNumber.length() == 9 && accountNumberCheckSum(accountNumber) == 0) {
			isValid = true;
		}
		return isValid;
		
	}
	
	public static int accountNumberCheckSum(String accountNumber) {
		char[] cAccNumber = accountNumber.toCharArray();
		int checkSum = (Character.getNumericValue(cAccNumber[8])+
						(2*Character.getNumericValue(cAccNumber[7]))+
						(3*Character.getNumericValue(cAccNumber[6]))+
						(4*Character.getNumericValue(cAccNumber[5]))+
						(5*Character.getNumericValue(cAccNumber[4]))+
						(6*Character.getNumericValue(cAccNumber[3]))+
						(7*Character.getNumericValue(cAccNumber[2]))+
						(8*Character.getNumericValue(cAccNumber[1]))+
						(9*Character.getNumericValue(cAccNumber[0])))% 11;
		
		return checkSum;
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
