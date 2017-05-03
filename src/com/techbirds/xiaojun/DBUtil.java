package com.techbirds.xiaojun;

import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.ResultSet;  
import java.sql.SQLException;  
import java.sql.Statement;  
  
/** 
 *  
 * @author InJavaWeTrust 
 * 
 */  
public class DBUtil {  
  
    private static Connection conn = null;  
    private static Statement st = null;  
    private static ResultSet rs = null;  
  
    private DBUtil() {  
  
    }  
  
    private static final DBUtil instance = new DBUtil();  
  
    public static DBUtil getInstance() {  
        return instance;  
    }  
  
    /** 
     * 连接数据库 
     *  
     * @return 
     */  
    public Connection connection() {  
        try {  
            Class.forName(Constants.DRIVER);  
        } catch (ClassNotFoundException e1) {  
            e1.printStackTrace();  
        }  
        try {  
            conn = DriverManager.getConnection(Constants.DBURL, Constants.USER,  
                    Constants.PASSWORD);  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
        return conn;  
    }  
  
    /** 
     * 关闭连接 
     *  
     * @param rs 
     * @param st 
     * @param conn 
     */  
    public void release(ResultSet rs, Statement st, Connection conn) {  
        try {  
            try {  
                if (null != rs) {  
                    rs.close();  
                }  
            } catch (Exception e) {  
                rs = null;  
            }  
            try {  
                if (null != st) {  
                    st.close();  
                }  
            } catch (Exception e) {  
                st = null;  
            }  
            try {  
                if (null != conn) {  
                    conn.close();  
                }  
            } catch (Exception e) {  
                conn = null;  
            }  
        } finally {  
            rs = null;  
            st = null;  
            conn = null;  
        }  
    }  
      
    /** 
     * 插入 
     * @param sql 
     */  
    public void insert(String sql){  
        try{  
            DBUtil.getInstance().connection();  
            st = conn.createStatement();  
            st.execute(sql);  
            DBUtil.getInstance().release(rs, st, conn);  
        }catch(Exception e){  
            e.printStackTrace();  
        }  
    }  
  
}  
