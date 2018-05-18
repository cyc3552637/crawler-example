package com.util;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DuplicateRemoval {
	public void removerepeat(String path) {
		
		List<String> lines;
		
		try {
			lines = Files.readAllLines(Paths.get(path), Charset.forName("utf-8"));
			Set<String> linesset=new HashSet();
			linesset.addAll(lines);
			List<String> returnlist=new ArrayList();
			returnlist.addAll(linesset);
			Files.write(Paths.get(path), returnlist, Charset.forName("utf-8")); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
   
         
		
		
	}

}
