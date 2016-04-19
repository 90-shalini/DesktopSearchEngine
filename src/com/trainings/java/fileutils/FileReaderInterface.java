package com.trainings.java.fileutils;

import java.io.File;
import java.util.Set;

public interface FileReaderInterface {
	public abstract Set<String> readFromFile(File file);

}
