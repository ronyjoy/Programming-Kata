package com.kata.bankocr;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import org.junit.Test;

public class AccountNumberFileReaderTest {

@Test
public void readTheAccountNumberFileSuccess() throws URISyntaxException, IOException {
	ClassLoader loader = AccountNumberFileReaderTest.class.getClassLoader();
	URL url =loader.getResource("fileReaderTest.txt");
	AccountFileReader fileReader = new AccountFileReader(url);
	assertEquals("Expected one line data is read from the file ", 1, fileReader.getAccountNumbers().size());
	assertEquals("Expected characters in the first line is",  " _  _  _  _  _  _  _  _  _ ",fileReader.getAccountNumbers().get(0).getLine1());
	assertEquals("Expected characters in the secoend line is","| || || || || || || || || |",fileReader.getAccountNumbers().get(0).getLine2());
	assertEquals("Expected characters in the third line is",  "|_||_||_||_||_||_||_||_||_|",fileReader.getAccountNumbers().get(0).getLine3());
}

@Test(expected = FileNotFoundException.class)
public void readTheAccountNumberFileFailure() throws URISyntaxException, IOException {
	ClassLoader loader = AccountNumberFileReaderTest.class.getClassLoader();
	URL url =loader.getResource("FileThatDonotExistsInTheFileSystem.txt");
	AccountFileReader fileReader = new AccountFileReader(url);
}



}
