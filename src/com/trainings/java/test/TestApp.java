package com.trainings.java.test;

import com.trainings.java.index.IndexingHelper;

public class TestApp {

	public static void main(String[] args) {

		String parentFolder = "D://TestFiles";
		MainHandler mh = new MainHandler();
		mh.init(parentFolder);
//		
		String[] words = {"arora","cisco"};
		IndexingHelper indexingHelper = mh.loadIndexes();
		mh.search(words,indexingHelper);
		
		
	}

}
