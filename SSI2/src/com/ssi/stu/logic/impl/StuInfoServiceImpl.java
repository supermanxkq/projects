package com.ssi.stu.logic.impl;

import java.util.List;
import java.util.Map;

import com.ssi.stu.bo.StuInfo;
import com.ssi.stu.dao.StuInfoDao;
import com.ssi.stu.logic.StuInfoService;

public class StuInfoServiceImpl implements StuInfoService{
	private StuInfoDao stuInfoDao;
	
	
	public StuInfoDao getStuInfoDao() {
		return stuInfoDao;
	}

	public void setStuInfoDao(StuInfoDao stuInfoDao) {
		this.stuInfoDao = stuInfoDao;
	}

	@Override
	public void deleteStuInfoById(int id) {
		stuInfoDao.deleteStuInfoById(id);
	}

	@Override
	public StuInfo findStuInfoById(int id) {
		return stuInfoDao.findStuInfoById(id);
	}

	@Override
	public List<StuInfo> queryAll(StuInfo stuInfo) {
		return stuInfoDao.queryAll(stuInfo);
	}

	@Override
	public void save(StuInfo stuInfo) {
		stuInfoDao.save(stuInfo);
	}

	@Override
	public void updateStuInfoById(StuInfo stuInfo) {
		stuInfoDao.updateStuInfoById(stuInfo);
	}

	@Override
	public void insertStuInfoByMap(Map map) {
		stuInfoDao.insertStuInfoByMap(map);
	}

	@Override
	public void batchAdd(List<Map> list) {
		stuInfoDao.batchAdd(list);
	}
	
}
