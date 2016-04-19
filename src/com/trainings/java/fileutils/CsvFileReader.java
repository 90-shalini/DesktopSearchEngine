package com.trainings.java.fileutils;

import java.io.File;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class CsvFileReader extends FileReader implements FileReaderInterface {
	@Override
	public Set<String> readFromFile(File file) {
		Scanner scanner=null;
		String[] words =null;
		Set<String> filteredWords = new HashSet<String>();
		try {
			scanner = new Scanner(file);
			while (scanner.hasNextLine()) {
				words=scanner.nextLine().split(",");
				filterStopWords(words,filteredWords);
			}
			return filteredWords;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			scanner.close();
		}

		return null;
	}

}
