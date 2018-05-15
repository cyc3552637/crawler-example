package com.util;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Save {
	public void save(String path,String content) {
		
	
		try {
			
			if(Files.exists(Paths.get(path)) == false){
				Files.write(Paths.get(path), content.getBytes());	
			}
			else{
			Files.write(Paths.get(path), content.getBytes(),StandardOpenOption.APPEND);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
}
