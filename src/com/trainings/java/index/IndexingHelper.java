package com.trainings.java.index;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class IndexingHelper {

	public HashMap<Integer,String> filePathMapper = new HashMap<Integer,String>();
	public HashMap<String, ArrayList<Integer>> invertedIndex = new HashMap<String,ArrayList<Integer>>();
	
	
	public void createFilePathMapperIndex(int index,File file){
		filePathMapper.put(index, file.getAbsolutePath());			
	}
	public void populateInvertedIndex(Set<String> words,int fileNumber){
		
		for(String word:words){
			if(invertedIndex.containsKey(word)){
				ArrayList<Integer> fileList = invertedIndex.get(word);
				fileList.add(fileNumber);
				invertedIndex.put(word, fileList);
			}
			else{
				ArrayList<Integer> fileList= new ArrayList<Integer>();
				fileList.add(fileNumber);
				invertedIndex.put(word, fileList);
			}
				
			
		}
		
		
		
	}
	
	//HashMap<String,Integer> invertedIndex = new HashMap<String,Integer>();
	
	
}
