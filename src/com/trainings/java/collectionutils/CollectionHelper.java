package com.trainings.java.collectionutils;

import java.util.ArrayList;
import java.util.TreeSet;

public class CollectionHelper {


	public static ArrayList<Integer> union(ArrayList<Integer> arrayList1,ArrayList<Integer> arrayList2){
		TreeSet<Integer> hashedArray = new TreeSet<Integer>();
		for (Integer entry : arrayList1) {
			hashedArray.add(entry);
		}

		for (Integer entry : arrayList2) {
			hashedArray.add(entry);
		}

		return new ArrayList<Integer>(hashedArray); //.toArray(new Integer[0]);
	}

	public static ArrayList<Integer> intersection(ArrayList<Integer> arrayList1,ArrayList<Integer> arrayList2){
		ArrayList<Integer> intersectionList = new ArrayList<Integer>(arrayList1.size()>arrayList2.size()? arrayList1.size():arrayList2.size());
		intersectionList.addAll(arrayList1);
		intersectionList.retainAll(arrayList2);
		return intersectionList;
	}


}
