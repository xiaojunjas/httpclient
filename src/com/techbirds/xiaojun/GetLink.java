package com.techbirds.xiaojun;

import java.io.IOException;
import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;  
  
/** 
 *  
 * @author InJavaWeTrust 
 * 
 */  
public class GetLink {  
    
  private JsoupUtil ju = JsoupUtil.getInstance();  
    
  private DBUtil du = DBUtil.getInstance();  
    
  private Link link = new Link();  
    
  private String insertSql = "";  
    
  public void getLink(String url) {  
      try {  
          Document document = Jsoup.connect(url).timeout(5000).get();  
          Elements hrefs = document.select("a[href]");  
          Iterator<Element> hrefIter = hrefs.iterator();  
          while (hrefIter.hasNext()) {  
              Element href = hrefIter.next();  
              link.setId(ju.getUUID());  
              link.setUrlName(href.text());  
              link.setUrl(href.attr("href"));  
              insertSql = ju.getInsertSql(link);  
              System.out.println(insertSql);
              du.insert(insertSql);  
          }  
          Elements srcs = document.select("img[src]");  
          Iterator<Element> srcIter = srcs.iterator();  
          while(srcIter.hasNext()){  
              Element src = srcIter.next();  
              link.setId(ju.getUUID());  
              link.setUrlName(src.attr("alt"));  
              link.setUrl(src.attr("src"));  
              insertSql = ju.getInsertSql(link);  
              du.insert(insertSql);  
          }  
          Elements opts = document.select("option[value]");  
          Iterator<Element> optIter = opts.iterator();  
          while(optIter.hasNext()){  
              Element opt = optIter.next();  
              link.setId(ju.getUUID());  
              link.setUrlName(opt.text());  
              link.setUrl(opt.attr("value"));  
              insertSql = ju.getInsertSql(link);  
              du.insert(insertSql);  
          }  
          Elements links = document.select("link[href]");  
          Iterator<Element> linkIter = links.iterator();  
          while(linkIter.hasNext()){  
              Element li =  linkIter.next();  
              link.setId(ju.getUUID());  
              link.setUrlName(li.text());  
              link.setUrl(li.attr("href"));  
              insertSql = ju.getInsertSql(link);  
              du.insert(insertSql);  
          }  
      } catch (IOException e) {  
          e.printStackTrace();  
      }  
  }  
    
  public static void main(String[] args) throws IOException {  
      new GetLink().getLink(Constants.URL); 
	  	/* Document document = Jsoup.connect("https://item.jd.com/3652063.html").timeout(5000).get(); 
	     Elements select = document.select("div[class=tab-con]");
	     Elements select2 = select.select("div");
	     Elements select2 = document.select("div[comment-item]");
	     
	     String text = select2.get(0).text();
	     System.out.println(text);
	     for (Element element : select2) {
			System.out.println(element.text());
//			System.out.println(element.select("a").attr("href"));
		}*/
  }  
  
}  