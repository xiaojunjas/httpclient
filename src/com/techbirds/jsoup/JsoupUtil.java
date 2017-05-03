package com.techbirds.jsoup;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class JsoupUtil {
	 
    public static void renameHTMLTitle(String dir) throws IOException {  
        File f = new File(dir);  
        if (f.isDirectory()) {  
            File fs[] = f.listFiles();  
            for (File s : fs) {  
                    Document doc = Jsoup.parse(s, "UTF-8");  
                    System.out.println(doc);
                   /* FileOutputStream fos = new FileOutputStream(s, false);  
                    OutputStreamWriter osw = new OutputStreamWriter(fos, "gb2312");  
                    osw.write(doc.html());  
                    osw.close(); */ 
            }
        } 
    } 
    
    
    public static void renameHTMLTitle1(String dir) throws IOException {  
        File f = new File(dir);
        Document doc = Jsoup.parse(f, "UTF-8");  
        Elements elements = doc.getAllElements();
        
        Elements tds = elements.select("td");
        for (Element element : tds) {
			String attr = element.attr("onclick");
			if(attr.indexOf("play")>-1){
				String[] resId = attr.split("'");
				String url = "http://www.sherc.net/sherc/platform/resapply/resapply.jsp?resid="+resId[1]+"&type=property";
				FileWriter fw = new FileWriter(new File("E:/sherc/url.txt"),true);
				fw.write(url+"\n");
				fw.flush();
			}
        }
        
    }  
    
    
    public static void phraseProperty(String filePath) {
    	
        File f = new File(filePath);
        
        Document doc;
		try {
			   doc = Jsoup.parse(f, "UTF-8");
			   Elements elements = doc.getAllElements();
			   String text = elements.text();
			   String[] split = text.split(";;");
			   String resId = split[0];
		       Elements trs = elements.select("tr");
		       String message = resId;
		       for (Element element : trs) {
					Elements tds = element.select("td");
					message += ";;"+tds.get(0).text().trim()+":"+tds.get(1).text().trim();
			   }
		       System.out.println(message);
			   	FileWriter fw = new FileWriter(new File("G://极课//AllProperty.txt"),true);
				fw.write(message+"\n");
				fw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}  
     
        
    } 
    
 public static void phrasePropertyHtml(String html) {
		try {
			   Document doc = Jsoup.parse(html);
			   Elements elements = doc.getAllElements();
			   String text = elements.text();
			   String[] split = text.split(";;");
			   String resId = split[0];
		       Elements trs = elements.select("tr");
		       String message = resId;
		       for (Element element : trs) {
					Elements tds = element.select("td");
					message += ";;"+tds.get(1).text().trim();
			   }
		        System.out.println(message);
			   	FileWriter fw = new FileWriter(new File("G://极课//allProperty1.txt"),true);
				fw.write(message+"\n");
				fw.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}  
     
        
    } 
 
 public static void renameJDHTML(String dir) throws IOException {  
     File f = new File(dir);
     Document doc = Jsoup.parse(f, "UTF-8");  
     Elements elements = doc.getAllElements();
     
     Elements select = elements.select("ul[class=JS_navCtn cate_menu]");
     Elements select2 = select.select("li");
     String text = select2.get(0).text();
     for (Element element : select2) {
		System.out.println(element.text());
	}
     
     
 }  
    
    public static void main(String[] args) throws IOException {
//    	renameJDHTML("F://jd.html");
    	  Document doc = Jsoup.connect("http://www.nongli.com/item4").get(); 
          Element infoTable = doc.getElementsByAttributeValue("class", "nav").first(); 
          Elements tableLineInfos = infoTable.select("ul"); 
          for (Element lineInfo : tableLineInfos) { 
//              String lineInfoContent = lineInfo.select("a").last().text().trim();
        	  String lineInfoContent = lineInfo.text();
              System.out.println("jsoup is :" + lineInfoContent); 
          } 
    	/* try {
			List<String> lines = FileUtils.readLines(new File("G://极课//property.txt"), "UTF-8");
			for (String html : lines) {
				phrasePropertyHtml(html);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}*/
	}
}