package com.techbirds.xiaojun;

import java.util.UUID;  

/** 
 *  
 * @author InJavaWeTrust 
 * 
 */  
public class JsoupUtil {  
  
    private JsoupUtil() {  
  
    }  
  
    private static final JsoupUtil instance = new JsoupUtil();  
  
    public static JsoupUtil getInstance() {  
        return instance;  
    }  
      
    /** 
     * 得到UUID 
     * @return 32位UUID 
     */  
    public String getUUID() {  
        String s = UUID.randomUUID().toString();  
        return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18)  
                + s.substring(19, 23) + s.substring(24);  
    }  
      
    /** 
     * insert sql 
     * @param link Link obj 
     * @return sql 
     */  
    public String getInsertSql(Link link) {  
        return "insert into jsoup (id, urlname, url, date) values ('"  
                + link.getId() + "','" + link.getUrlName() + "','"  
                + link.getUrl() + "',NOW())";  
    }  }  
