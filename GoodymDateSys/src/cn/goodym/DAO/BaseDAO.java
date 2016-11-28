package cn.goodym.DAO;

import java.sql.Connection;
import java.util.*;
import java.io.Serializable;


/** ͳһ��ݷ��ʽӿ� */
public interface BaseDAO {
	/** ����ָ��ID�ĳ־û����� */
	public Object loadById(Class clazz,Serializable id);
	
	/** �������������ĳ־û����� */
	public Object loadObject(String hql);
	
	/** ɾ��ָ��ID�ĳ־û����� */
	public void delById(Class clazz,Serializable id);
	
	/** ��������ָ���ĳ־û����� */
	public void saveOrUpdate(Object obj);
	
	/** װ��ָ��������г־û����� */
	public List listAll(String clazz);
	
	/** ��ҳװ��ָ��������г־û����� */
	public List listAll(String clazz,int pageNo,int pageSize);
	
	/** ͳ��ָ��������г־û����� */
	public int countAll(String clazz);
	
	/** ��ѯָ��������������ĳ־û����� */
	public List query(String hql);
	
	/** ��ҳ��ѯָ��������������ĳ־û����� */
	public List query(String hql,int pageNo,int pageSize);
	
	/** ͳ��ָ����Ĳ�ѯ��� */
	public int countQuery(String hql);
	
	/** ͳ��ָ����Ĳ�ѯ���2 */
	public int countQuery2(String hql);
	
	/** ����������� */
	public int update(String hql);
	
	/** �����ӳ���ȡ��һ��JDBC���� */
	public Connection getConnection();
	/** ȡ��IP��ַ��Ӧ�������ַ */
	public String getIpToAddress(double ip);	
	/**���µ����ķ���*/
	public boolean updateClicks(int clicks,int id);
	
	
	/** ԭ��̬SQL����������� */
	public int SqlUpdate(String Sql);
	/** ԭ��̬SQLͳ��ָ����Ĳ�ѯ��� */
	public int countSqlQuery(String sql);
	/** ԭ��̬SQL��ѯָ��������������ĳ־û����� */
	public List SqlQueryList(String sql);
	/** ԭ��̬SQL��ҳ��ѯָ��������������ĳ־û����� */
	public List SqlQueryList(String sql,int pageNo,int pageSize);
}


