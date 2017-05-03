package com.techbirds.phrase;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class Phrase {

	public static void main(String[] args) {

		try {

		/*	List<String> lines = new ArrayList<String>();

			FileReader reader = new FileReader("G://极课//grade_原始 - 副本 - 处理.txt");
			BufferedReader br = new BufferedReader(reader);
			String str = null;
			
			while ((str = br.readLine()) != null) {
				lines.add(str);
			}
			for (String line : lines) {
				System.out.println(line);
			}
			br.close();
			reader.close();
			
			*
			*/
			
			//G:\极课\filetest
			/*List<String> lines = FileUtils.readLines(new File("G://极课//full.txt"), "UTF-8");
			List<String> lines1= FileUtils.readLines(new File("G://极课//ok.txt"), "UTF-8");
			File fileList = new File("G://极课//file");
			int cout = 0;
			if(fileList.isDirectory()){
				File[] listFiles = fileList.listFiles();
				for (File file : listFiles) {
					for (String string : lines) {
						if(file.getName().split("\\.")[0].split("_")[0].equals(string.split(";")[0])){
							System.out.println(file.getName().split("\\.")[0]+";"+file.getName().split("\\.")[1]+";"+file.length());
							cout +=1; 
						}
					}
					
				}
				System.out.println(cout+"---------------");
			}*/
			/*File fileList = new File("G://极课//filetest");
			if(fileList.isDirectory()){
				File[] listFiles = fileList.listFiles();
				for (File file : listFiles) {
					for (String string : lines) {
						if(file.getName().split("\\.")[0].split("_")[0].equals(string.split(";")[0])){
							System.out.println(file.getName().split("\\.")[0]+";"+file.getName().split("\\.")[1]+";"+file.length());
						}
					}
					
				}
			}*/
			
		/*	List<String> lines = FileUtils.readLines(new File("G://极课//allProperty1(只有value)_原始 - 副本.txt"), "UTF-8");
			
			for (String line : lines) {
				String[] split = line.split(";;");
				String resId = split[0];
				String name = split[1];
				String description = split[9];
				System.out.println(resId+";"+name+";"+description);
				String message = resId+";"+name+";"+description;
				FileWriter fw = new FileWriter(new File("G://极课//name.txt"),true);
				fw.write(message+"\n");
				fw.flush();
			}*/
			
			
			/*List<String> namelines = FileUtils.readLines(new File("G://极课//name.txt"), "UTF-8");
			
			List<String> subjectLines = FileUtils.readLines(new File("G://极课//grade_原始 - 副本 - 处理.txt"), "UTF-8");
			
			for (String line : namelines) {
				
				String[] split = line.split(";");
				String resId = split[0].trim();
				
				for (String line1 : subjectLines) {
					
					String[] split2 = line1.split(";");
					String resId1 = split2[0].trim();
					
					if(resId.equals(resId1)){
						
						String name = split[1];
						String description = split[2];
						
						String message = "";
						
						if(split2.length>5){
							message = resId+";"+split2[1]+";"+split2[2]+";"+split2[3]+";"+split2[4]+";"+split2[5]+";"+name+";"+description;
						}else{
							message = resId+";"+split2[1]+";"+split2[2]+";"+split2[3]+";"+split2[4]+"; ;"+name+";"+description;
						}
						
						FileWriter fw = new FileWriter(new File("G://极课//full.txt"),true);
						fw.write(message+"\n");
						fw.flush();
						
					}
				}
				
			}*/
			
			/*List<String> lines = FileUtils.readLines(new File("G://极课//full.txt"), "UTF-8");
			List<String> lines1= FileUtils.readLines(new File("G://极课//ok.txt"), "UTF-8");
			for (String line : lines) {
				String resId = line.split(";")[0];
				if(!lines1.contains(resId)){
					FileWriter fw = new FileWriter(new File("G://极课//fullleft.txt"),true);
					fw.write(line+"\n");
					fw.flush();
				}
				
			}*/
			String str = "[\"A,B,C\"]";
			System.out.println(str);
			str = str.replaceAll("\\[", "").replaceAll("\\]", "").replaceAll("\\\"", "");
			System.out.println(str);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
