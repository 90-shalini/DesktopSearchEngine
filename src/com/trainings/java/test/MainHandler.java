package com.trainings.java.test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import com.trainings.java.collectionutils.CollectionHelper;
import com.trainings.java.fileutils.FileHelper;
import com.trainings.java.fileutils.FileReader;
import com.trainings.java.fileutils.FileReaderInterface;
import com.trainings.java.index.IndexingHelper;

public class MainHandler {
	

	//------------------------------------------------------------------------
	
	public void init(String parentFolder){
		Properties prop = new Properties();
		String extension = null;
		
		
		FileHelper fileHelper = new FileHelper();
		fileHelper.populateProperties(prop, "D://Trainings//CoreJAVA//DesktopSearchEngine//config//extensionClassMapping.properties");
		
		IndexingHelper indexingHelper = new IndexingHelper();
		FileReaderInterface fileReaderInterface = null;
		Class clazz = null;
		FileReader fileReader = new FileReader();
		fileReader.populateStopWords();

		fileHelper.setDirectoryPath(parentFolder);
		ArrayList<File> filteredListOfFiles =fileHelper.getFilesFromFolder(fileHelper.getDirectoryPath());
		
		Set<String> words = new HashSet<String>();
		
		for(int i =0;i<filteredListOfFiles.size();i++){			
			indexingHelper.createFilePathMapperIndex(i,filteredListOfFiles.get(i));
			extension =fileHelper.getFileExtension(filteredListOfFiles.get(i));
			try {
				clazz = null;
				clazz= Class.forName(prop.getProperty(extension));
				fileReaderInterface = (FileReaderInterface) clazz.newInstance();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			words = fileReaderInterface.readFromFile(filteredListOfFiles.get(i));
			indexingHelper.populateInvertedIndex(words,i);
		}
		fileHelper.writeObjectToFile(indexingHelper.invertedIndex, "D://Trainings//CoreJAVA//DesktopSearchEngine//Indexes//i_index.ser");
		fileHelper.writeObjectToFile(indexingHelper.filePathMapper, "D://Trainings//CoreJAVA//DesktopSearchEngine//Indexes//n_index.ser");

	}

	//------------------------------------------------------------------------
		
	public IndexingHelper loadIndexes(){
		IndexingHelper indexingHelper = new IndexingHelper();
		FileHelper fileHelper = new FileHelper();
		indexingHelper.invertedIndex= (HashMap<String,ArrayList<Integer>>)fileHelper.readObjectFromFile("D://Trainings//CoreJAVA//DesktopSearchEngine//Indexes//i_index.ser");
		indexingHelper.filePathMapper= (HashMap<Integer,String>)fileHelper.readObjectFromFile("D://Trainings//CoreJAVA//DesktopSearchEngine//Indexes//n_index.ser");
		return indexingHelper;
	}
	
	
	//------------------------------------------------------------------------
	
	public void search(String[] arrayOfWords,IndexingHelper indexingHelper){
		

		ArrayList<ArrayList<Integer>> shortListedFiles = new ArrayList<ArrayList<Integer>>();
		for(String word:arrayOfWords){
			if(indexingHelper.invertedIndex.containsKey(word)){
				shortListedFiles.add(indexingHelper.invertedIndex.get(word));
			}
		}
		
		ArrayList<Integer> finalList = new ArrayList<Integer>();
		finalList = shortListedFiles.get(0);
		for(int i =1;i<shortListedFiles.size();i++){
			
			finalList = CollectionHelper.union(finalList, shortListedFiles.get(i));
		}
		
		for(int i=0; i<finalList.size(); i++)
		System.out.println(indexingHelper.filePathMapper.get(finalList.get(i)));

	}


	//------------------------------------------------------------------------
	
}
