package com.trainings.java.fileutils;

import java.util.ArrayList;
import java.util.Set;

public class FileReader{
	ArrayList<String> stopWords=new ArrayList<String>();
	public void populateStopWords(){
		stopWords.add("is");
		stopWords.add("the");
		stopWords.add("a");		
	}

	

	public void filterStopWords(String[] words,Set<String> filteredWords){
		for(String word:words){
			if(!stopWords.contains(word))
				filteredWords.add(word);						
		}
	}
}