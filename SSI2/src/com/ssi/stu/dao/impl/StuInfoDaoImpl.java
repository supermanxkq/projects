package com.ssi.stu.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

import com.ssi.stu.bo.StuInfo;
import com.ssi.stu.dao.StuInfoDao;
import com.ssi.stu.util.BatchInsertSqlMapCallback;

@SuppressWarnings("deprecation")
public class StuInfoDaoImpl extends SqlMapClientDaoSupport implements StuInfoDao {
	private static String BASIC = "basic.";

	@Override
	public void deleteStuInfoById(int id) {
		this.getSqlMapClientTemplate().delete(BASIC + "deleteStuInfoById", id);
	}

	@Override
	public StuInfo findStuInfoById(int id) {
		return (StuInfo) this.getSqlMapClientTemplate().queryForObject(BASIC + "findStuInfoById", id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<StuInfo> queryAll(StuInfo stuInfo) {
		return this.getSqlMapClientTemplate().queryForList(BASIC + "selectAllStuInfo", stuInfo);
	}

	@Override
	public void save(StuInfo stuInfo) {
		this.getSqlMapClientTemplate().insert(BASIC + "insertStuInfo", stuInfo);
	}

	@Override
	public void updateStuInfoById(StuInfo stuInfo) {
		this.getSqlMapClientTemplate().update(BASIC + "updateStuInfoById", stuInfo);
	}

	@Override
	public void insertStuInfoByMap(Map map) {
		this.getSqlMapClientTemplate().insert(BASIC + "insertStuInfoByMap", map);
	}

	@Override
	public void batchAdd(List<Map> list) {
		BatchInsertSqlMapCallback bsc=new BatchInsertSqlMapCallback();
		bsc.setDataList(list);
		bsc.setStatementId(BASIC +"batchAdd");
		getSqlMapClientTemplate().execute(bsc);
	}
}
