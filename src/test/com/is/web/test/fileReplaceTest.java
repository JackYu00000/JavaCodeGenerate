package com.is.web.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import org.junit.Test;

import com.tools.TemplateFile;

public class fileReplaceTest {
	static String webname = "testproj";
	static String packagename="com.is.web." + webname;
	
	@Test
	public void test() {
		TemplateFile tf = new TemplateFile();
		renameBaseFolder(tf.realPathBuild("","","./src/java/com/is/web/"));
	}

	static void renameBaseFolder(String filePath){
		File root = new File(filePath);
		File newFolder = new File(filePath + webname);
		File[] files = root.listFiles();
		for(File file:files){     
			if(file.isDirectory()){
//				file.renameTo(newFolder);
			}    
		 }
//		modifyFiles(newFolder.getAbsolutePath());
		modifyFiles(filePath);
	}
	
	static void modifyFiles(String filePath){
		File root = new File(filePath);
		File[] files = root.listFiles();
		for(File file:files){     
			if(!file.isDirectory()){
					System.out.println(file.getAbsolutePath());
			    	 try{
			    		BufferedReader bufReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
	                    StringBuffer strBuffer = new StringBuffer();
	                    String empty = "";
	                    String tihuan = "";
	                    for (String temp = null; (temp = bufReader.readLine()) != null; temp = null) {
	                        temp = temp.replaceFirst("com\\.is\\.web\\.\\w*?\\.", packagename + ".");
	                        strBuffer.append(temp);
	                        strBuffer.append(System.getProperty("line.separator"));//行与行之间的分割
	                    }
	                    bufReader.close();
	                    PrintWriter printWriter = new PrintWriter(file.getAbsolutePath());//替换后输出的文件位置（切记这里的/tmp/source 在你的本地必须有这个文件夹）
	                    printWriter.write(strBuffer.toString().toCharArray());
	                    printWriter.flush();
	                    printWriter.close();
			    	 }catch (Exception e) { 
			    	     e.printStackTrace(); 
			    	 }
		    }else{
		    	modifyFiles(file.getAbsolutePath());
		    }     
		 }
	}
}
