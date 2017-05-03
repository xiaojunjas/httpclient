package com.techbirds.test;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

public class HttpClientTest {

	private Logger logger=Logger.getLogger(this.getClass().getName());
	
	/**
	 * get,不带参数
	 */
	@Test
	public void getHttp(String url){
		HttpClient http=new DefaultHttpClient();
		HttpGet get=new HttpGet(url);
		try {
			HttpResponse resp=http.execute(get);
			HttpEntity entity=resp.getEntity();
			logger.info("content Length:"+entity.getContentLength());
			logger.info("content:"+EntityUtils.toString(entity));
			System.out.println(EntityUtils.toString(entity));
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			//关闭连接，释放资源
			http.getConnectionManager().shutdown();
		}
	}
	
	/**
	 * post带参
	 */
	@Test
	public void postHttp(){
		HttpClient http=new DefaultHttpClient();
		HttpPost post=new HttpPost("http://localhost:8080/httpserver/test");
		List<NameValuePair> params=new ArrayList<NameValuePair>();
		params.add(new BasicNameValuePair("username", "奥巴马"));
		params.add(new BasicNameValuePair("password", "123456"));
		try {
			UrlEncodedFormEntity formEntity=new UrlEncodedFormEntity(params,"UTF-8");
			post.setEntity(formEntity);
			logger.info("uri:"+post.getURI());
			try {
				HttpResponse resp=http.execute(post);
				HttpEntity entity=resp.getEntity();
				logger.info("content length:"+entity.getContentLength());
				logger.info("content:"+EntityUtils.toString(entity,"UTF-8"));
				
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}finally{
			//关闭连接，释放资源
			http.getConnectionManager().shutdown();
		}
		
	}
	
	/**
	 * https方式，带参
	 */
	@Test
	public void getHttps(){
		HttpClient hc=new DefaultHttpClient();
		try {
			KeyStore ks=KeyStore.getInstance(KeyStore.getDefaultType());
			try {
				FileInputStream fis=new FileInputStream(new File("d:\\tomcat.keystore"));
				try {
					ks.load(fis,"123456".toCharArray());
					try {
						SSLSocketFactory ssf=new SSLSocketFactory(ks);
						Scheme sch=new Scheme("https", 8443, ssf);
						hc.getConnectionManager().getSchemeRegistry().register(sch);
						HttpPost post=new HttpPost("https://localhost:8443/httpserver/test");
						List<NameValuePair> params=new ArrayList<NameValuePair>();
						params.add(new BasicNameValuePair("username", "奥巴马"));
						params.add(new BasicNameValuePair("password", "123456"));
						UrlEncodedFormEntity formEntity=new UrlEncodedFormEntity(params,"UTF-8");
						post.setEntity(formEntity);
						HttpResponse resp=hc.execute(post);
						HttpEntity entity=resp.getEntity();
						logger.info("content length:"+entity.getContentLength());
						logger.info("content:"+EntityUtils.toString(entity,"UTF-8"));
						
//						HttpGet get=new HttpGet("https://localhost:8443/httpserver/test");
//						HttpResponse resp=hc.execute(get);
//						HttpEntity entity=resp.getEntity();
//						logger.info("content length:"+entity.getContentLength());
//						logger.info("content:"+EntityUtils.toString(entity,"UTF-8"));
						
					} catch (KeyManagementException e) {
						e.printStackTrace();
					} catch (UnrecoverableKeyException e) {
						e.printStackTrace();
					}
					
					
					
				} catch (NoSuchAlgorithmException e) {
					e.printStackTrace();
				} catch (CertificateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
		} catch (KeyStoreException e) {
			e.printStackTrace();
		}
	}
	
	
	public  void downLoad(String url,String dst){
		try {  
            HttpGet httpGet = new HttpGet(url);  
            HttpClient http=new DefaultHttpClient();
            HttpResponse httpResponse = http.execute(httpGet);  
            HttpEntity entity = httpResponse.getEntity();  
            InputStream in = entity.getContent();  
            long length=entity.getContentLength();  
            if(length<=0){  
                System.out.println("下载文件不存在！");  
                return;  
            }  
            OutputStream out = new FileOutputStream(new File(dst));  
            saveTo(in, out);  
        } catch (IOException e) {  
            e.printStackTrace();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
	}
	
	
	 public void saveTo(InputStream in, OutputStream out) throws Exception {  
	        byte[] data = new byte[1024*1024];  
	        int index =0;  
	        while ((index=in.read(data) )!= -1) {  
	            out.write(data,0,index);  
	        }  
	        in.close();  
	        out.close();  
	    }
	 
	 public static void main(String[] args) {
			HttpClientTest hct = new HttpClientTest();
			//String url = "http://www.sherc.net/sherc/platform/resapply/resapply.jsp?resid=120&type=play";
			//hct.getHttp(url);
			hct.downLoad("http://www.sherc.net/sherc/platform/resapply/resapply.jsp?resid=120&type=play", "E://a.doc");
			
		}
}
