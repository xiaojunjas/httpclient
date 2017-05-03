package com.techbirds.xiaojun;

import java.io.Serializable;  
import java.util.Date;  
  
/** 
 *  
 * @author InJavaWeTrust 
 * 
 */  
public class Link implements Serializable{  
  
    private static final long serialVersionUID = 1165098694307553167L;  
    /** 
     * ID 
     */  
    private String id;  
    /** 
     * link name 
     */  
    private String urlName;  
    /** 
     * link url 
     */  
    private String url;  
    /** 
     * insert db date 
     */  
    private Date date;  
  
    public String getId() {  
        return id;  
    }  
  
    public void setId(String id) {  
        this.id = id;  
    }  
  
    public String getUrlName() {  
        return urlName;  
    }  
  
    public void setUrlName(String urlName) {  
        this.urlName = urlName;  
    }  
  
    public String getUrl() {  
        return url;  
    }  
  
    public void setUrl(String url) {  
        this.url = url;  
    }  
  
    public Date getDate() {  
        return date;  
    }  
  
    public void setDate(Date date) {  
        this.date = date;  
    }  
      
}  
