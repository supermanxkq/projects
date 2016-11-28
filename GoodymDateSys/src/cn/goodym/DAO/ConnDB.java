package cn.goodym.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
/*****************************
 * �������
 * 1.����MySql��
 * 2.����Sql Service2000��
 * @author mok
 * @Time 2007-11-13
 *****************************/
public class ConnDB {
	
	/**********************************************
     * ������ݿ⺯��
     * sqltypeΪ��ݿ����� mysql��SqlServer��ݿ�
     * ipΪ��ݿ�IP��ַ
     * port��ݿ�˿�
     * userΪ��ݿ��˺�
     * passwordΪ��ݿ�����
     * characterEncodingΪ��ݿ��ַ�
     ***********************************************/
	public static Connection ConnDataBase(String sqltype,String ip,String port,String database,String user,String password){
		
		Connection conn=null;
		String url="";
	    try{
			if (conn==null||conn.isClosed()){
				if(sqltype.equalsIgnoreCase("mysql")){
					Class.forName("com.mysql.jdbc.Driver").newInstance(); 
					url="jdbc:mysql://"+ip+":"+port+"/"+database+"?useUnicode=true&characterEncoding=gb2312";
					conn = DriverManager.getConnection(url,user,password);
				}else if(sqltype.equalsIgnoreCase("sqlserver")){
					Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver").newInstance();
			        url="jdbc:microsoft:sqlserver://"+ip+":"+port+";SelectMethod=cursor;DatabaseName="+database;
			        conn= DriverManager.getConnection(url,user,password);
				}
			}
        }catch(Exception ex){
        	System.out.println(ex.getMessage());	      	 	    	         	 
       }
       return conn;
	}
}
