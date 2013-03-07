package com.kata.bankocr;

import static org.junit.Assert.*;

import org.junit.Ignore;
import org.junit.Test;

public class AccountNumberTest {
	
	@Test
	public void parseDigitalRepresetationOfZEROIntoChar() {
		AccountNumber accountNumber = new AccountNumber.Builder()
								   .line1(" _ ")
								   .line2("| |")
								   .line3("|_|")
								   .build();
		
		assertEquals("account number should be equal", "0",accountNumber.parseToString());
		
		
	}
	@Test
	public void parseDigitalRepresetationOfONEIntoChar() {
		AccountNumber accountNumber = new AccountNumber.Builder()
		.line1("   ")
		.line2("  |")
		.line3("  |")
		.build();
		
		assertEquals("account number should be equal", "1",accountNumber.parseToString());
		
		
	}
	@Test
	public void parseDigitalRepresetationOfTWOIntoChar() {
		AccountNumber accountNumber = new AccountNumber.Builder()
		.line1(" _ ")
		.line2(" _|")
		.line3("|_ ")
		.build();
		
		assertEquals("account number should be equal", "2",accountNumber.parseToString());
		
		
	}
	@Test
	public void parseDigitalRepresetationOfTHREEIntoChar() {
		AccountNumber accountNumber = new AccountNumber.Builder()
		.line1(" _ ")
		.line2(" _|")
		.line3(" _|")
		.build();
		
		assertEquals("account number should be equal", "3",accountNumber.parseToString());
		
		
	}
	@Test
	public void parseDigitalRepresetationOfFOURIntoChar() {
		AccountNumber accountNumber = new AccountNumber.Builder()
		.line1("   ")
		.line2("|_|")
		.line3("  |")
		.build();
		
		assertEquals("account number should be equal", "4",accountNumber.parseToString());
		
		
	}
	@Test
	public void parseDigitalRepresetationOfFIVEIntoChar() {
		AccountNumber accountNumber = new AccountNumber.Builder()
		.line1(" _ ")
		.line2("|_ ")
		.line3(" _|")
		.build();
		
		assertEquals("account number should be equal", "5",accountNumber.parseToString());
		
		
	}
	@Test
	public void parseDigitalRepresetationOfSIXIntoChar() {
		AccountNumber accountNumber = new AccountNumber.Builder()
		.line1(" _ ")
		.line2("|_ ")
		.line3("|_|")
		.build();
		
		assertEquals("account number should be equal", "6",accountNumber.parseToString());
	}
	@Test
	public void parseDigitalRepresetationOfSEVENIntoChar() {
		AccountNumber accountNumber = new AccountNumber.Builder()
		.line1(" _ ")
		.line2("  |")
		.line3("  |")
		.build();
		
		assertEquals("account number should be equal", "7",accountNumber.parseToString());
	}
	@Test
	public void parseDigitalRepresetationOfEIGHTIntoChar() {
		AccountNumber accountNumber = new AccountNumber.Builder()
		.line1(" _ ")
		.line2("|_|")
		.line3("|_|")
		.build();
		
		assertEquals("account number should be equal", "8",accountNumber.parseToString());
	}
	@Test
	public void parseDigitalRepresetationOfNINEIntoChar() {
		AccountNumber accountNumber = new AccountNumber.Builder()
		.line1(" _ ")
		.line2("|_|")
		.line3(" _|")
		.build();
		
		assertEquals("account number should be equal", "9",accountNumber.parseToString());
	}
	
	@Test
	public void parseWholeAccountNumberIntoChar() {
		AccountNumber accountNumber = new AccountNumber.Builder()
		.line1("    _  _     _  _  _  _  _  _ ")
		.line2("  | _| _||_||_ |_   ||_||_|| |")
		.line3("  ||_  _|  | _||_|  ||_| _||_|")
		.build();
		
		assertEquals("account number should be equal", "1234567890",accountNumber.parseToString());
	}
	
	@Test
	public void isaValidAccuontNumberReturnFALSEIfTheAccountnumberLengthIsGreaterThan9() {
		AccountNumber accountNumber = new AccountNumber.Builder()
		.line1(" _     _  _  _  _  _  _  _  _ ")
		.line2(" _||_||_ |_||_| _||_||_ |_ | |")
		.line3(" _|  | _||_||_||_ |_||_| _||_|")
		.build();
		
		assertEquals("account number length should be equal to 9", false,accountNumber.isaValidAccountNumber());
	}
	@Test
	public void isaValidAccuontNumberReturnTRUEIfTheAccountnumberLengthIsEqualto9() {
		AccountNumber accountNumber = new AccountNumber.Builder()
		.line1(" _     _  _  _  _  _  _  _ ")
		.line2(" _||_||_ |_||_| _||_||_ |_ ")
		.line3(" _|  | _||_||_||_ |_||_| _|")
		.build();
		
		assertEquals("account number length should be equal to 9", true,accountNumber.isaValidAccountNumber());
	}
	
	@Test
	public void accountNumberCheckSumShouldBeZEROForAValidAccountNumber() {
		assertEquals("Account Number CheckSum should be ",0, AccountNumber.accountNumberCheckSum("345882865"));
	}
	
	@Test
	public void isaValidAccuontNumberReturnTRUEIfTheAccountnumberLengthIsEqualto9AndCheckSumIs0() {
		AccountNumber accountNumber = new AccountNumber.Builder()
		.line1(" _     _  _  _  _  _  _  _ ")
		.line2(" _||_||_ |_||_| _||_||_ |_ ")
		.line3(" _|  | _||_||_||_ |_||_| _|")
		.build();
		
		assertEquals("account number length should be equal to 9", true,accountNumber.isaValidAccountNumber());
	}
	
	@Ignore("fix this later")
	@Test
	public void extraWhiteSpaceInTheAccountNumber() {
		AccountNumber accountNumber = new AccountNumber.Builder()
		.line1(" _ ")
		.line2("|_| ")
		.line3(" _|")
		.build();
		
		assertEquals("account number should be equal", "9",accountNumber.parseToString());
	}

}
