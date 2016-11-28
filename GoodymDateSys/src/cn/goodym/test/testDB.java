package cn.goodym.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import cn.goodym.DAO.ConnDB;

import com.mysql.jdbc.PreparedStatement;

public class testDB {

	/**
	 * jdbc����
	 * @param args
	 */
	public static void main(String[] args) {
		
		Connection conn_mysql_fdrcms=null;
		Statement stmt=null;
	    ResultSet rs = null;
	    
	    
	    String admin_name="";
	    String login_name="";
	    String login_pwd="";
	    
		try{
			ConnDB connDB = new ConnDB();
        	conn_mysql_fdrcms=connDB.ConnDataBase("mysql","127.0.0.1","3306","fdrapp","root","root");
			stmt = conn_mysql_fdrcms.createStatement();
			
			String sql="select * from app_admin";
        	rs = stmt.executeQuery(sql);
        	
        	while (rs.next()){
        		 admin_name=rs.getString("admin_name");//���
        		 login_name=rs.getString("login_name");//�˺�
        		 login_pwd=rs.getString("login_pwd");//����
        		 System.out.println(admin_name+"��"+login_name+"��"+login_pwd);
        	}
        	rs.close();
            stmt.close();
	   }catch(Exception e){
	       e.printStackTrace();	      	 	    	         	 
       }
	}
}
