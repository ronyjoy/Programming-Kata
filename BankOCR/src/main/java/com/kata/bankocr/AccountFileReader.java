package com.kata.bankocr;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class AccountFileReader {
	private List<AccountNumber> accountNumbers = new ArrayList<AccountNumber>();
 
	public List<AccountNumber> getAccountNumbers() {
		return accountNumbers;
	}

	public AccountFileReader(URL fileLocation) throws URISyntaxException, FileNotFoundException, IOException {
		BufferedReader br = null;
		try {
			if(fileLocation == null ) {
				throw new FileNotFoundException();
			}
			br = new BufferedReader(new FileReader(new File(fileLocation.toURI())));
			String line;
			int lineNumber = 1;
			String[] accNum = new String[3];
			while ((line = br.readLine()) != null) {
				accNum[lineNumber -1] = line;
				lineNumber++;
				if(lineNumber ==4) {
					br.readLine();
					lineNumber = 1;
					AccountNumber accountNumber = new AccountNumber.Builder().line1(accNum[0]).line2(accNum[1]).line3(accNum[2]).build();
					accountNumbers.add(accountNumber);
				}
				
			}
		} finally {
			try {
				if (br != null) {
					br.close();
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
	

}
