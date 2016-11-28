package com.ssi.stu.logic;

import java.util.List;
import java.util.Map;

import com.ssi.stu.bo.StuInfo;

public interface StuInfoService {
	public void save(StuInfo stuInfo);

	public List<StuInfo> queryAll(StuInfo stuInfo);

	public void updateStuInfoById(StuInfo stuInfo);

	public void deleteStuInfoById(int id);

	public StuInfo findStuInfoById(int id);

	public void insertStuInfoByMap(Map map);
	public void batchAdd(List<Map> list);
}
