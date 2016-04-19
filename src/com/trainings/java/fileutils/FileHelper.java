package com.trainings.java.fileutils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Properties;


public class FileHelper {
	String directoryPath ="";
	public String getDirectoryPath() {
		return directoryPath;
	}
	public void setDirectoryPath(String directoryPath) {
		this.directoryPath = directoryPath;
	}
	public void getFileNames(File folder,ArrayList<File> filteredListOfFiles){
		File[] listOfFiles = folder.listFiles();
		for (int i = 0; i < listOfFiles.length; i++) {
			if (listOfFiles[i].isFile() && validateExtension(listOfFiles[i].getName())) {
				filteredListOfFiles.add(listOfFiles[i]);
			} else if (listOfFiles[i].isDirectory()) {
				getFileNames(listOfFiles[i],filteredListOfFiles);
			}
		}

	}
	public ArrayList<File> getFilesFromFolder(String directoryPath){
		File folder = new File(directoryPath);
		ArrayList<File> filteredListOfFiles = new ArrayList<File>();
		getFileNames(folder, filteredListOfFiles);
		return filteredListOfFiles;
	}

	public Boolean validateExtension(String fileName){
		String[] supportedExtensions= {"txt","csv","pdf"};
		for(int i=0; i<supportedExtensions.length;i++){
			if(fileName.endsWith(supportedExtensions[i]))
				return true;
		}		
		return false;
	}
	public void writeObjectToFile(Object obj,String filePath){
		try
		{
			FileOutputStream fileOut =
					new FileOutputStream(filePath);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(obj);
			out.close();
			fileOut.close();
		}catch(IOException i)
		{
			i.printStackTrace();
		}
	}
	public Object readObjectFromFile(String filePath){
		Object obj = new Object();
		try
		{
			FileInputStream fileIn = new FileInputStream(filePath);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			obj= in.readObject();
			in.close();
			fileIn.close();
			return obj;
		}catch(Exception i)
		{
			i.printStackTrace();
			return null;

		}
	}

	public void populateProperties(Properties prop,String filePath){
		InputStream input = null;
		try {
			input = new FileInputStream(filePath);
			// load a properties file
			prop.load(input);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public String getFileExtension(File file) {
	    String name = file.getName();
	    try {
	        return name.substring(name.lastIndexOf(".") + 1);
	    } catch (Exception e) {
	        return "";
	    }
	}





}
